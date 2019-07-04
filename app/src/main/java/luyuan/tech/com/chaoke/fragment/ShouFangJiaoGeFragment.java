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
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.activity.ChuFangJiaoGeActivity;
import luyuan.tech.com.chaoke.adapter.DaiChuShenAdapter;
import luyuan.tech.com.chaoke.adapter.ShouFangJiaoGeAdapter;
import luyuan.tech.com.chaoke.bean.ShouFangJiaoGeListBean;
import luyuan.tech.com.chaoke.bean.ShouFangShenPiBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ShouFangJiaoGeFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private ArrayList<ShouFangJiaoGeListBean> list;
    private ShouFangJiaoGeAdapter adapter;

    public static Fragment newInstance(ArrayList<ShouFangJiaoGeListBean> list) {
        ShouFangJiaoGeFragment fragment = new ShouFangJiaoGeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data",list);
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
        }
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShouFangJiaoGeAdapter(list);
        recycler.setAdapter(adapter);
        loadData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ShouFangJiaoGeAdapter.class);
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
