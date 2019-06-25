package luyuan.tech.com.chaoke.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/15
 * @describe:
 */


public class LoginMIMaFragment extends Fragment {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_mima, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etPhone.setText("18723836080");
        etPwd.setText("123456");
        initListener();
    }

    private void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpManager.post(HttpManager.LOGIN)
                        .params("role","2")
                        .params("login_type","1")
                        .params("phone",etPhone.getText().toString().trim())
                        .params("password",etPwd.getText().toString().trim())
                        .execute(new SimpleCallBack<LoginBean>() {

                            @Override
                            public void onError(ApiException e) {
                                T.showShort(getContext(),e.getMessage());
                            }

                            @Override
                            public void onSuccess(LoginBean loginBean) {
                                EventBus.getDefault().post(new LoginEvent());
                                T.showShort(getContext(),"登录成功");
                                UserInfoUtils.getInstance().updateUserInfo(loginBean);
                                getActivity().finish();
                            }
                        });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
