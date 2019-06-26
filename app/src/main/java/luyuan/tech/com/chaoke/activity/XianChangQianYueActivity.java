package luyuan.tech.com.chaoke.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;

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


public class XianChangQianYueActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
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
    private String id;
    private HouseDetailBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xianchang_daikan);
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
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HeToneXinXiOneActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
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
                        bean = data;
                        fillData(data);
                    }
                });
    }

    private void fillData(HouseDetailBean data) {
        if (data.getPics()!=null){
            banner.setImages(data.getPics());
            banner.setImageLoader(new NormalImageLoader());
        }
        if (!TextUtils.isEmpty(data.getRoom_name())){
            tvName.setText(data.getRoom_name());
        }
        if (!TextUtils.isEmpty(data.getLong_price())){
            tvMoney.setText(data.getLong_price());
        }
        if (!TextUtils.isEmpty(data.getApartment())){
            tvJushi.setText(data.getApartment());
        }
        if (!TextUtils.isEmpty(data.getArea())){
            tvSize.setText(data.getArea());
        }
//        tvFangyuanzhuangtai.setText();
        //朝向 1朝南 2为朝北 3为朝东 4为朝西
        int ori = data.getOrientation();
        String s = "朝南";
        if (ori==1){
            s = "朝南";
        }else if (ori==2){
            s = "朝北";
        }else if (ori==3){
            s = "朝东";
        }else if (ori==4){
            s = "朝西";
        }
        tvChaoxiang.setText("朝向:"+s);
//        tvZhuangxiuqingkuang.setText();
        if (!TextUtils.isEmpty(data.getFloor())){
            tvLouceng.setText("楼层:"+data.getFloor());
        }
//        tvWuyeyongtu.setText();
//        tvFankangzhuangtai.setText();


    }

    private void initView() {
    }
}
