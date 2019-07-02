package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.GeRenYeJiAdapter;
import luyuan.tech.com.chaoke.adapter.GenJinAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.GeRenYeJiBean;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class GeRenYeJiPaiMingActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private GeRenYeJiAdapter adapter;
    private ArrayList<GeRenYeJiBean> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenyeji);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new GeRenYeJiAdapter(list);
        recycler.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.GERENYEJI)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("type","1")
                .params("order_by","1")
                .execute(new SimpleCallBack<List<GeRenYeJiBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<GeRenYeJiBean> data) {
                        list.clear();
                        list.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });
    }


}
