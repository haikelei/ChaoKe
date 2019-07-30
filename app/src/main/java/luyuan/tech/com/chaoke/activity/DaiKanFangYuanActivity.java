package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
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
import luyuan.tech.com.chaoke.adapter.KaiFaFangYuanAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.CityBean;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.SettingManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DownMenuButton;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.PaiXuPopup;
import luyuan.tech.com.chaoke.widget.QuYuPopup;
import luyuan.tech.com.chaoke.widget.ShaiXuanPopup;
import luyuan.tech.com.chaoke.widget.ZuJinPopup;
import razerdp.basepopup.BasePopupWindow;

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
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.rl_container)
    LinearLayout rlContainer;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.recycler_left)
    RecyclerView recyclerLeft;
    @BindView(R.id.recycler_center)
    RecyclerView recyclerCenter;
    @BindView(R.id.recycler_right)
    RecyclerView recyclerRight;
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
    @BindView(R.id.rent_state1)
    CheckBox rentState1;
    @BindView(R.id.rent_state2)
    CheckBox rentState2;
    @BindView(R.id.rent_state3)
    CheckBox rentState3;
    @BindView(R.id.container_rent_state)
    GridLayout containerRentState;
    @BindView(R.id.fit_up1)
    CheckBox fitUp1;
    @BindView(R.id.fit_up2)
    CheckBox fitUp2;
    @BindView(R.id.fit_up3)
    CheckBox fitUp3;
    @BindView(R.id.fit_up4)
    CheckBox fitUp4;
    @BindView(R.id.fit_up5)
    CheckBox fitUp5;
    @BindView(R.id.container_fit_up)
    GridLayout containerFitUp;
    @BindView(R.id.orientation1)
    CheckBox orientation1;
    @BindView(R.id.orientation2)
    CheckBox orientation2;
    @BindView(R.id.orientation3)
    CheckBox orientation3;
    @BindView(R.id.orientation4)
    CheckBox orientation4;
    @BindView(R.id.container_orientation)
    GridLayout containerOrientation;
    @BindView(R.id.source1)
    CheckBox source1;
    @BindView(R.id.source2)
    CheckBox source2;
    @BindView(R.id.source3)
    CheckBox source3;
    @BindView(R.id.source4)
    CheckBox source4;
    @BindView(R.id.source5)
    CheckBox source5;
    @BindView(R.id.source6)
    CheckBox source6;
    @BindView(R.id.source8)
    CheckBox source8;
    @BindView(R.id.source9)
    CheckBox source9;
    @BindView(R.id.source10)
    CheckBox source10;
    @BindView(R.id.container_source)
    GridLayout containerSource;
    @BindView(R.id.has_pic1)
    CheckBox hasPic1;
    @BindView(R.id.has_pic2)
    CheckBox hasPic2;
    @BindView(R.id.container_has_pic)
    GridLayout containerHasPic;
    @BindView(R.id.input_floor_min)
    InputLayout inputFloorMin;
    @BindView(R.id.input_floor_max)
    InputLayout inputFloorMax;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private ArrayList<HouseBean> list = new ArrayList<>();
    private KaiFaFangYuanAdapter adapter;
    private String zujinLow;
    private String zujinHigh;
    private String orderBy;
    private String areaId;
    private String rent_state;
    private String fit_up;
    private String orientation;
    private String source;
    private String hasPic;
    private String floorMin;
    private String floorMax;
    private ZuJinPopup zuJinPopup;
    private PaiXuPopup paiXuPopup;
    private ShaiXuanPopup shaiXuanPopup;
    private QuYuPopup quYuPopup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daikan_fangyuan);
        ButterKnife.bind(this);
        initPopup();
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),HouseSearchActivity.class));
            }
        });
        quyuButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChage(View view, boolean checked) {
                if (checked) {
                    quYuPopup.showPopupWindow(view);
                }
            }
        });
        zujinButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                if (checked) {
                    zuJinPopup.showPopupWindow(view);
                }
            }
        });
        paixuButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                if (checked) {
                    paiXuPopup.showPopupWindow(view);
                }
            }
        });
        shaixuanButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                if (checked) {
                    shaiXuanPopup.showPopupWindow(view);
                }
            }
        });
        initView();
        loadData();


    }

    private void initPopup() {
//        区域
        quYuPopup = new QuYuPopup(getActivity());
        quYuPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
        quYuPopup.setOnQuYuSelectListener(new QuYuPopup.OnQuYuSelectListener() {
            @Override
            public void onQuYuSelect(CityBean.ChildCityBean.ChildAreaBean bean) {
                areaId = bean.getId() + "";
            }
        });
        quYuPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                quyuButton.setChecked(false);
                loadData();
            }
        });
