package luyuan.tech.com.chaoke.utils;

import android.text.TextUtils;

import luyuan.tech.com.chaoke.App;
import luyuan.tech.com.chaoke.bean.LoginBean;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;

/**
 * @author: lujialei
 * @date: 2019/6/15
 * @describe:
 */


public class AppStorageUtils {
    private static AppStorageUtils instance;
    private static ZuKeDetailBean zuKeDetailBean;

    public static ZuKeDetailBean getZuKeDetailBean() {
        return zuKeDetailBean;
    }

    public static void setZuKeDetailBean(ZuKeDetailBean zuKeDetailBean) {
        AppStorageUtils.zuKeDetailBean = zuKeDetailBean;
    }

    private AppStorageUtils(){
    }

    public static AppStorageUtils getInstance(){
        if (instance==null){
            instance = new AppStorageUtils();
        }
        return instance;
    }



}
