package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.AppStorageUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/18
 * @describe:
 */


public class XiuGaiZhuangTaiActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rb0)
    RadioButton rb0;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.sl_shixiaoliyou)
    SelectLayout slShixiaoliyou;
    @BindView(R.id.btn_next)
    Button btnNext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai_zhuangtai);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        String[] arr = {"没有客户想要的房子","不在服务状态内","客户不诚心","跟其他人重客","客户是同行","其他理由"};
        setSelectLListener(slShixiaoliyou,arr,"失效理由");
        rb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    rb1.setChecked(false);
                    rb2.setChecked(false);
                    rb3.setChecked(false);
                }
            }
        });
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    rb0.setChecked(false);
                    rb2.setChecked(false);
                    rb3.setChecked(false);
                }
            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    rb1.setChecked(false);
                    rb0.setChecked(false);
                    rb3.setChecked(false);
                }
            }
        });
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    rb1.setChecked(false);
                    rb2.setChecked(false);
                    rb0.setChecked(false);
                }
            }
        });
    }

    private void loadData() {
        int status = 1;
        if (rb1.isChecked()){
            status = 2;
        }else if (rb2.isChecked()){
            status = 3;
        }else if (rb3.isChecked()){
            status = 4;
        }
        HttpManager.post(HttpManager.CHANGE_STATE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("tenant_id", AppStorageUtils.getZuKeDetailBean().getId()+"")
                .params("status",status+"")
                .params("reason",((int)slShixiaoliyou.getTag()+"")+"")
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
