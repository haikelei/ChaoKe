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
import luyuan.tech.com.chaoke.bean.QianYueBeanEightChuzuren;
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


public class FangYuanQianYueEightJiaFangDaiLiRenActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.sl_zhengjianleixing)
    SelectLayout slZhengjianleixing;
    @BindView(R.id.input_xingming)
    InputLayout inputXingming;
    @BindView(R.id.input_zhengjianhaoma)
    InputLayout inputZhengjianhaoma;
    @BindView(R.id.input_shoujihaoma)
    InputLayout inputShoujihaoma;
    @BindView(R.id.input_youxiangdizhi)
    InputLayout inputYouxiangdizhi;
    @BindView(R.id.input_tongxundizhi)
    InputLayout inputTongxundizhi;
    private String downloadTotalId;
    private String uploadTotalId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianyuefangyuan_ten_jiafangdailiren);
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
        String[] arr1 = {"居民身份证","护照","军人证"};
        setSelectLListener(slZhengjianleixing,arr1,"证件类型");
        if (!TextUtils.isEmpty(downloadTotalId)){
            loadOldData(downloadTotalId);
        }
    }

    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.QIANYUESHUJU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",totalId)
                .params("step", "11")
                .execute(new SimpleCallBack<QianYueBeanEightChuzuren>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(QianYueBeanEightChuzuren data) {
                        slZhengjianleixing.setSelect(data.getCard_type());
                        inputXingming.setText(data.getUsername());
                        inputZhengjianhaoma.setText(data.getCard_num());
                        inputShoujihaoma.setText(data.getPhone());
                        inputYouxiangdizhi.setText(data.getEmail_address());
                        inputTongxundizhi.setText(data.getComm_address());
                    }
                });

    }

    private String oldId;

    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        PostRequest postRequest = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id", uploadTotalId)
                .params("card_type",getValue(slZhengjianleixing))
                .params("username",getValue(inputXingming))
                .params("card_num",getValue(inputZhengjianhaoma))
                .params("phone",getValue(inputShoujihaoma))
                .params("email_address",getValue(inputYouxiangdizhi))
                .params("comm_address",getValue(inputTongxundizhi))
                .params("step", "11");
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
                oldId = data.getTotal_id();
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueNineActivity.class);
                intent.putExtra("down_id",downloadTotalId);
                intent.putExtra("up_id",uploadTotalId);
                startActivity(intent);
            }
        });
    }

}
