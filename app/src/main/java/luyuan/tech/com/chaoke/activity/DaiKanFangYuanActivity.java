package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.AreaShaiXuanAdapter;
import luyuan.tech.com.chaoke.adapter.CityShaiXuanAdapter;
import luyuan.tech.com.chaoke.adapter.KaiFaFangYuanAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.bean.ShaiXuanCityBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DownMenuButton;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class DaiKanFangYuanActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.quyu_button)
    DownMenuButton quyuButton;
    @BindView(R.id.zujin_button)
    DownMenuButton zujinButton;
    @BindView(R.id.paixu_button)
    DownMenuButton paixuButton;
    @BindView(R.id.shaixuan_button)
    DownMenuButton shaixuanButton;
    @BindView(R.id.pop_quyu)
    LinearLayout llquyu;
    @BindView(R.id.pop_zujin)
    LinearLayout llzujin;
    @BindView(R.id.pop_paixu)
    LinearLayout llpaixu;
    @BindView(R.id.pop_shaixuan)
    LinearLayout llshaixuan;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.recycler_left)
    RecyclerView recyclerLeft;
    @BindView(R.id.recycler_right)
    RecyclerView recyclerRight;
    @BindView(R.id.rl_container)
    LinearLayout rlContainer;
    @BindView(R.id.tv_zujinbuxian)
    TextView tvZujinbuxian;
    @BindView(R.id.tv_zujin_1500)
    TextView tvZujin1500;
    @BindView(R.id.tv_zujin_2000)
    TextView tvZujin2000;
    @BindView(R.id.tv_zujin_2500)
    TextView tvZujin2500;
    @BindView(R.id.tv_zujin_4000)
    TextView tvZujin4000;
    @BindView(R.id.tv_zujin_4000_high)
    TextView tvZujin4000High;
    @BindView(R.id.et_from)
    EditText etFrom;
    @BindView(R.id.et_to)
    EditText etTo;
    @BindView(R.id.btn_zujin)
    Button btnZujin;
    @BindView(R.id.tv_paixu_buxian)
    TextView tvPaixuBuxian;
    @BindView(R.id.tv_paixu_zujinhigh)
    TextView tvPaixuZujinhigh;
    @BindView(R.id.tv_paixu_zujinlow)
    TextView tvPaixuZujinlow;
    @BindView(R.id.tv_paixu_mianjihigh)
    TextView tvPaixuMianjihigh;
    @BindView(R.id.tv_paixu_mianjilow)
    TextView tvPaixuMianjilow;
    @BindView(R.id.btn_shaixuan)
    TextView btnShaixuan;
    private ArrayList<HouseBean> list = new ArrayList<>();
    private ArrayList<ShaiXuanCityBean> cityList = new ArrayList<>();
    private ArrayList<ShaiXuanCityBean.AreaListBean> areaList = new ArrayList<>();
    private String areaId;
    private String priceLow;
    private String priceHigh;
    private String paixu;//1为租金最低 2为租金最高 3为面积最小 4为面积最大
    private String rent_state;//房源状态为业主待租 2为非自营在租 3为业主自用

    private KaiFaFangYuanAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daikan_fangyuan);
        ButterKnife.bind(this);
        initView();
        loadData();
        loadCityData();
        quyuButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                llquyu.setVisibility(checked ? View.VISIBLE : View.GONE);
                if (checked){
                    llzujin.setVisibility(View.GONE);
                    llpaixu.setVisibility(View.GONE);
                    llshaixuan.setVisibility(View.GONE);
                }
            }
        });
        zujinButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                llzujin.setVisibility(checked ? View.VISIBLE : View.GONE);
                if (checked){
                    llpaixu.setVisibility(View.GONE);
                    llquyu.setVisibility(View.GONE);
                    llshaixuan.setVisibility(View.GONE);
                }

            }
        });
        paixuButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                llpaixu.setVisibility(checked ? View.VISIBLE : View.GONE);
                if (checked){
                    llzujin.setVisibility(View.GONE);
                    llquyu.setVisibility(View.GONE);
                    llshaixuan.setVisibility(View.GONE);
                }
            }
        });
        shaixuanButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                llshaixuan.setVisibility(checked ? View.VISIBLE : View.GONE);
                if (checked){
                    llzujin.setVisibility(View.GONE);
                    llquyu.setVisibility(View.GONE);
                    llpaixu.setVisibility(View.GONE);
                }
            }
        });
        tvZujinbuxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zujinButton.setChecked(false);
                priceLow = "";
                priceHigh = "";
            }
        });
        tvZujin1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zujinButton.setChecked(false);
                priceLow = "0";
                priceHigh = "1500";
            }
        });
        tvZujin2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zujinButton.setChecked(false);
                priceLow = "1500";
                priceHigh = "2000";
            }
        });
        tvZujin2500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zujinButton.setChecked(false);
                priceLow = "2000";
                priceHigh = "2500";
            }
        });
        tvZujin4000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zujinButton.setChecked(false);
                priceLow = "2500";
                priceHigh = "4000";
            }
        });
        tvZujin4000High.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zujinButton.setChecked(false);
                priceLow = "4000";
                priceHigh = "999999";
            }
        });
        btnZujin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zujinButton.setChecked(false);
                if (!TextUtils.isEmpty(etTo.getText().toString().trim()) && !TextUtils.isEmpty(etFrom.getText().toString().trim())) {
                    priceHigh = etTo.getText().toString().trim();
                    priceLow = etFrom.getText().toString().trim();
                }
            }
        });
        tvPaixuBuxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paixuButton.setChecked(false);
                paixu = "";
            }
        });
        tvPaixuMianjihigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paixuButton.setChecked(false);
                paixu = "4";
            }
        });
        tvPaixuMianjilow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paixuButton.setChecked(false);
                paixu = "3";
            }
        });
        tvPaixuZujinhigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paixuButton.setChecked(false);
                paixu = "2";
            }
        });
        tvPaixuZujinlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paixuButton.setChecked(false);
                paixu = "1";
            }
        });
        btnShaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shaixuanButton.setChecked(false);
            }
        });

    }

    private void loadCityData() {
        HttpManager.post(HttpManager.CHENGSHI_LIST)
                .params("token", UserInfoUtils.getInstance().getToken())
                .execute(new SimpleCallBack<List<ShaiXuanCityBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<ShaiXuanCityBean> data) {
                        cityList.clear();
                        cityList.addAll(data);
                        recyclerLeft.setLayoutManager(new LinearLayoutManager(getContext()));
                        CityShaiXuanAdapter adapterLeft = new CityShaiXuanAdapter(data);
                        recyclerLeft.setAdapter(adapterLeft);

                        final AreaShaiXuanAdapter adapterRight = new AreaShaiXuanAdapter(areaList);
                        recyclerRight.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerRight.setAdapter(adapterRight);

                        adapterRight.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                quyuButton.setChecked(false);
                                llquyu.setVisibility(View.GONE);
                                areaId = adapterRight.getData().get(position).getArea_id() + "";
                            }
                        });
                        adapterLeft.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                List<ShaiXuanCityBean.AreaListBean> areaBeanList = cityList.get(position).getArea_list();
                                areaList.clear();
                                areaList.addAll(areaBeanList);
                                adapterRight.notifyDataSetChanged();
                            }
                        });
                    }
                });
    }

    private void loadData() {
        // TODO: 2019/6/18  选择带看房源接口
        PostRequest request = HttpManager.post(HttpManager.HOUSE_LIST)
                .params("token", UserInfoUtils.getInstance().getToken());
        if (!TextUtils.isEmpty(areaId)) {
            request.params("area_id", areaId);
        }
        if (!TextUtils.isEmpty(priceLow) && !TextUtils.isEmpty(priceHigh)) {
            request.params("price_min", priceLow).params("price_max", priceHigh);
        }
        if (!TextUtils.isEmpty(paixu)) {
            request.params("order_by", paixu);
        }
        if (!TextUtils.isEmpty(rent_state)) {
            request.params("rent_state", rent_state);
        }
        request.execute(new SimpleCallBack<List<HouseBean>>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getContext(), e.getMessage());
            }

            @Override
            public void onSuccess(List<HouseBean> data) {
                list.clear();
                list.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new KaiFaFangYuanAdapter(list);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getBaseContext(), XianChangDaiKanActivity.class);
                HouseBean houseBean = list.get(position);
                intent.putExtra("id", houseBean.getId() + "");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
