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
}
