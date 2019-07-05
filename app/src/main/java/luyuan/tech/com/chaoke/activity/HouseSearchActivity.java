package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.HouseSearchAdapter;
import luyuan.tech.com.chaoke.adapter.KaiFaFangYuanAdapter;
import luyuan.tech.com.chaoke.adapter.XiaoQuAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.XiaoQuBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class HouseSearchActivity extends BaseActivity {

    @BindView(R.id.tv_search)
    TextView tvCancel;
    @BindView(R.id.search_input)
    EditText searchInput;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    HouseSearchAdapter adapter;
    List<XiaoQuBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_search);
        ButterKnife.bind(this);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HouseSearchAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(),FangYuanXiangQingActivity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString().trim();
                loadData(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loadData(String s) {
        PostRequest request = HttpManager.post(HttpManager.SEARCH)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("type", "1");
        if (!TextUtils.isEmpty(s)){
            request.params("content",s);
        }
        request.execute(new SimpleCallBack<List<XiaoQuBean>>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(List<XiaoQuBean> data) {
                list.clear();
                list.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
    }


}
