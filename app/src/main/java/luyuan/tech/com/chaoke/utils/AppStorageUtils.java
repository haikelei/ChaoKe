package luyuan.tech.com.chaoke.utils;

import android.text.TextUtils;

import luyuan.tech.com.chaoke.App;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.LoginBean;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;

/**
 * @author: lujialei
 * @date: 2019/6/15
 * @describe:
 */


public class AppStorageUtils {
    private static AppStorageUtils instance;
    private HouseDetailBean mHouseDetail;

    private AppStorageUtils(){
    }

    public static AppStorageUtils getInstance(){
        if (instance==null){
            instance = new AppStorageUtils();
        }
        return instance;
    }

    public void setHouseDetail(HouseDetailBean houseDetail) {
        mHouseDetail = houseDetail;
    }

    public HouseDetailBean getHouseDetail() {
        return mHouseDetail;
    }



}
