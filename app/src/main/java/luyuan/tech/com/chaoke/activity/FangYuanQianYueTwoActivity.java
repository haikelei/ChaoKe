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
import luyuan.tech.com.chaoke.bean.QianYueBeanOne;
import luyuan.tech.com.chaoke.bean.QianYueBeanTwo;
import luyuan.tech.com.chaoke.bean.TotalIdBean;
import luyuan.tech.com.chaoke.bean.XiaoQuBean;
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


public class FangYuanQianYueTwoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.sl_gongyuleixing)
    SelectLayout slGongyuleixing;
    @BindView(R.id.input_gaizaoxinxi)
    InputLayout inputGaizaoxinxi;
    @BindView(R.id.input_hetongleixing)
    InputLayout inputHetongleixing;
    @BindView(R.id.sl_zhuangxiuqisuanri)
    SelectLayout slZhuangxiuqisuanri;
    @BindView(R.id.sl_zhuangxiujiezhiri)
    SelectLayout slZhuangxiujiezhiri;
    @BindView(R.id.sl_jiafangchegndan)
    SelectLayout slJiafangchegndan;
    private String downloadTotalId;
    private String uploadTotalId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_two);
        ButterKnife.bind(this);
        if (getIntent()!=null){
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
        String[] arr = {"普通公寓","商用公寓","住宅公寓","复式公寓","品牌公寓"};
        setSelectLListener(slGongyuleixing,arr,"公寓类型");

        setDatePickerListener(slZhuangxiujiezhiri);
        setDatePickerListener(slZhuangxiuqisuanri);

        String[] arr1 = {"电费", "水费", "燃气费", "物业及能耗费"};
        setMultiSelectListener(slJiafangchegndan, arr1, "甲方承担");

        if (!TextUtils.isEmpty(downloadTotalId)){
            loadOldData(downloadTotalId);
        }
    }


    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.QIANYUESHUJU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",totalId)
                .params("step", "2")
                .execute(new SimpleCallBack<QianYueBeanTwo>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(QianYueBeanTwo data) {
                        slGongyuleixing.setSelect(data.getHouse_type());
                        inputGaizaoxinxi.setText(data.getReform_data());
                        inputHetongleixing.setText(data.getContract_type());
                        slZhuangxiuqisuanri.setText(data.getRenovation_begin());
                        slZhuangxiujiezhiri.setText(data.getRenovation_end());
                        slJiafangchegndan.setMlttySelect(data.getFirst_cost());
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
                .params("step","2")
                .params("total_id",uploadTotalId)
                .params("house_type",getValue(slGongyuleixing))
                .params("reform_data",getValue(inputGaizaoxinxi))
                .params("contract_type",getValue(inputHetongleixing))
                .params("renovation_begin",slZhuangxiuqisuanri.getText().toString())
                .params("renovation_end",slZhuangxiujiezhiri.getText())
                .params("first_cost", slJiafangchegndan.getValue());
        if (!TextUtils.isEmpty(oldId)){
            postRequest.params("old_id",oldId);
        }
                postRequest.execute(new SimpleCallBack<TotalIdBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(TotalIdBean data) {
                       oldId = data.getOld_id();
                       Intent intent = new Intent(getBaseContext(), FangYuanQianYueThreeActivity.class);
                       intent.putExtra("down_id",downloadTotalId);
                       intent.putExtra("up_id",uploadTotalId);
                        startActivity(intent);
                    }
                });
    }

}
