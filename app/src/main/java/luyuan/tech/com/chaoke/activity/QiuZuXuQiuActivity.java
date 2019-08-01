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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.MainActivity;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.FindCityBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.KeyBoardUtil;
import luyuan.tech.com.chaoke.utils.SettingManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class QiuZuXuQiuActivity extends BaseActivity {
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
    SelectLayout slRuzhurenshu;
    @BindView(R.id.sl_ruzhushijian)
    SelectLayout slRuzhushijian;
    @BindView(R.id.et_more)
    EditText etMore;
    @BindView(R.id.sl_huxing)
    SelectLayout slHuxing;
    @BindView(R.id.input_chenghu)
    InputLayout inputChenghu;
    private String id;
    private List<FindCityBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<FindCityBean.AreaListBean>> options2Items = new ArrayList<>();
    private ArrayList<String> shiList;
    private ArrayList<String> tingList;
    private ArrayList<String> weiList;
    private int shi, ting, wei;
    private int topId=-1;
    private int cityId=-1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiuzu_xuqiu);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
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
        String[] arr = {"1人", "2人", "3人", "4人", "5人", "6人"};
        setSelectLListener(slRuzhurenshu, arr);


        slHuxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyBoardUtil.hideKeyBoard(getActivity());
                showHuXingPickerView();
            }
        });
        loadCity();
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
        HttpManager.post(HttpManager.FIND_CITYS)
                .params("token", UserInfoUtils.getInstance().getToken())
                .execute(new SimpleCallBack<List<FindCityBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<FindCityBean> datas) {
                        initJsonData(datas);
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

    private void initJsonData(List<FindCityBean> datas) {
        handleEmpty(datas);
        ArrayList<FindCityBean> jsonBean = (ArrayList<FindCityBean>) datas;

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历市
            FindCityBean bean = jsonBean.get(i);
            ArrayList<FindCityBean.AreaListBean> areaListBeans = new ArrayList<>();
            for (int j = 0; j < bean.getArea_list().size(); j++) {
                areaListBeans.add(bean.getArea_list().get(j));
            }
            /**
             * 添加城市数据
             */
            options2Items.add(areaListBeans);

        }

    }

    private void handleEmpty(List<FindCityBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            FindCityBean cityBean = datas.get(i);
            if (cityBean.getArea_list().size() == 0) {
                cityBean.getArea_list().add(FindCityBean.newChildBean());
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
                        options2Items.get(options1).get(options2).getArea_name() : "";
                cityId = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2).getId() : -1;

                String tx = opt1tx + opt2tx;
                slCity.setText(tx);
//                Toast.makeText(getActivity(), tx, Toast.LENGTH_SHORT).show();

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items);//二级选择器
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
        if (topId==-1){
            T.showShort(getActivity(), "请选择城市");
            return;
        }
        if (cityId==-1){
            T.showShort(getActivity(), "请选择商圈");
            return;
        }
        HttpManager.post(HttpManager.QIUZUXUQIU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .params("city_id", topId + "")
                .params("area_id", cityId + "")
                .params("short_price", etZujinLow.getText().toString().trim())
                .params("long_price", etZujinHigh.getText().toString().trim())
                .params("room", shi + "")
                .params("office", ting + "")
                .params("guard", wei + "")
                .params("people", getValue(slRuzhurenshu))
                .params("setin_time", slRuzhushijian.getText().trim())
                .params("remark", etMore.getText().toString().trim())
                .params("nickname",inputChenghu.getText().toString())
                .execute(new SimpleCallBack<String>() {

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
