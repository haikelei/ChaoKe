package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HeTongDetailBean;
import luyuan.tech.com.chaoke.bean.HeTongIdBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class HeToneXinXiFiveActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_wuyedizhi)
    TextView tvWuyedizhi;
    @BindView(R.id.tv_chuzudizhi)
    TextView tvChuzudizhi;
    @BindView(R.id.tv_zukexingming)
    TextView tvZukexingming;
    @BindView(R.id.tv_shoujihaoma)
    TextView tvShoujihaoma;
    @BindView(R.id.tv_qianyueshijian)
    TextView tvQianyueshijian;
    @BindView(R.id.tv_qianyuezhuangtai)
    TextView tvQianyuezhuangtai;
    private HeTongIdBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hetong_xinxi_five);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            bean = (HeTongIdBean) getIntent().getSerializableExtra("data");
        }
        loadData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }

    private void loadData() {
        HttpManager.post(HttpManager.HETONG_DETAIL)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("con_id", bean.getCon_id())
                .params("cons_id", bean.getCons_id())
                .execute(new SimpleCallBack<HeTongDetailBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HeTongDetailBean data) {
                        fillData(data);
                    }
                });
    }

    private void fillData(HeTongDetailBean data) {
        tvWuyedizhi.setText(data.getAddress());
//        tvChuzudizhi.getText()
        tvZukexingming.setText(data.getUsername());
        tvShoujihaoma.setText(data.getPhone());
        tvQianyueshijian.setText(data.getSubtime());
        tvQianyuezhuangtai.setText(data.getState()+"");
    }

}
