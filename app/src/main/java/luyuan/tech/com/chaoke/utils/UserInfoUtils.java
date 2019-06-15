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

    public void setToken(String userId) {
        helper.put("token",userId);
    }



    public void updateUserInfo(LoginBean bean){
        setToken(bean.getToken());
    }

    public void logout() {
        setToken("");
    }
}
