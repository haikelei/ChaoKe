package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HeTongXiangQingBean;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.QianYueBeanTen;
import luyuan.tech.com.chaoke.bean.QianYueBeanTwo;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class FangYuanQianYueTenActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.input_xingming)
    InputLayout inputXingming;
    @BindView(R.id.input_shouji)
    InputLayout inputShouji;
    private String downloadTotalId;
    private String uploadTotalId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_ten);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            downloadTotalId = getIntent().getStringExtra("down_id");
            uploadTotalId = getIntent().getStringExtra("up_id");
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();

            }
        });

        if (!TextUtils.isEmpty(downloadTotalId)){
            loadOldData(downloadTotalId);
        }
    }

    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.QIANYUESHUJU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",totalId)
                .params("step", "13")
                .execute(new SimpleCallBack<QianYueBeanTen>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(QianYueBeanTen data) {
                        inputXingming.setText(data.getUsername());
                        inputShouji.setText(data.getPhone());
                    }
                });

    }

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",uploadTotalId)
                .params("step","13" )
                .params("username",getValue(inputXingming) )
                .params("phone", getValue(inputShouji))
                .execute(new SimpleCallBack<HeTongXiangQingBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HeTongXiangQingBean data) {
                        Intent intent = new Intent(getBaseContext(), HeToneXiangQingActivity.class);
                        intent.putExtra("data",data);
                        startActivity(intent);
                    }
                });
    }

}
