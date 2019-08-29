package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.MainActivity;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.CityBean;
import luyuan.tech.com.chaoke.bean.ZukeOneBean;
import luyuan.tech.com.chaoke.bean.ZukeTwoBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.KeyBoardUtil;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/18
 * @describe:
 */


public class ZuKeXinXiQiTaActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_submmit)
    Button btnSubmmit;
    @BindView(R.id.sl_city)
    SelectLayout slCity;
    @BindView(R.id.et_zujin_low)
    EditText etZujinLow;
    @BindView(R.id.et_zujin_high)
    EditText etZujinHigh;
    @BindView(R.id.sl_ruzhurenshu)
    InputLayout slRuzhurenshu;
    @BindView(R.id.sl_ruzhushijian)
    SelectLayout slRuzhushijian;
    @BindView(R.id.et_more)
    EditText etMore;
    @BindView(R.id.sl_huxing)
    SelectLayout slHuxing;
    private String id;
    private String tenant_id;
    private List<CityBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<CityBean.ChildCityBean>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<CityBean.ChildCityBean.ChildAreaBean>>> options3Items = new ArrayList<>();
    private ArrayList<String> shiList;
    private ArrayList<String> tingList;
    private ArrayList<String> weiList;
    private int shi, ting, wei;
    private int topId, cityId, areaId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuke_xinxi_qita);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
            tenant_id = getIntent().getStringExtra("tenant_id");
        }
        shiList = new ArrayList<>();
        tingList = new ArrayList<>();
        weiList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            shiList.add(i + "室");
            weiList.add(i + "卫");
            tingList.add(i + "厅");
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnSubmmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        setDatePickerListener(slRuzhushijian);

        slHuxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyBoardUtil.hideKeyBoard(getActivity());
                showHuXingPickerView();
            }
        });
        loadCity();
    }

    private void loadOldData() {
        HttpManager.post(HttpManager.ZUKEBIANJI_TWO)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("tenant_id", tenant_id)
                .execute(new SimpleCallBack<ZukeTwoBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(ZukeTwoBean data) {
                        etZujinLow.setText(data.getRent_min());
                        etZujinHigh.setText(data.getRent_max());
                        shi = data.getRoom();
                        ting = data.getOffice();
                        wei = data.getToilet();
                        topId = data.getTop_city();
                        cityId = data.getCity_id();
                        areaId = data.getArea_id();
                        slHuxing.setText(data.getRoom() + "室" + data.getOffice() + "厅" + data.getToilet() + "卫");
                        int city1Index = findCity1(data.getTop_city());
                        int city2Index = findCity2(data.getCity_id(), city1Index);
                        int city3Index = findCity3(data.getArea_id(), city1Index, city2Index);
                        slCity.setText(city1 + city2 + city3);
                        slRuzhurenshu.setText(data.getPeople_num() + "");
                        slRuzhushijian.setText(data.getCheckin_time());
                        etMore.setText(data.getDesc());
                    }
                });
    }

    String city3;
    String city2;
    String city1;

    private int findCity3(int areaId, int city1Index, int city2Index) {
        for (int i = 0; i < options3Items.get(city1Index).get(city2Index).size(); i++) {
            if (options3Items.get(city1Index).get(city2Index).get(i).getId() == areaId) {
                city3 = options3Items.get(city1Index).get(city2Index).get(i).getArea_name();
                return i;
            }
        }
        return 0;
    }

    private int findCity2(int cityId, int index) {
        for (int i = 0; i < options2Items.get(index).size(); i++) {
            if (options2Items.get(index).get(i).getId() == cityId) {
                city2 = options2Items.get(index).get(i).getCity_name();
                return i;
            }

        }
        return 0;
    }

    private int findCity1(int top_city) {
        for (int i = 0; i < options1Items.size(); i++) {
            if (options1Items.get(i).getId() == top_city) {
                city1 = options1Items.get(i).getCity_name();
                return i;
            }

        }
        return 0;
    }

    private void showHuXingPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                shi = options1 + 1;
                wei = options3 + 1;
                ting = options2 + 1;
                String tx = shiList.get(options1) + tingList.get(options2) + weiList.get(options3);
                slHuxing.setText(tx);
