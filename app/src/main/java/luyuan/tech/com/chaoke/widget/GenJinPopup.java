package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.view.View;

import luyuan.tech.com.chaoke.R;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class GenJinPopup extends BasePopupWindow  {


    public GenJinPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {

    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_genjin);
    }



}
