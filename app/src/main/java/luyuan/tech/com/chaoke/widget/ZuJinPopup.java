package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.QuYuLeftAdapter;
import luyuan.tech.com.chaoke.adapter.QuYuRightAdapter;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class ZuJinPopup extends BasePopupWindow  {


    public ZuJinPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {

    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_zujin);
    }



}
