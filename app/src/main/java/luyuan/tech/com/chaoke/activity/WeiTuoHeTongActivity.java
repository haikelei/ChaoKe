package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.HouseTaskPagerAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.fragment.MessageFragment;
import luyuan.tech.com.chaoke.fragment.SuoYouHeTongFragment;
import luyuan.tech.com.chaoke.fragment.ZhengZaiQianYueFragment;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class WeiTuoHeTongActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_tuo_he_tong);
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
//        viewPager.setAdapter(new HouseTaskPagerAdapter(getSupportFragmentManager()));
        String[] arr = {"正在签约", "所有合同"};
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new ZhengZaiQianYueFragment());
        list.add(new SuoYouHeTongFragment());
        tabLayout.setTabData(arr, this,R.id.container,list);

    }
}
