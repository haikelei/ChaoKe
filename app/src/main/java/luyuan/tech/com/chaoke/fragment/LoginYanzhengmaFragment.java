package luyuan.tech.com.chaoke.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.LoginBean;
import luyuan.tech.com.chaoke.event.LoginEvent;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.CountDownTimerUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/15
 * @describe:
 */


public class LoginYanzhengmaFragment extends Fragment {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @BindView(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    @BindView(R.id.tv_wangjimima)
    TextView tvWangjimima;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_yanzhegnma, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        if (TextUtils.isEmpty(phone)){
            T.showShort(getActivity(),"请输入手机号");
            return;
        }else if (TextUtils.isEmpty(yanzhengma)){
            T.showShort(getActivity(),"请输入验证码");
            return;
        }
        HttpManager.post(HttpManager.LOGIN)
                .params("role", "2")
                .params("login_type", "2")
                .params("phone", phone)
                .params("code", yanzhengma)
                .execute(new SimpleCallBack<LoginBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        UserInfoUtils.getInstance().updateUserInfo(loginBean);
                        T.showShort(getContext(), "登录成功");
                        EventBus.getDefault().post(new LoginEvent());
                        getActivity().finish();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