//        租金
        zuJinPopup = new ZuJinPopup(getActivity());
        zuJinPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
        zuJinPopup.setOnZuJinListener(new ZuJinPopup.OnZuJinListener() {
            @Override
            public void onZuJinSelected(String low, String high) {
                zujinLow = low;
                zujinHigh = high;
            }
        });
        zuJinPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                zujinButton.setChecked(false);
                loadData();
            }
        });
//        排序
        paiXuPopup = new PaiXuPopup(getActivity());
        paiXuPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
        paiXuPopup.setOnPaiXuListener(new PaiXuPopup.OnPaiXuListener() {
            @Override
            public void onZuJinSelected(String index) {
                orderBy = index;
            }
        });
        paiXuPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                paixuButton.setChecked(false);
                loadData();
            }
        });
//        筛选
        shaiXuanPopup = new ShaiXuanPopup(getActivity());
        shaiXuanPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
        shaiXuanPopup.setOnShaiXuanSelectListener(new ShaiXuanPopup.OnShaiXuanSelectListener() {
            @Override
            public void onConfirm(String source, String orientation, String fitUp, String rentState, String hasPic, String floormin, String floormax) {
                DaiKanFangYuanActivity.this.source = source;
                DaiKanFangYuanActivity.this.fit_up = fitUp;
                DaiKanFangYuanActivity.this.rent_state = rentState;
                DaiKanFangYuanActivity.this.hasPic = hasPic;
                DaiKanFangYuanActivity.this.floorMin = floormin;
                DaiKanFangYuanActivity.this.floorMax = floorMax;
            }
        });
        shaiXuanPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                shaixuanButton.setChecked(false);
                loadData();
            }
        });
    }


    private void loadData() {
        PostRequest request = HttpManager.post(HttpManager.HOUSE_LIST)
                .params("type", "2")
                .params("is_dai","1")
                .params("token", UserInfoUtils.getInstance().getToken());
        if (!TextUtils.isEmpty(zujinLow) && !TextUtils.isEmpty(zujinHigh)) {
            request.params("price_min", zujinLow).params("price_max", zujinHigh);
        }
        if (!TextUtils.isEmpty(orderBy)) {
            request.params("order_by", orderBy);
        }
        if (!TextUtils.isEmpty(areaId)) {
            request.params("area_id", areaId);
        }
        if (!TextUtils.isEmpty(source)) {
            request.params("source", source);
        }
        if (!TextUtils.isEmpty(orientation)) {
            request.params("orientation", orientation);
        }
        if (!TextUtils.isEmpty(fit_up)) {
            request.params("fit_up", fit_up);
        }
        if (!TextUtils.isEmpty(rent_state)) {
            request.params("rent_state", rent_state);
        }
        if (!TextUtils.isEmpty(hasPic)) {
            request.params("has_pic", hasPic);
        }
        if (!TextUtils.isEmpty(floorMin)) {
            request.params("floor_min", floorMin);
        }
        if (!TextUtils.isEmpty(hasPic)) {
            request.params("floor_max", floorMax);
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
