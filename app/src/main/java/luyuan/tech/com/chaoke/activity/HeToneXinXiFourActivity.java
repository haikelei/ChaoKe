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
    @BindView(R.id.sl_jiaofuriqi)
    SelectLayout slJiaofuriqi;
    @BindView(R.id.input_dianfeifeng)
    InputLayout inputDianfeifeng;
    @BindView(R.id.input_dianfeigu)
    InputLayout inputDianfeigu;
    @BindView(R.id.input_dianlihuhao)
    InputLayout inputDianlihuhao;
    @BindView(R.id.input_zongdian)
    InputLayout inputZongdian;
    @BindView(R.id.input_zongbeiqi)
    InputLayout inputZongbeiqi;
    @BindView(R.id.sl_jiafangchegndan)
    SelectLayout slJiafangchegndan;
    @BindView(R.id.num_dianshi)
    InputLayout numDianshi;
    @BindView(R.id.num_kongtiao)
    InputLayout numKongtiao;
    @BindView(R.id.num_bingxiang)
    InputLayout numBingxiang;
    @BindView(R.id.num_xiyiji)
    InputLayout numXiyiji;
    @BindView(R.id.num_hongganji)
    InputLayout numHongganji;
    @BindView(R.id.num_weibolu)
    InputLayout numWeibolu;
    @BindView(R.id.num_meiqizao)
    InputLayout numMeiqizao;
    @BindView(R.id.num_diancilu)
    InputLayout numDiancilu;
    @BindView(R.id.num_chuang)
    InputLayout numChuang;
    @BindView(R.id.num_chuangdian)
    InputLayout numChuangdian;
    @BindView(R.id.num_shafa)
    InputLayout numShafa;
    @BindView(R.id.num_yigui)
    InputLayout numYigui;
    @BindView(R.id.num_shuzhuo)
    InputLayout numShuzhuo;
    @BindView(R.id.num_yizi)
    InputLayout numYizi;
    @BindView(R.id.num_chuanglian)
    InputLayout numChuanglian;
    @BindView(R.id.num_chuangtougui)
    InputLayout numChuangtougui;
    @BindView(R.id.num_ditan)
    InputLayout numDitan;
    @BindView(R.id.num_xiegui)
    InputLayout numXiegui;
    @BindView(R.id.num_canzhuo)
    InputLayout numCanzhuo;
    @BindView(R.id.num_canyi)
    InputLayout numCanyi;
    @BindView(R.id.num_chaji)
    InputLayout numChaji;
    @BindView(R.id.num_aigui)
    InputLayout numAigui;
    @BindView(R.id.num_yinshuiji)
    InputLayout numYinshuiji;
    @BindView(R.id.num_reshuiqi)
    InputLayout numReshuiqi;
    @BindView(R.id.num_menka)
    InputLayout numMenka;
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
        setDatePickerListener(slXueli);
        setDatePickerListener(slJiaofuriqi);
        String[] arr = {"电费","水费","燃气","物业及能耗费"};
        setSelectLListener(slJiafangchegndan,arr);
    }

    private void loadData() {
        HttpManager.post(HttpManager.HETONG_TOUR)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("con_id",bean.getCon_id())
                .params("cons_id",bean.getCons_id())
                .params("phone",inputShouji.getText().trim())
                .params("email",inputYouxiang.getText().trim())
                .params("education",slXueli.getText())
                .params("urgent_man",inputJinjixingming.getText().trim())
                .params("urgent_phone",inputJinjishouji.getText())
                .params("other_msg",slJiaofuriqi.getText())
                .params("side_letter",etBuchogntiaokuan.getText().toString().trim())
                .params("elect_peak_value",inputDianfeifeng.getText().trim())
                .params("elect_valley_value",inputDianfeigu.getText().trim() )
                .params("elect_number",inputDianlihuhao.getText().trim())
                .params("elect_all",inputZongdian.getText().trim())
                .params("gas_value",inputZongbeiqi.getText().trim())
                .params("water_all","")
                .params("first_party_pay",slJiafangchegndan.getText())
                .params("handover_list","")
                .execute(new SimpleCallBack<HeTongIdBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HeTongIdBean data) {
                        startActivity(new Intent(getBaseContext(), HeToneXinXiFiveActivity.class));
                    }
                });
    }

}
