package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.MultySelectAdapter;
import luyuan.tech.com.chaoke.bean.MultySelectBean;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class MultySelectPopup extends BasePopupWindow {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    MultySelectAdapter adapter;
    @BindView(R.id.btn_submmit)
    TextView btnSubmmit;
    private ArrayList<MultySelectBean> list;

    public MultySelectPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    public void setData(ArrayList<MultySelectBean> data) {
        list.clear();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void bindEvent() {
        ButterKnife.bind(this, getContentView());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new MultySelectAdapter(list);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                list.get(position).checked = !list.get(position).checked;
                adapter.notifyDataSetChanged();
            }
        });
        btnSubmmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder titleBuilder = new StringBuilder();
                StringBuilder valueBuilder = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    MultySelectBean bean = list.get(i);
                    if (bean.checked){
                        valueBuilder.append(bean.id+",");
                        titleBuilder.append(bean.title+",");
                    }
                }
                if (listener!=null){
                    listener.onGetTitle(titleBuilder.toString());
                    listener.onGetValue(valueBuilder.toString());
                }
                dismiss();
            }
        });
    }

    private OnMultySelectListener listener;
    public interface OnMultySelectListener{
        void onGetTitle(String s);
        void onGetValue(String s);
    }
    public void setOnMultySelectListener(OnMultySelectListener listener){
        this.listener = listener;
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_duoxuan);
    }

}
