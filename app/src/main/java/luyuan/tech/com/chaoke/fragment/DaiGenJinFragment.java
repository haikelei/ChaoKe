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
import luyuan.tech.com.chaoke.activity.FangYuanXiangQingActivity;
import luyuan.tech.com.chaoke.adapter.DaiGenJinAdapter;
import luyuan.tech.com.chaoke.adapter.HouseTaskAdapter;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.bean.HouseTaskBean;
import luyuan.tech.com.chaoke.bean.XiaoQuBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class DaiGenJinFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private List<HouseTaskBean> list = new ArrayList<>();
    private HouseTaskAdapter adapter;

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
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HouseTaskAdapter(list);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), FangYuanXiangQingActivity.class);
                intent.putExtra("id", list.get(position).getId()+"");
                startActivity(intent);
            }
        });
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.HOUSE_TASK)
                .params("token", UserInfoUtils.getInstance().getToken())
                .execute(new SimpleCallBack<List<HouseTaskBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(),e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<HouseTaskBean> data) {
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
