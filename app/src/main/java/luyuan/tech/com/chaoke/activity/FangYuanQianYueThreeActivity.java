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
import com.zhouyou.http.request.PostRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.QianYueBeanThree;
import luyuan.tech.com.chaoke.bean.QianYueBeanTwo;
import luyuan.tech.com.chaoke.bean.TotalIdBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class FangYuanQianYueThreeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.sl_fukuanxinxi)
    SelectLayout slFukuanxinxi;
    @BindView(R.id.input_fukuantianshu)
    InputLayout inputFukuantianshu;
    @BindView(R.id.input_mianzhutianshu)
    InputLayout inputMianzhutianshu;
    @BindView(R.id.sl_weituoqisuanri)
    SelectLayout slWeituoqisuanri;
    @BindView(R.id.sl_weituodaoqiri)
    SelectLayout slWeituodaoqiri;
    private String downloadTotalId;
    private String uploadTotalId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianyuefangyuan_three);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            downloadTotalId = getIntent().getStringExtra("down_id");
            uploadTotalId = getIntent().getStringExtra("up_id");
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        String[] arr = {"1次性付款 ", "年付", "月付", "季付", "半年付"};
        setSelectLListener(slFukuanxinxi, arr, "付款信息");
        setDatePickerListener(slWeituodaoqiri);
        setDatePickerListener(slWeituoqisuanri);

        if (!TextUtils.isEmpty(downloadTotalId)){
            loadOldData(downloadTotalId);
        }
    }

    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.QIANYUESHUJU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",totalId)
                .params("step", "3")
                .execute(new SimpleCallBack<QianYueBeanThree>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(QianYueBeanThree data) {
                        slFukuanxinxi.setSelect(data.getPayment_msg());
                        inputFukuantianshu.setText(data.getPayment_day());
                        inputMianzhutianshu.setText(data.getRent_day());
                        slWeituoqisuanri.setText(data.getEntrust_begin());
                        slWeituodaoqiri.setText(data.getEntrust_end());

                    }
                });

    }

    private String oldId;

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        PostRequest postRequest = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id", uploadTotalId)
                .params("payment_msg", getValue(slFukuanxinxi))
                .params("payment_day", getValue(inputFukuantianshu))
                .params("rent_day", getValue(inputMianzhutianshu))
                .params("entrust_begin", slWeituoqisuanri.getText())
                .params("entrust_end", slWeituodaoqiri.getText())
                .params("step", "3");
        if (!TextUtils.isEmpty(oldId)) {
            postRequest.params("old_id", oldId);
        }
        postRequest.execute(new SimpleCallBack<TotalIdBean>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(TotalIdBean data) {
                oldId = data.getOld_id();
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueFourActivity.class);
                intent.putExtra("down_id",downloadTotalId);
                intent.putExtra("up_id",uploadTotalId);
                startActivity(intent);
            }
        });
    }

}
