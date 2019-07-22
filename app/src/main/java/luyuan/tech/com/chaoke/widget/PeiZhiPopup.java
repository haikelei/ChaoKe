package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.PeizhiAdapter;
import luyuan.tech.com.chaoke.bean.PeiZhiBean;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class PeiZhiPopup extends BasePopupWindow {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_submmit)
    Button btnSubmmit;
    PeizhiAdapter adapter;
    ArrayList<PeiZhiBean> list;
    public interface OnPeiZhiListener{
        void onGetData(String s);
    }
    private OnPeiZhiListener listener;
    public void setOnPeiZhiListener(OnPeiZhiListener listener){
        listener = listener;
    }

    public PeiZhiPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnSubmmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null&&list.size()>0){
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                       if (list.get(i).checked){
                           stringBuilder.append(list.get(i).getId()+",");
                       }
                    }
                    listener.onGetData(stringBuilder.toString());
                }
                dismiss();
            }
        });
    }

    private void bindEvent() {
        ButterKnife.bind(this, getContentView());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new PeizhiAdapter(list);
        recycler.setAdapter(adapter);
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_peizhi);
    }


    public void setData(List<PeiZhiBean> datas) {
        list.clear();
        list.addAll(datas);
        adapter.notifyDataSetChanged();
    }
}
