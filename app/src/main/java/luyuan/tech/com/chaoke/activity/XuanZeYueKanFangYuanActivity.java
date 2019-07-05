package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.DuoXuanFangYuanAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DownMenuButton;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class XuanZeYueKanFangYuanActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.quyu_button)
    DownMenuButton quyuButton;
    @BindView(R.id.zujin_button)
    DownMenuButton zujinButton;
    @BindView(R.id.paixu_button)
    DownMenuButton paixuButton;
    @BindView(R.id.shaixuan_button)
    DownMenuButton shaixuanButton;
    @BindView(R.id.pop_quyu)
    LinearLayout llquyu;
    @BindView(R.id.pop_zujin)
    LinearLayout llzujin;
    @BindView(R.id.pop_paixu)
    LinearLayout llpaixu;
    @BindView(R.id.pop_shaixuan)
    LinearLayout llshaixuan;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.tv_xuanzhong)
    TextView tvXuanzhong;
    @BindView(R.id.tv_netx)
    TextView tvNetx;
    private ArrayList<HouseBean> list = new ArrayList<>();
    private DuoXuanFangYuanAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanzeyuekan_fangyuan);
        ButterKnife.bind(this);
        initView();
        loadData();
        quyuButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                llquyu.setVisibility(checked ? View.VISIBLE : View.GONE);
            }
        });
        zujinButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                llzujin.setVisibility(checked ? View.VISIBLE : View.GONE);
            }
        });
        paixuButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                llpaixu.setVisibility(checked ? View.VISIBLE : View.GONE);
            }
        });
        shaixuanButton.setOnCheckedChangeListener(new DownMenuButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChage(View view, boolean checked) {
                llshaixuan.setVisibility(checked ? View.VISIBLE : View.GONE);
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AddHouseActivity.class));
            }
        });
        tvNetx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HouseBean> data = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).checked){
                        data.add(list.get(i));
                    }
                }
                Intent intent = new Intent(getActivity(),YuYueDaiKanActivity.class);
                intent.putParcelableArrayListExtra("data",data);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        HttpManager.post(HttpManager.HOUSE_LIST)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("type", "2")
                .execute(new SimpleCallBack<List<HouseBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(), e.getMessage());
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
        adapter = new DuoXuanFangYuanAdapter(list);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HouseBean bean = list.get(position);
                bean.checked = !bean.checked;
                adapter.notifyDataSetChanged();
                updateText();
            }
        });
    }

    private void updateText() {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).checked){
                count++;
            }
        }
        tvXuanzhong.setText("选中"+count+"套");
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
