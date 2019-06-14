package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

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
public class QuYuPopup extends BasePopupWindow  {

    private RecyclerView recyclerLeft;
    private RecyclerView recyclerRight;
    private QuYuLeftAdapter leftAdapter;
    private QuYuRightAdapter rightAdapter;

    public QuYuPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {
        recyclerLeft = findViewById(R.id.recycler_left);
        recyclerRight = findViewById(R.id.recycler_right);
        recyclerLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRight.setLayoutManager(new LinearLayoutManager(getContext()));
        final ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < 2; i++) {
            list.add(i);
        }
        leftAdapter = new QuYuLeftAdapter(list);
        recyclerLeft.setAdapter(leftAdapter);
        rightAdapter = new QuYuRightAdapter(list);
        recyclerRight.setAdapter(rightAdapter);

        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position==0){
                    dismiss();
                }else if (position==1){

                }
            }
        });

        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
            }
        });
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_quyu);
    }



}
