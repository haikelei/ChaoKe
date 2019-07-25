package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.QianYueFangYuanAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.SettingManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class QianYueFangYuanActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    ArrayList<HouseBean> list = new ArrayList();
    private QianYueFangYuanAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianyue_fangyuan);
        ButterKnife.bind(this);
        initView();
        loadData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        HttpManager.post(HttpManager.QIANYUEFANGYUAN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("tenant_id", SettingManager.getInstance().getZuKeDetailBean().getId()+"")
                .execute(new SimpleCallBack<List<HouseBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(),e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<HouseBean> data) {
                        list.clear();
                        list.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new QianYueFangYuanAdapter(list);
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getBaseContext(),XianChangQianYueActivity.class);
                HouseBean houseBean = list.get(position);
                intent.putExtra("id",String.valueOf(houseBean.getId()));
                startActivity(intent);
            }
        });
    }
}
