package luyuan.tech.com.chaoke.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.activity.ChuFangJiaoGeActivity;
import luyuan.tech.com.chaoke.adapter.ShouFangJiaoGeAdapter;
import luyuan.tech.com.chaoke.bean.ShouFangJiaoGeListBean;

import static luyuan.tech.com.chaoke.adapter.ChuFangJiaoGePagerAdapter.TYPE_YIJIAOGE;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ChuFangJiaoGeFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private ArrayList<ShouFangJiaoGeListBean> list;
    private ShouFangJiaoGeAdapter adapter;
    private int type;

    public static Fragment newInstance(ArrayList<ShouFangJiaoGeListBean> list,int type) {
        ChuFangJiaoGeFragment fragment = new ChuFangJiaoGeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data",list);
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments()!=null){
            list = getArguments().getParcelableArrayList("data");
            type = getArguments().getInt("type");
        }
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShouFangJiaoGeAdapter(list);
        recycler.setAdapter(adapter);
        loadData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (type==TYPE_YIJIAOGE){
                    return;
                }
                Intent intent = new Intent(getActivity(), ChuFangJiaoGeActivity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }

    private void loadData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
