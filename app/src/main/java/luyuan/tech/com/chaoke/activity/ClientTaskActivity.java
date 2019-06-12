package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.ClientTaskAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ClientTaskActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_task);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        ArrayList list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(0);
        }
        recycler.setAdapter(new ClientTaskAdapter(list));
    }
}
