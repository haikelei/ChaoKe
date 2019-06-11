package luyuan.tech.com.chaoke.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ItemBean;
import luyuan.tech.com.chaoke.adapter.SelectListAdapter;
import luyuan.tech.com.chaoke.bean.ItemListBean;

/**
 * @author: lujialei
 * @date: 2019/6/11
 * @describe:
 */


public class SelectDialogFragment extends DialogFragment {

    RecyclerView recyclerView;
    private ArrayList<ItemBean> datas;

    public static SelectDialogFragment create(ArrayList<ItemBean> datas) {
        SelectDialogFragment dialogFragment = new SelectDialogFragment();
        ItemListBean itemListBean = new ItemListBean();
        itemListBean.setList(datas);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",itemListBean);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //无标题

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = inflater.inflate(R.layout.layout_select_dialog, container);
        recyclerView = view.findViewById(R.id.recycler);
        if (getArguments()!=null&&getArguments().getSerializable("data")!=null){
            final ItemListBean itemListBean = (ItemListBean) getArguments().getSerializable("data");
            SelectListAdapter adapter = new SelectListAdapter(itemListBean.getList());
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    for (int i = 0; i < itemListBean.getList().size(); i++) {
                        itemListBean.getList().get(i).setChecked(position==i);
                    }
                    if (mOnSelectListener!=null){
                        mOnSelectListener.onSelect(itemListBean.getList().get(position).getTitle());
                    }
                    dismiss();
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    public void show(FragmentManager manager) {
        super.show(manager, "select");
    }

    @Override
    public void onStart() {
        super.onStart();
        int dialogHeight = (int) (getContext().getResources().getDisplayMetrics().heightPixels * 0.8);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, dialogHeight);
        getDialog().setCanceledOnTouchOutside(true); //点击边际可消失
    }

    public interface OnSelectListener{
        void onSelect(String s);
    }
    private OnSelectListener mOnSelectListener;
    public void setOnSelectListener(OnSelectListener mOnSelectListener) {
        this.mOnSelectListener = mOnSelectListener;
    }

}
