package luyuan.tech.com.chaoke.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author: lujialei
 * @date: 2019/7/23
 * @describe:
 */

public final class KeyBoardUtil {
    public KeyBoardUtil() {
    }

    public static void showKeyBoard(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, 2);
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void hideKeyBoard(Activity context) {
        View view = context.getCurrentFocus();
        if(view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }

    }

    public static boolean isShownKeyBoard(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if(view == null) {
            return false;
        } else {
            InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            return inputMethodManager.isActive() && activity.getWindow().getCurrentFocus() != null;
        }
    }
}