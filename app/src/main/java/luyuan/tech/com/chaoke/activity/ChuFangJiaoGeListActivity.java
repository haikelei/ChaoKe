package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.ChuFangJiaoGePagerAdapter;
import luyuan.tech.com.chaoke.adapter.ShouFangJiaoGePagerAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ShouFangJiaoGeBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ChuFangJiaoGeListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_fang_jiaoge_list);
        ButterKnife.bind(this);
        initView();
        initListener();
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.CHUFANGJIAOGE_LIST)
                .params("token", UserInfoUtils.getInstance().getToken())
                .execute(new SimpleCallBack<ShouFangJiaoGeBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(ShouFangJiaoGeBean data) {
                        viewPager.setOffscreenPageLimit(5);
                        viewPager.setAdapter(new ChuFangJiaoGePagerAdapter(getSupportFragmentManager(),data));
                        tabLayout.setViewPager(viewPager);
                    }
                });
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {

    }
}
