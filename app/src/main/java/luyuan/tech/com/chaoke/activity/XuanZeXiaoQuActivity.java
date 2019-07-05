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
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.XiaoQuAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.XiaoQuBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class XuanZeXiaoQuActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.search_input)
    EditText searchInput;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<XiaoQuBean> list = new ArrayList<>();
    private XiaoQuAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanzexiaoqu);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new XiaoQuAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("data",list.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        recycler.setAdapter(adapter);
        loadData("");
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loadData(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loadData(String s) {
        PostRequest request = HttpManager.post(HttpManager.XIAOQUMINGCHENG)
                .params("token", UserInfoUtils.getInstance().getToken());
        if (!TextUtils.isEmpty(s)){
            request.params("search",s);
        }
        request.execute(new SimpleCallBack<List<XiaoQuBean>>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getContext(), e.getMessage());
            }

            @Override
            public void onSuccess(List<XiaoQuBean> data) {
                list.clear();
                list.addAll(data);
                adapter.notifyDataSetChanged();
                if (list == null || list.size() == 0) {
                    T.showShort(getContext(), "暂无小区数据");
                }

            }
        });
    }

}
