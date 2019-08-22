package luyuan.tech.com.chaoke.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

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

    public String getAvatar(){
        return (String) helper.getSharedPreference("avatar","");
    }

    public void setAvatar(String avatar) {
        helper.put("avatar",avatar);
    }


    public String getUserName(){
        return (String) helper.getSharedPreference("name","");
    }

    public void setUserName(String name) {
        helper.put("name",name);
    }

    public void setUser(String s){
        helper.put("user",s);
    }

    public LoginBean getUser(){
        Gson gson = new Gson();
        String s = (String) helper.getSharedPreference("user","");
        LoginBean bean = gson.fromJson(s,LoginBean.class);
        return bean;
    }




    public void updateUserInfo(LoginBean bean){
        setToken(bean.getToken());
        setPhone(bean.getPhone());
        setAvatar(bean.getHeadimgurl());
        setUserName(bean.getNickname());
        Gson gson = new Gson();
        setUser(gson.toJson(bean));
    }

    public void logout() {
        setToken("");
        setPhone("");
        setAvatar("");
        setUserName("");
    }
}
