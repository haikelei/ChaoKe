package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.HouseTaskPagerAdapter;
import luyuan.tech.com.chaoke.adapter.ShouFangShenPiPagerAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ShouFangShenPiActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_fang_shen_pi);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        viewPager.setAdapter(new ShouFangShenPiPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }
}
