package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HeTongXiangQingBean;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class HeToneXiangQingActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_wuyedizhi)
    TextView tvWuyedizhi;
    @BindView(R.id.tv_fangdongxingming)
    TextView tvFangdongxingming;
    @BindView(R.id.tv_shoujihaoma)
    TextView tvShoujihaoma;
    @BindView(R.id.tv_qianyueshijian)
    TextView tvQianyueshijian;
    @BindView(R.id.tv_qianyuezhuangtai)
    TextView tvQianyuezhuangtai;
    @BindView(R.id.iv_erweima)
    ImageView ivErweima;
    private HeTongXiangQingBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hetong_xiangqing);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            bean = (HeTongXiangQingBean) getIntent().getSerializableExtra("data");
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (bean!=null){
            tvWuyedizhi.setText(bean.getWuye_address());
            tvFangdongxingming.setText(bean.getLandlady_name());
            tvQianyueshijian.setText(bean.getSigning_time());
            tvShoujihaoma.setText(bean.getLandlady_phone());
            Glide.with(getActivity()).load(bean.getCode()).into(ivErweima);
        }


    }

    @Override
    public void onBackPressed() {
        showSuccess();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }


}
