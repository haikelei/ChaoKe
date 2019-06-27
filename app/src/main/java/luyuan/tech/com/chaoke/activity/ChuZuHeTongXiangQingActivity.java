package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ChuZuDetailBean;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/26
 * @describe:
 */


public class ChuZuHeTongXiangQingActivity extends BaseActivity {
    private String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuzuhetongxiangqing);
        if (getIntent()!=null){
            id = getIntent().getStringExtra("id");
        }
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.CHUZUHETONG_XIANGQING)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<ChuZuDetailBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(ChuZuDetailBean data) {
                        fillData(data);
                    }
                });
    }

    private void fillData(ChuZuDetailBean data) {

    }
}
