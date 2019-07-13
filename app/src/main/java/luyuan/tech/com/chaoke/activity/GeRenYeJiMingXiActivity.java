package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.MingXiAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.MingXiBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class GeRenYeJiMingXiActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_user_address)
    TextView tvUserAddress;
    @BindView(R.id.tv_month_count)
    TextView tvMonthCount;
    @BindView(R.id.tv_year_count)
    TextView tvYearCount;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    MingXiAdapter adapter;
    ArrayList<MingXiBean.MonthDataBean> list;
    String time;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.iv_date)
    ImageView ivDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenyejimingxi);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        list = new ArrayList<>();
        adapter = new MingXiAdapter(list);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        time = format.format(date);
        tvMonth.setText(time.split("-")[1]+"月");
        loadData(time);
        ivDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
    }

    private void loadData(String time) {
        HttpManager.post(HttpManager.YEJIMINGXI)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("search", time)
                .execute(new SimpleCallBack<MingXiBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(MingXiBean data) {
                        fillData(data);
                    }
                });
    }

    public void showPicker() {
        final DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.create(true);
        datePickerDialogFragment.show(getSupportFragmentManager(), "tag");
        datePickerDialogFragment.setOnSelectListener(new DatePickerDialogFragment.OnDateSelectListener() {
            @Override
            public void onSelect(List<String> date) {
                String[] arr = date.get(0).split("-");
                time = arr[0] + "-" + arr[1];
                tvMonth.setText(arr[1]+"月");
                loadData(time);
                datePickerDialogFragment.dismiss();
            }
        });
    }

    private void fillData(MingXiBean data) {
        tvMonthCount.setText(data.getMonth_count() + "");
        tvYearCount.setText(data.getYears_count() + "");
        list.clear();
        list.addAll(data.getMonth_data());
        adapter.notifyDataSetChanged();
    }


}
