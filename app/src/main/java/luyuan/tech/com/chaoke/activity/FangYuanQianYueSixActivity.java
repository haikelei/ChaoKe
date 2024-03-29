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
import luyuan.tech.com.chaoke.bean.QianYueBeanSix;
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


public class FangYuanQianYueSixActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.input_chanquandizhi)
    InputLayout inputChanquandizhi;
    @BindView(R.id.input_jianzhumianji)
    InputLayout inputJianzhumianji;
//    @BindView(R.id.input_huxing)
//    InputLayout inputHuxing;
    @BindView(R.id.input_gongyouqingkuang)
    InputLayout inputGongyouqingkuang;
    @BindView(R.id.sl_yongtu)
    SelectLayout slYongtu;
    @BindView(R.id.sl_fangdaidiya)
    SelectLayout slFangdaidiya;
    private String downloadTotalId;
    private String uploadTotalId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_six);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            downloadTotalId = getIntent().getStringExtra("down_id");
            uploadTotalId = getIntent().getStringExtra("up_id");
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();

            }
        });
        String[] arr = {"住宅","商用"};
        setSelectLListener(slYongtu,arr,"用途");

        String[] arr1 = {"有抵押","无抵押"};
        setSelectLListener(slFangdaidiya,arr1,"房贷抵押");

        if (!TextUtils.isEmpty(downloadTotalId)){
            loadOldData(downloadTotalId);
        }
    }


    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.QIANYUESHUJU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",totalId)
                .params("step", "6")
                .execute(new SimpleCallBack<QianYueBeanSix>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(QianYueBeanSix data) {
                        oldId = data.getOld_id();
                        inputChanquandizhi.setText(data.getProperty_address());
                        inputJianzhumianji.setText(data.getArea());
//                        inputHuxing.setText(data.getApartment());
                        inputGongyouqingkuang.setText(data.getShare_desc());
                        slYongtu.setSelect(data.getUsed_type());
                        slFangdaidiya.setSelect(data.getIs_loan());
                    }
                });

    }

    private String oldId;
    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        PostRequest request = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",uploadTotalId)
                .params("step","6")
                .params("property_address",getValue(inputChanquandizhi))
                .params("area", getValue(inputJianzhumianji))
//                .params("apartment",getValue(inputHuxing))
                .params("share_desc",getValue(inputGongyouqingkuang))
                .params("used_type", getValue(slYongtu))
                .params("is_loan", getValue(slFangdaidiya));
        if (!TextUtils.isEmpty(oldId)){
            request.params("old_id",oldId);
        }

        request.execute(new SimpleCallBack<TotalIdBean>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(TotalIdBean data) {
                oldId = data.getOld_id();
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueSevenActivity.class);
                intent.putExtra("down_id",downloadTotalId);
                intent.putExtra("up_id",uploadTotalId);
                startActivity(intent);
            }
        });
    }

}
