package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.QianYueBeanFour;
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


public class FangYuanQianYueFourActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.input_meiyuefangzu)
    InputLayout inputMeiyuefangzu;
    @BindView(R.id.sl_shoucifukuanri)
    SelectLayout slShoucifukuanri;
    @BindView(R.id.sl_shifoudaichewei)
    SelectLayout slShifoudaichewei;
    @BindView(R.id.sl_mianzukaishiri)
    SelectLayout slMianzukaishiri;
    @BindView(R.id.sl_mianzudaoqiri)
    SelectLayout slMianzudaoqiri;
    @BindView(R.id.sl_zujinzhifufangshi)
    SelectLayout slZujinzhifufangshi;
    @BindView(R.id.et_more)
    EditText etMore;
    private String downloadTotalId;
    private String uploadTotalId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_four);
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
        setDatePickerListener(slMianzudaoqiri);
        setDatePickerListener(slMianzukaishiri);
        setDatePickerListener(slShoucifukuanri);
        String[] arr = {"带车位", "不带车位"};
        setSelectLListener(slShifoudaichewei, arr, "是否带车位");

        String[] arr1 = {"押一付一", "押一付二", "押一付三"};
        setSelectLListener(slZujinzhifufangshi, arr1);
        if (!TextUtils.isEmpty(downloadTotalId)){
            loadOldData(downloadTotalId);
        }
    }


    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.QIANYUESHUJU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",totalId)
                .params("step", "4")
                .execute(new SimpleCallBack<QianYueBeanFour>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(QianYueBeanFour data) {
                        oldId = data.getOld_id();
                        inputMeiyuefangzu.setText(data.getRent_price());
                        slShoucifukuanri.setText(data.getFirst_pay());
                        slShifoudaichewei.setSelect(data.getIs_parking());
                        slMianzukaishiri.setText(data.getRent_begin());
                        slMianzudaoqiri.setText(data.getRent_end());
                        slZujinzhifufangshi.setSelect(data.getPay_type());
                        etMore.setText(data.getOther_desc());

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
                .params("total_id", uploadTotalId)
                .params("rent_price", getValue(inputMeiyuefangzu))
                .params("first_pay",slShoucifukuanri.getText())
                .params("is_parking", getValue(slShifoudaichewei))
                .params("rent_begin", slMianzukaishiri.getText())
                .params("rent_end",slMianzudaoqiri.getText())
                .params("pay_type", getValue(slZujinzhifufangshi))
                .params("other_desc", etMore.getText().toString().trim())
                .params("step","4");
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
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueFiveActivity.class);
                intent.putExtra("down_id",downloadTotalId);
                intent.putExtra("up_id",uploadTotalId);
                startActivity(intent);
            }
        });
    }

}
