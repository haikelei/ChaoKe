package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.GeRenYeJiAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.GeRenYeJiBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.PaiMingHeader;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class GeRenYeJiPaiMingActivity extends BaseActivity implements ActionSheet.ActionSheetListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    private GeRenYeJiAdapter adapter;
    private ArrayList<GeRenYeJiBean> list;
    private PaiMingHeader header;
    private String order_by = "1";


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
        header = new PaiMingHeader(getActivity());
        adapter.addHeaderView(header);
        recycler.setAdapter(adapter);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GeRenYeJiMingXiActivity.class);
                startActivity(intent);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), GeRenYeJiMingXiActivity.class);
                startActivity(intent);
            }
        });
        tvShaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        loadData();
    }


    public void showDialog() {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("Cancel")
                .setOtherButtonTitles("全部排名", "区排名", "门店排名", "小组排名")
                .setCancelableOnTouchOutside(true)
                .setCancelButtonTitle("取消")
                .setListener(this).show();
    }

    private void loadData() {
        PostRequest request = HttpManager.post(HttpManager.GERENYEJI)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("type", "1");
        if (!TextUtils.isEmpty(order_by)) {
            request.params("order_by", order_by);
        }
        request.execute(new SimpleCallBack<List<GeRenYeJiBean>>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(List<GeRenYeJiBean> data) {
                ArrayList<GeRenYeJiBean> temp = new ArrayList<>();
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        if (i < 3) {
                            header.setData(data.get(i), i);
                        } else {
                            temp.add(data.get(i));
                        }

                    }
                }
                list.clear();
                list.addAll(temp);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            order_by = "1";
        } else if (index == 1) {
            order_by = "2";
        } else if (index == 0) {
            order_by = "3";
        } else if (index == 0) {
            order_by = "1";
        }
        loadData();
    }
}
