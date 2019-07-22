package luyuan.tech.com.chaoke.utils;

import android.text.TextUtils;

import luyuan.tech.com.chaoke.App;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;


/**
 * @author: lujialei
 * @date: 2018/10/3
 * @describe:
 */


public class SettingManager {
    private static SettingManager instance;
    private SharedPreferencesHelper helper;
    private SettingManager(){
        helper = new SharedPreferencesHelper(App.getAppContext(),"chaoke_sp");
    }

    public static SettingManager getInstance(){
        if (instance==null){
            instance = new SettingManager();
        }
        return instance;
    }

    private ZuKeDetailBean zuKeDetailBean = new ZuKeDetailBean();

    public ZuKeDetailBean getZuKeDetailBean() {
        return zuKeDetailBean;
    }

    public void setZuKeDetailBean(ZuKeDetailBean zuKeDetailBean) {
        this.zuKeDetailBean = zuKeDetailBean;
    }
}
