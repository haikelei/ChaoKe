package luyuan.tech.com.chaoke;

import android.app.Application;
import android.content.Context;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.model.HttpHeaders;
import com.zhouyou.http.model.HttpParams;

import luyuan.tech.com.chaoke.net.TokenInterceptor;


/**
 * @author: lujialei
 * @date: 2018/12/11
 * @describe:
 */


public class App extends Application {
    private static Application app = null;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initNet();
    }

    public static Context getAppContext(){
        return  app;
    }

    private void initNet() {
        EasyHttp.init(this);
        String Url = "http://renting.liebianzhe.com";
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
//        headers.put("token", SettingManager.getInstance().getToken());
        //设置请求参数
        HttpParams params = new HttpParams();
        EasyHttp.getInstance()
                .debug("RxEasyHttp", true)
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3)//默认网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setBaseUrl(Url)
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
                .setCacheVersion(1)//缓存版本为1
                .setCertificates()//信任所有证书
                .addInterceptor(new TokenInterceptor())
                .addCommonHeaders(headers)//设置全局公共头
                .addCommonParams(params);//设置全局公共参数
    }
}
