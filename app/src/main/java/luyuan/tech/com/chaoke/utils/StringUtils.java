package luyuan.tech.com.chaoke.utils;

import java.util.regex.Pattern;

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

    /**
     * 1.判断字符串是否仅为数字:
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {

        for (int i = str.length(); --i >= 0; ) {

            if (!Character.isDigit(str.charAt(i))) {

                return false;

            }

        }

        return true;

    }

    /*
  * 判断是否为整数
  * @param str 传入的字符串
  * @return 是整数返回true,否则返回false
*/


    public static boolean isZhengInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
