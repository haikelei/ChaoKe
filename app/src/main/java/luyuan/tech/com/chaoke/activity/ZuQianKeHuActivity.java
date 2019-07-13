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
import luyuan.tech.com.chaoke.adapter.ZuQianKeHuAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.CityBean;
import luyuan.tech.com.chaoke.bean.ZuKeListBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DownMenuButton;
import luyuan.tech.com.chaoke.widget.PaiXuPopup;
import luyuan.tech.com.chaoke.widget.QuYuPopup;
import luyuan.tech.com.chaoke.widget.ShaiXuanPopup;
import luyuan.tech.com.chaoke.widget.ZuJinPopup;
import luyuan.tech.com.chaoke.widget.ZuKePaiXuPopup;
import luyuan.tech.com.chaoke.widget.ZuKeShaiXuanPopup;
import razerdp.basepopup.BasePopupWindow;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ZuQianKeHuActivity extends BaseActivity {
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
    private ArrayList<ZuKeListBean> list = new ArrayList<>();
    private ZuQianKeHuAdapter adapter;
    private String zujinLow;
    private String zujinHigh;
    private String orderBy;
    private String areaId;
    private String status;
    private String grade;
    private String sex;
    private String source;
    private ZuJinPopup zuJinPopup;
    private ZuKePaiXuPopup paiXuPopup;
    private ZuKeShaiXuanPopup shaiXuanPopup;
    private QuYuPopup quYuPopup;
    private String room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuqian_kehu);
        ButterKnife.bind(this);
        initPopup();
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
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KeHuSearchActivity.class));
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ZuKeXinXiActivity.class));
            }
        });
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
        paiXuPopup = new ZuKePaiXuPopup(getActivity());
        paiXuPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
        paiXuPopup.setOnPaiXuListener(new ZuKePaiXuPopup.OnPaiXuListener() {
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
        shaiXuanPopup = new ZuKeShaiXuanPopup(getActivity());
        shaiXuanPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
        shaiXuanPopup.setOnShaiXuanSelectListener(new ZuKeShaiXuanPopup.OnShaiXuanSelectListener() {
            @Override
            public void onSource(String s) {
                source = s;
            }

            @Override
            public void onStatus(String s) {
                status = s;
            }

            @Override
            public void onGrade(String s) {
                grade = s;
            }

            @Override
            public void onSex(String s) {
                sex = s;
            }

            @Override
            public void onRoom(String s) {
                room = s;
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
        PostRequest request = HttpManager.post(HttpManager.ZUKE_LIST)
                .params("token", UserInfoUtils.getInstance().getToken());
        if (!TextUtils.isEmpty(zujinLow) && !TextUtils.isEmpty(zujinHigh)) {
            request.params("rent_min", zujinLow).params("rent_max", zujinHigh);
        }
        if (!TextUtils.isEmpty(orderBy)) {
            request.params("order", orderBy);
        }
        if (!TextUtils.isEmpty(areaId)) {
            request.params("area_id", areaId);
        }
        if (!TextUtils.isEmpty(source)) {
            request.params("source", source);
        }
        if (!TextUtils.isEmpty(sex)) {
            request.params("sex", sex);
        }
        if (!TextUtils.isEmpty(grade)) {
            request.params("grade", grade);
        }
        if (!TextUtils.isEmpty(status)) {
            request.params("status", status);
        }if (!TextUtils.isEmpty(room)) {
            request.params("room", room);
        }
        request.execute(new SimpleCallBack<List<ZuKeListBean>>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getContext(), e.getMessage());
            }

            @Override
            public void onSuccess(List<ZuKeListBean> data) {
                list.clear();
                list.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new ZuQianKeHuAdapter(list);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getBaseContext(), ZuKeDetailActivity.class);
                ZuKeListBean bean = list.get(position);
                intent.putExtra("id", bean.getId() + "");
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
