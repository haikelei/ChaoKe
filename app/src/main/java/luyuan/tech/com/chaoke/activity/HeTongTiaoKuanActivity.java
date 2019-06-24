package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.HeTongTiaoKuanPagerAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class HeTongTiaoKuanActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_he_tong_tiao_kuan);
        ButterKnife.bind(this);
        initView();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        viewPager.setAdapter(new HeTongTiaoKuanPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }


}
