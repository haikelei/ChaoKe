package luyuan.tech.com.chaoke.utils;

/**
 * @author: lujialei
 * @date: 2019/8/22
 * @describe:
 */


public class StringUtils {

    public static String handleDouHao(String value) {
        if (value.length()>1){
            String s = value.substring(0,value.length()-1);
            return s;
        }
        return value;

    }
}
