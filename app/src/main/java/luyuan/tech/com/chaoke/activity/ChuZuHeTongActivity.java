package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ChuZuHeTongBean;
import luyuan.tech.com.chaoke.fragment.SuoYouHeTongFragment;
import luyuan.tech.com.chaoke.fragment.ZhengZaiQianYueFragment;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ChuZuHeTongActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_zhu_he_tong);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.CHUZUHETONG_LIST)
                .params("token", UserInfoUtils.getInstance().getToken())
                .execute(new SimpleCallBack<ChuZuHeTongBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(ChuZuHeTongBean data) {
                        ArrayList<ChuZuHeTongBean.ListBean> list = (ArrayList<ChuZuHeTongBean.ListBean>) data.getList();
                        ArrayList<ChuZuHeTongBean.ListBean> list1 = (ArrayList<ChuZuHeTongBean.ListBean>) data.getList1();
                        String[] arr = {"正在签约", "所有合同"};
                        ArrayList<Fragment> fragments = new ArrayList<>();
                        fragments.add(ZhengZaiQianYueFragment.instance(list));
                        fragments.add(SuoYouHeTongFragment.instance(list1));
                        tabLayout.setTabData(arr, ChuZuHeTongActivity.this,R.id.container,fragments);
                    }
                });
    }

}
