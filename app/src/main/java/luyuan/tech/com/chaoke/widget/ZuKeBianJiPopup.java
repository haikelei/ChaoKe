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
public class ZuKeBianJiPopup extends BasePopupWindow  {


    public ZuKeBianJiPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {
        findViewById(R.id.tv_zukexinxi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnPopupClickListener!=null){
                    mOnPopupClickListener.onZukexinxiClick(view);
                }
            }
        });
        findViewById(R.id.tv_qiuzuxuqiu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnPopupClickListener!=null){
                    mOnPopupClickListener.onQiuzuxuqiu(view);
                }
            }
        });
        findViewById(R.id.tv_xiugaizhuangtai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnPopupClickListener!=null){
                    mOnPopupClickListener.onXiugaizhuangtai(view);
                }
            }
        });
    }



    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_zuke_bianji);
    }



    public interface OnPopupClickListener{
        void onZukexinxiClick(View view);
        void onQiuzuxuqiu(View view);
        void onXiugaizhuangtai(View view);
    }
    private OnPopupClickListener mOnPopupClickListener;
    public void setOnPopupClickListener(OnPopupClickListener mOnPopupClickListener){
        this.mOnPopupClickListener = mOnPopupClickListener;
    }
}
