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
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianyuefangyuan_three);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
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
    }

    private String oldId;

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        PostRequest postRequest = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id", id)
                .params("payment_msg", getValue(slFukuanxinxi))
                .params("payment_day", getValue(inputFukuantianshu))
                .params("rent_day", getValue(inputMianzhutianshu))
                .params("entrust_begin", getValue(slWeituoqisuanri))
                .params("entrust_end", getValue(slWeituodaoqiri))
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
                startActivity(new Intent(getBaseContext(), FangYuanQianYueFourActivity.class));
            }
        });
    }

}
