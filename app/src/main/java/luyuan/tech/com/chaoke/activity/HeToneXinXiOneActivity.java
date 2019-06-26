package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HeTongIdBean;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.AppStorageUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class HeToneXinXiOneActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.iv_name)
    TextView ivName;
    @BindView(R.id.sl_qianyueriqi)
    SelectLayout slQianyueriqi;
    @BindView(R.id.sl_shoucifukuan)
    SelectLayout slShoucifukuan;
    @BindView(R.id.sl_chengzuqisuan)
    SelectLayout slChengzuqisuan;
    @BindView(R.id.sl_chengzujiezhi)
    SelectLayout slChengzujiezhi;
    @BindView(R.id.sl_fukuanfangshi)
    SelectLayout slFukuanfangshi;
    @BindView(R.id.sl_yongtu)
    SelectLayout slYongtu;
    @BindView(R.id.input_tongxundizhi)
    InputLayout inputTongxundizhi;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hetong_xinxi_one);
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

        setDatePickerListener(slQianyueriqi);
        setDatePickerListener(slShoucifukuan);
        setDatePickerListener(slChengzuqisuan);
        setDatePickerListener(slChengzujiezhi);

        String[] arr = {"一次性付款","年付","月付","季付","半年付"};
        setSelectLListener(slFukuanfangshi,arr,"付款方式");

        String[] arr1 = {"居住","商业"};
        setSelectLListener(slYongtu,arr1,"用途");





    }

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        int i = (int) slFukuanfangshi.getTag();
        HttpManager.post(HttpManager.HETONG_ONE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("rent_id", id)
                .params("phone",UserInfoUtils.getInstance().getPhone())
                .params("address",inputTongxundizhi.getText())
                .params("lessee", AppStorageUtils.getInstance().getZuKeDetailBean().getId()+"")
                .params("payway",String.valueOf(i+1))
                .params("purpose",slYongtu.getText().equals("居住")?"1":"2")
                .params("postal_address",inputTongxundizhi.getText())
                .params("first_paytime",slShoucifukuan.getText())
                .params("lessee_starttime",slChengzuqisuan.getText())
                .params("lessee_endtime",slChengzujiezhi.getText())
                .params("subtime",slQianyueriqi.getText())
                .execute(new SimpleCallBack<HeTongIdBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HeTongIdBean data) {
                        Intent intent = new Intent(getBaseContext(), HeToneXinXiTwoActivity.class);
                        intent.putExtra("data",data);
                        startActivity(intent);
                    }
                });
    }


}
