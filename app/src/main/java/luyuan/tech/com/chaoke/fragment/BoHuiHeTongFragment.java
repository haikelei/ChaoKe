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
import luyuan.tech.com.chaoke.activity.BoHuiWeiTuoHeTongXiangQingActivity;
import luyuan.tech.com.chaoke.activity.WeiTuoHeTongXiangQingActivity;
import luyuan.tech.com.chaoke.adapter.BoHuiHeTongAdapter;
import luyuan.tech.com.chaoke.adapter.WeiTuoHeTongAdapter;
import luyuan.tech.com.chaoke.bean.WeiTuoHeTongBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class BoHuiHeTongFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    ArrayList<WeiTuoHeTongBean> list;
    private BoHuiHeTongAdapter adapter;

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
        list = new ArrayList<>();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BoHuiHeTongAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = list.get(position).getId()+"";
                Intent intent = new Intent(getActivity(), BoHuiWeiTuoHeTongXiangQingActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        recycler.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.WEITUOHETONG_LIST)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("type", "3")
                .execute(new SimpleCallBack<List<WeiTuoHeTongBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getActivity(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<WeiTuoHeTongBean> data) {
                        list.clear();
                        list.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
