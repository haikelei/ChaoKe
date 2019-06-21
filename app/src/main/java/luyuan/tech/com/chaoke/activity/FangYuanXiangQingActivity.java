package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.NormalImageLoader;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class FangYuanXiangQingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_jushi)
    TextView tvJushi;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.tv_fangyuanzhuangtai)
    TextView tvFangyuanzhuangtai;
    @BindView(R.id.tv_chaoxiang)
    TextView tvChaoxiang;
    @BindView(R.id.tv_zhuangxiuqingkuang)
    TextView tvZhuangxiuqingkuang;
    @BindView(R.id.tv_louceng)
    TextView tvLouceng;
    @BindView(R.id.tv_wuyeyongtu)
    TextView tvWuyeyongtu;
    @BindView(R.id.tv_fankangzhuangtai)
    TextView tvFankangzhuangtai;
    @BindView(R.id.ll_shoufang_bottom)
    LinearLayout llShoufangBottom;
    @BindView(R.id.ll_qianyue_bottom)
    LinearLayout llQianyueBottom;
    @BindView(R.id.ll_daikan_bottom)
    LinearLayout llShikanBottom;
    @BindView(R.id.ll_genjin_bottom)
    LinearLayout llGenjinBottom;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuan_xiangqing);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        initView();
        loadData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        llShoufangBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),ShouFangShenPiActivity.class));
            }
        });
        llQianyueBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),FangYuanQianYueOneActivity.class));
            }
        });
        llShikanBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),ShiKanActivity.class));
            }
        });
        llGenjinBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),GenJinActivity.class));
            }
        });
        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone("18888888888");
            }
        });

    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    private void loadData() {
        HttpManager.post(HttpManager.HOUSE_DETAIL)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<HouseDetailBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HouseDetailBean data) {
                        fillData(data);
                    }
                });
    }

    private void fillData(HouseDetailBean data) {
        if (data.getPics() != null) {
            banner.setImages(data.getPics());
            banner.setImageLoader(new NormalImageLoader());
        }
        if (!TextUtils.isEmpty(data.getRoom_name())) {
            tvName.setText(data.getRoom_name());
        }
        if (!TextUtils.isEmpty(data.getLong_price())) {
            tvMoney.setText(data.getLong_price());
        }
        if (!TextUtils.isEmpty(data.getApartment())) {
            tvJushi.setText(data.getApartment());
        }
        if (!TextUtils.isEmpty(data.getArea())) {
            tvSize.setText(data.getArea());
        }
//        tvFangyuanzhuangtai.setText();
        tvChaoxiang.setText(data.getOrientation() + "");
//        tvZhuangxiuqingkuang.setText();
        if (!TextUtils.isEmpty(data.getFloor())) {
            tvLouceng.setText(data.getFloor());
        }
//        tvWuyeyongtu.setText();
//        tvFankangzhuangtai.setText();


    }

    private void initView() {
    }
}
