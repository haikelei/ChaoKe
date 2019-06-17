package luyuan.tech.com.chaoke.net;

import com.google.gson.Gson;

/**
 * @author: lujialei
 * @date: 2018/12/11
 * @describe:
 */


public class NetParser {

    public static Gson gson = new Gson();

    public static boolean isOk(String s){
        CustomApiResult customApiResult = gson.fromJson(s,CustomApiResult.class);
        if (customApiResult.getCode()==1){
            return  true;
        }
        return false;
    }

    public static  <T> T parse(String data, Class<T> clazz){
        return gson.fromJson(data,clazz);
    }
}
