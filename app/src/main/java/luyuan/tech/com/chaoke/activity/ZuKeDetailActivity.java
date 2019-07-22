package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.GenJinAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.AppStorageUtils;
import luyuan.tech.com.chaoke.utils.Constant;
import luyuan.tech.com.chaoke.utils.SettingManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.GenJinPopup;
import luyuan.tech.com.chaoke.widget.ZuKeBianJiPopup;
import razerdp.basepopup.BasePopupWindow;

/**
 * @author: lujialei
 * @date: 2019/6/18
 * @describe:
 */


public class ZuKeDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.ll_qianyue_bottom)
    LinearLayout llQianyueBottom;
    @BindView(R.id.ll_daikan_bottom)
    LinearLayout llDaikanBottom;
    @BindView(R.id.ll_genjin_bottom)
    LinearLayout llGenjinBottom;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    @BindView(R.id.tv_quyu)
    TextView tvQuyu;
    @BindView(R.id.tv_zujin)
    TextView tvZujin;
    @BindView(R.id.tv_jushi)
    TextView tvJushi;
    @BindView(R.id.tv_zhuangxiu)
    TextView tvZhuangxiu;
    @BindView(R.id.tv_xiwangruzhuri)
    TextView tvXiwangruzhuri;
    @BindView(R.id.tv_renshu)
    TextView tvRenshu;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_leixing)
    TextView tvLeixing;
    @BindView(R.id.tv_youxiao)
    TextView tvYouxiao;
    @BindView(R.id.tv_bianhao)
    TextView tvBianhao;
    @BindView(R.id.tv_createtime)
    TextView tvCreatetime;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.ll_yuekan_bottom)
    LinearLayout llYuekanBottom;
    private String id;
    private List<ZuKeDetailBean.FollowDataBean> list;
    private GenJinAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuke_detail);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        list = new ArrayList<>();
        recycler.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                return false;
            }
        });
        //解决数据加载不完的问题
        recycler.setNestedScrollingEnabled(false);
        recycler.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        recycler.setFocusable(false);
        adapter = new GenJinAdapter(list);
        recycler.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        HttpManager.post(HttpManager.ZUKE_DETAIL)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<ZuKeDetailBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(ZuKeDetailBean data) {
                        fillData(data);
                    }
                });
    }

    private void fillData(ZuKeDetailBean data) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.moren_touxiang);
        Glide.with(this).load("").apply(requestOptions).into(ivAvatar);
        tvName.setText(data.getUsername());
        SettingManager.getInstance().setZuKeDetailBean(data);
        tvYouxiao.setText(data.getStatus() == 1 ? "有效" : "无效");
        tvCreatetime.setText(data.createtime);
        tvBianhao.setText("编号:" + data.getTenant_num() + "");
        tvQuyu.setText("区域:" + data.getCity_name());
        tvZujin.setText("租金:" + data.getRent_min());
        tvJushi.setText("居室:" + data.getRoom() + "室" + data.getOffice() + "厅" + data.getToilet() + "卫");
//        tvZhuangxiu.setText("装修:"+);
        tvXiwangruzhuri.setText("希望入住日:" + data.getCheckin_time());
        tvRenshu.setText("人数:" + data.getPeople_num());
        if (data.getFollow_data() != null) {
            list.clear();
            list.addAll(data.getFollow_data());
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.ll_qianyue_bottom, R.id.ll_daikan_bottom, R.id.ll_genjin_bottom,R.id.ll_yuekan_bottom, R.id.ll_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_edit:
                ZuKeBianJiPopup quYuPopup = new ZuKeBianJiPopup(getBaseContext());
                quYuPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
                quYuPopup.showPopupWindow(view);
                quYuPopup.setOnPopupClickListener(new ZuKeBianJiPopup.OnPopupClickListener() {
                    @Override
                    public void onZukexinxiClick(View view) {
                        Intent intent = new Intent(getBaseContext(), ZuKeXinXiActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onQiuzuxuqiu(View view) {

                    }

                    @Override
                    public void onXiugaizhuangtai(View view) {
                        Intent intent = new Intent(getBaseContext(), XiuGaiZhuangTaiActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.ll_qianyue_bottom:
                startActivity(new Intent(getBaseContext(), QianYueFangYuanActivity.class));
                break;
            case R.id.ll_daikan_bottom:
                startActivity(new Intent(getBaseContext(), DaiKanFangYuanActivity.class));
                break;
            case R.id.ll_genjin_bottom:
                GenJinPopup genJinPopup = new GenJinPopup(ZuKeDetailActivity.this, id);
                genJinPopup.setPopupGravity(Gravity.CENTER);
                genJinPopup.showPopupWindow();
                break;
            case R.id.ll_yuekan_bottom:
                startActivity(new Intent(getActivity(),XuanZeYueKanFangYuanActivity.class));
                break;
            case R.id.ll_call:
                callPhone("13800000000");
                break;
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
