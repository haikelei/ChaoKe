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


public class FangYuanQianYueOneActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.input_wuyedizhi)
    InputLayout inputWuyedizhi;
    @BindView(R.id.input_fangyuanbianhao)
    InputLayout inputFangyuanbianhao;
    @BindView(R.id.sl_fangwulaiyuan)
    SelectLayout slFangwulaiyuan;
    @BindView(R.id.sl_qianyueleixing)
    SelectLayout slQianyueleixing;
    @BindView(R.id.input_hetongbianhao)
    InputLayout inputHetongbianhao;
    @BindView(R.id.sl_qianyueriqi)
    SelectLayout slQianyueriqi;
    @BindView(R.id.sl_jiaofangriqi)
    SelectLayout slJiaofangriqi;
    @BindView(R.id.sl_jiafangchizheng)
    SelectLayout slJiafangchizheng;
    @BindView(R.id.sl_fangwuyongtu)
    SelectLayout slFangwuyongtu;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuan_qianyue_one);
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

        String[] arr = {"中介合作", "转介绍", "老客户", "网络端口", "地推", "房东上门", "名单获取", "销冠"};
        setSelectLListener(slFangwulaiyuan, arr, "房源来源");

        String[] arr1 = {"房屋所有权证书", "原购房发票", "房屋买卖合同", "其他"};
        setSelectLListener(slJiafangchizheng, arr1, "甲方持证");

        setDatePickerListener(slQianyueriqi);
        setDatePickerListener(slJiaofangriqi);

        String[] arr2 = {"居住", "商用"};
        setSelectLListener(slFangwuyongtu, arr2, "房屋用途");

        String[] arr3 = {"首签", "续签"};
        setSelectLListener(slQianyueleixing, arr3, "签约类型");

    }

    private String totalId;

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        PostRequest postRequest = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .params("step", "1")
                .params("wuye_address", getValue(inputWuyedizhi))
                .params("rent_num", getValue(inputFangyuanbianhao))
                .params("from_by", getValue(slFangwulaiyuan))
                .params("signing_type", getValue(slQianyueleixing))
                .params("signing_time", getValue(slQianyueriqi))
                .params("over_time", getValue(slJiaofangriqi))
                .params("certificate", getValue(slJiafangchizheng))
                .params("used_by", getValue(slFangwuyongtu));
        if (!TextUtils.isEmpty(totalId)) {
            postRequest.params("total_id", totalId);
        }
        postRequest.execute(new SimpleCallBack<TotalIdBean>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(TotalIdBean data) {
                totalId = data.getTotal_id();
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueTwoActivity.class);
                intent.putExtra("id",totalId);
                startActivity(intent);
            }
        });
    }

}
