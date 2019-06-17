package luyuan.tech.com.chaoke.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.DaiChuShenAdapter;
import luyuan.tech.com.chaoke.adapter.DaiGenJinAdapter;
import luyuan.tech.com.chaoke.bean.LoginBean;
import luyuan.tech.com.chaoke.bean.ShouFangShenPiBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ShouFangDaiChuShenFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private ArrayList<ShouFangShenPiBean.DataBean> list;
    private DaiChuShenAdapter adapter;
    private int type;//1 待初审 2待复审 3审核通过

    public static Fragment newInstance(int type) {
        ShouFangDaiChuShenFragment fragment = new ShouFangDaiChuShenFragment();
        Bundle bundle = new Bundle();
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
            type = getArguments().getInt("type",1);
        }
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList();
        adapter = new DaiChuShenAdapter(list);
        recycler.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.SHOUFANGSHENPI)
                .params("token",UserInfoUtils.getInstance().getToken())
                .params("type",String.valueOf(type))
                .execute(new SimpleCallBack<ShouFangShenPiBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(),e.getMessage());
                    }

                    @Override
                    public void onSuccess(ShouFangShenPiBean bean) {
                        adapter.setNewData(bean.getData());
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
