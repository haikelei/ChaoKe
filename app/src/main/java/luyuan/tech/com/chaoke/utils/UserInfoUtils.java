package luyuan.tech.com.chaoke.utils;

import android.text.TextUtils;

import luyuan.tech.com.chaoke.App;
import luyuan.tech.com.chaoke.bean.LoginBean;

/**
 * @author: lujialei
 * @date: 2019/6/15
 * @describe:
 */


public class UserInfoUtils {
    private static UserInfoUtils instance;
    private SharedPreferencesHelper helper;
    private UserInfoUtils(){
        helper = new SharedPreferencesHelper(App.getAppContext(),"chaoke_user_info");
    }

    public static UserInfoUtils getInstance(){
        if (instance==null){
            instance = new UserInfoUtils();
        }
        return instance;
    }

    public boolean isLogin(){
        return !TextUtils.isEmpty(getToken());
    }

    public String getToken(){
        return (String) helper.getSharedPreference("token","");
    }

    public void setToken(String token) {
        helper.put("token",token);
    }

    public String getPhone(){
        return (String) helper.getSharedPreference("phone","");
    }

    public void setPhone(String phone) {
        helper.put("phone",phone);
    }





    public void updateUserInfo(LoginBean bean){
        setToken(bean.getToken());
        setPhone(bean.getPhone());
    }

    public void logout() {
        setToken("");
    }
}
