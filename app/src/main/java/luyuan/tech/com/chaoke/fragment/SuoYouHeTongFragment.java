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
import luyuan.tech.com.chaoke.activity.ChuZuHeTongXiangQingActivity;
import luyuan.tech.com.chaoke.adapter.ChuZuHeTongAdapter;
import luyuan.tech.com.chaoke.bean.ChuZuHeTongBean;
import luyuan.tech.com.chaoke.utils.T;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class SuoYouHeTongFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    ArrayList<ChuZuHeTongBean.ListBean> list;

    public static Fragment instance(ArrayList<ChuZuHeTongBean.ListBean> list) {
        SuoYouHeTongFragment fragment = new SuoYouHeTongFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data",list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhengzaiqianyue, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getArguments()!=null){
            list = getArguments().getParcelableArrayList("data");
        }
        ChuZuHeTongAdapter adapter = new ChuZuHeTongAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if ( list.get(position).user_id<=0){
                    T.showShort(getActivity(),"id小于0");
                    return;
                }
                String id = list.get(position).getId()+"";
                Intent intent = new Intent(getActivity(), ChuZuHeTongXiangQingActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        recycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