//                Toast.makeText(getActivity(), tx, Toast.LENGTH_SHORT).show();

            }
        })

                .setTitleText("户型选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setNPicker(shiList, tingList, weiList);//三级选择器
        pvOptions.show();
    }

    private void loadCity() {
        HttpManager.post(HttpManager.CITYS)
                .params("token", UserInfoUtils.getInstance().getToken())
                .execute(new SimpleCallBack<List<CityBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<CityBean> datas) {
                        initJsonData(datas);
                        if (!TextUtils.isEmpty(tenant_id)) {
                            loadOldData();
                        }
                        slCity.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                KeyBoardUtil.hideKeyBoard(getActivity());
                                showCityPickerView();
                            }
                        });
                    }
                });
    }

    private void initJsonData(List<CityBean> datas) {
        handleEmpty(datas);
        ArrayList<CityBean> jsonBean = (ArrayList<CityBean>) datas;

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历市
            ArrayList<CityBean.ChildCityBean> quList = new ArrayList<>();//该市的地区（第二级）
            ArrayList<ArrayList<CityBean.ChildCityBean.ChildAreaBean>> areaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getChild_city().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getChild_city().get(c).getCity_name();
                CityBean.ChildCityBean childCityBean = jsonBean.get(i).getChild_city().get(c);
                quList.add(childCityBean);//添加城市
                ArrayList<CityBean.ChildCityBean.ChildAreaBean> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getChild_city().get(c).getChild_area());
                areaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(quList);

            /**
             * 添加地区数据
             */
            options3Items.add(areaList);
        }

    }

    private void handleEmpty(List<CityBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            CityBean cityBean = datas.get(i);
            if (cityBean.getChild_city().size() == 0) {
                cityBean.getChild_city().add(CityBean.newChildBean());
            }
            for (int j = 0; j < cityBean.getChild_city().size(); j++) {
                if (cityBean.getChild_city().get(j).getChild_area().size() == 0) {
                    cityBean.getChild_city().get(j).getChild_area().add(CityBean.newAreaBean());
                }
            }
        }
    }

    private void showCityPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置

                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getCity_name() : "";
                topId = options1Items.size() > 0 ?
                        options1Items.get(options1).getId() : -1;

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2).getCity_name() : "";
                cityId = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2).getId() : -1;

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3).getArea_name() : "";
                areaId = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3).getId() : -1;

                String tx = opt1tx + opt2tx + opt3tx;
                slCity.setText(tx);
//                Toast.makeText(getActivity(), tx, Toast.LENGTH_SHORT).show();

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void loadData() {
        if (TextUtils.isEmpty(etZujinLow.getText()) || TextUtils.isEmpty(etZujinHigh.getText()) || TextUtils.isEmpty(etZujinLow.getText()) || TextUtils.isEmpty(etMore.getText())) {
            T.showShort(getActivity(), "请填写完整信息");
            return;
        }
        int min = Integer.valueOf(etZujinLow.getText().toString());
        int max = Integer.valueOf(etZujinHigh.getText().toString());
        if (min >= max) {
            T.showShort(getActivity(), "请确保最低租金要比最高租金低");
            return;
        }
        if (!checkEmptyInfo()) {
            return;
        }
        PostRequest request = HttpManager.post(HttpManager.ZUKEXINXI_TWO)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("top_city", topId + "")
                .params("city_id", cityId + "")
                .params("area_id", areaId + "")
                .params("rent_min", etZujinLow.getText().toString().trim())
                .params("rent_max", etZujinHigh.getText().toString().trim())
                .params("room", shi + "")
                .params("office", ting + "")
                .params("toilet", wei + "")
                .params("people_num", slRuzhurenshu.getText().trim())
                .params("checkin_time", slRuzhushijian.getText().trim())
                .params("desc", etMore.getText().toString().trim());
        if (!TextUtils.isEmpty(id)) {
            request.params("id", id);
        }
        if (!TextUtils.isEmpty(tenant_id)) {
            request.params("id", tenant_id);
        }

        request.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String data) {
                T.showShort(getBaseContext(), "提交成功");
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }


}
