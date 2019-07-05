package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.ZiYingYuanAdapter;
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


public class ZiYingFangYuanActivity extends BaseActivity {
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
    @BindView(R.id.rl_container)
    LinearLayout rlContainer;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.recycler_left)
    RecyclerView recyclerLeft;
    @BindView(R.id.recycler_right)
    RecyclerView recyclerRight;
    @BindView(R.id.tv_zujinbuxian)
    TextView tvZujinbuxian;
    @BindView(R.id.tv_zujin_1500)
    TextView tvZujin1500;
    @BindView(R.id.tv_zujin_2000)
    TextView tvZujin2000;
    @BindView(R.id.tv_zujin_2500)
    TextView tvZujin2500;
    @BindView(R.id.tv_zujin_4000)
    TextView tvZujin4000;
    @BindView(R.id.tv_zujin_4000_high)
    TextView tvZujin4000High;
    @BindView(R.id.et_from)
    EditText etFrom;
    @BindView(R.id.et_to)
    EditText etTo;
    @BindView(R.id.btn_zujin)
    Button btnZujin;
    @BindView(R.id.tv_paixu_buxian)
    TextView tvPaixuBuxian;
    @BindView(R.id.tv_paixu_zujinhigh)
    TextView tvPaixuZujinhigh;
    @BindView(R.id.tv_paixu_zujinlow)
    TextView tvPaixuZujinlow;
    @BindView(R.id.tv_paixu_mianjihigh)
    TextView tvPaixuMianjihigh;
    @BindView(R.id.tv_paixu_mianjilow)
    TextView tvPaixuMianjilow;
    @BindView(R.id.btn_shaixuan)
    TextView btnShaixuan;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private ArrayList<HouseBean> list = new ArrayList<>();
    private ZiYingYuanAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziying_fangyuan);
        ButterKnife.bind(this);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),HouseSearchActivity.class));
            }
        });
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
        initView();
        loadData();
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new ZiYingYuanAdapter(list);
        recycler.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getBaseContext(), XianChangDaiKanActivity.class);
//                HouseBean houseBean = list.get(position);
//                intent.putExtra("id", houseBean.getId() + "");
//                startActivity(intent);
//            }
//        });
    }

    private void loadData() {
        PostRequest request = HttpManager.post(HttpManager.HOUSE_LIST)
                .params("type", "1")
                .params("token", UserInfoUtils.getInstance().getToken());
        request.execute(new SimpleCallBack<List<HouseBean>>() {

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

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
