package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.LoginBean;
import luyuan.tech.com.chaoke.event.LoginEvent;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.CountDownTimerUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @BindView(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        tvYanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadYanZhengMa();
            }
        });
    }

    private void loadYanZhengMa() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            T.showShort(getActivity(),"请输入手机号");
            return;
        }
        HttpManager.post(HttpManager.YANZHENGMA)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("phone",phone)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getActivity(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(String data) {
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tvYanzhengma, 60000, 1000);
                        mCountDownTimerUtils.start();
                    }
                });
    }

    private void loadData() {
        String phone = etPhone.getText().toString().trim();
        String yanzhengma = etYanzhengma.getText().toString().trim();
        String pwd = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            T.showShort(getActivity(),"请输入手机号");
            return;
        }else if (TextUtils.isEmpty(yanzhengma)){
            T.showShort(getActivity(),"请输入验证码");
            return;
        }else if (TextUtils.isEmpty(pwd)){
            T.showShort(getActivity(),"请输入密码");
            return;
        }
        HttpManager.post(HttpManager.CHANGE_PWD)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("phone", phone)
                .params("code", yanzhengma)
                .params("password",pwd)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getActivity(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        if (NetParser.isOk(s)){
                            T.showShort(getActivity(), "修改成功");
                            getActivity().finish();
                        }else {
                            T.showShort(getActivity(), "修改失败");
                        }

                    }
                });
    }

}
