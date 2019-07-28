package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HeTongIdBean;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
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


public class HeToneXinXiFourActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.input_shouji)
    InputLayout inputShouji;
    @BindView(R.id.input_youxiang)
    InputLayout inputYouxiang;
    @BindView(R.id.sl_xueli)
    SelectLayout slXueli;
    @BindView(R.id.input_jinjixingming)
    InputLayout inputJinjixingming;
    @BindView(R.id.input_jinjishouji)
    InputLayout inputJinjishouji;
    @BindView(R.id.sl_jiafangchegndan)
    SelectLayout slJiafangchegndan;
    @BindView(R.id.et_buchogntiaokuan)
    EditText etBuchogntiaokuan;
    private HeTongIdBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hetong_xinxi_four);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            bean = (HeTongIdBean) getIntent().getSerializableExtra("data");
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

        String[] arr = {"电费","水费","燃气","物业及能耗费"};
        setSelectLListener(slJiafangchegndan,arr,"甲方承担");

        String[] arr1 = {"专科以下","专科","本科","研究生","博士"};
        setSelectLListener(slXueli,arr1,"学历");
    }

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }

        HttpManager.post(HttpManager.HETONG_TOUR)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("con_id",bean.getCon_id())
                .params("cons_id",bean.getCons_id())
                .params("phone",inputShouji.getText().trim())
                .params("email",inputYouxiang.getText().trim())
                .params("education",slXueli.getText().toString())
                .params("urgent_man",inputJinjixingming.getText().trim())
                .params("urgent_phone",inputJinjishouji.getText())
                .params("side_letter",etBuchogntiaokuan.getText().toString().trim())
                .params("first_party_pay",slJiafangchegndan.getValue())
                .execute(new SimpleCallBack<HeTongIdBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HeTongIdBean data) {
                        Intent intent = new Intent(getBaseContext(), HeToneXinXiFiveActivity.class);
                        intent.putExtra("data",bean);
                        startActivity(intent);
                    }
                });
    }

}
