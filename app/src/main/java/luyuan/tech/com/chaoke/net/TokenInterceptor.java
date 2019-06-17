package luyuan.tech.com.chaoke.net;

import android.content.Intent;

import com.google.gson.Gson;
import com.zhouyou.http.interceptor.BaseExpiredInterceptor;
import com.zhouyou.http.model.ApiResult;

import luyuan.tech.com.chaoke.App;
import luyuan.tech.com.chaoke.activity.LoginActivity;
import okhttp3.Response;

/**
 * <p>描述：处理het token、签名等异常</p>
 * 作者： zhouyou<br>
 * 日期： 2017/5/4 21:02 <br>
 * 版本： v1.0<br>
 */
public class TokenInterceptor extends BaseExpiredInterceptor {
    private ApiResult apiResult;

    @Override
    public boolean isResponseExpired(Response response, String bodyString) {
        apiResult = new Gson().fromJson(bodyString, ApiResult.class);
        if (apiResult != null) {
            int code = apiResult.getCode();
            if (code == Code.TOKEN_INVAILD) {
                return true;
            }
        }
        return false;
    }

    //只有上面配置的过期情况才会执行这里
    @Override
    public Response responseExpired(Chain chain, String bodyString) {
        try {
            switch (apiResult.getCode()) {
                case Code.TOKEN_INVAILD: //AccessToken错误或已过期
                    App.getAppContext().startActivity(new Intent(App.getAppContext(), LoginActivity.class));
                    Response response = chain.proceed(chain.request());
                    return response;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

}
