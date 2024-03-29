package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DownMenuButton;
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


public class KaiFaFangYuanActivity extends BaseActivity {
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
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rl_container)
    LinearLayout rlContainer;
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
        setContentView(R.layout.activity_kaifa_fangyuan);
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

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(), AddHouseActivity.class),175);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        shaiXuanPopup.setOutSideDismiss(false);
        shaiXuanPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
        shaiXuanPopup.setOnShaiXuanSelectListener(new ShaiXuanPopup.OnShaiXuanSelectListener() {
            @Override
            public void onConfirm(String source, String orientation, String fitUp, String rentState, String hasPic, String floormin, String floormax) {
                KaiFaFangYuanActivity.this.source = source;
                KaiFaFangYuanActivity.this.fit_up = fitUp;
                KaiFaFangYuanActivity.this.rent_state = rentState;
                KaiFaFangYuanActivity.this.hasPic = hasPic;
                KaiFaFangYuanActivity.this.floorMin = floormin;
                KaiFaFangYuanActivity.this.floorMax = floormax;
                KaiFaFangYuanActivity.this.orientation = orientation;
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

        if (!TextUtils.isEmpty(hasPic)){
            request.params("has_pic",hasPic);
        }
        if (!TextUtils.isEmpty(floorMin)){
            request.params("floor_min",floorMin);
        }
        if (!TextUtils.isEmpty(floorMax)){
            request.params("floor_max",floorMax);
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
                Intent intent = new Intent(getBaseContext(), FangYuanXiangQingActivity.class);
                intent.putExtra("id", list.get(position).getId() + "");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==175){
            loadData();
        }
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
