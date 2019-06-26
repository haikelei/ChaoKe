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
    @BindView(R.id.input_dianfeifeng)
    InputLayout inputDianfeifeng;
    @BindView(R.id.input_dianfeigu)
    InputLayout inputDianfeigu;
    @BindView(R.id.input_dianlihuhao)
    InputLayout inputDianlihuhao;
    @BindView(R.id.input_zongdian)
    InputLayout inputZongdian;
    @BindView(R.id.input_zongmeiqi)
    InputLayout inputZongmeiqi;
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
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_two);
        ButterKnife.bind(this);
        if (getIntent()!=null){
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
        String[] arr = {"普通公寓","商用公寓","住宅公寓","复式公寓","品牌公寓"};
        setSelectLListener(slGongyuleixing,arr,"公寓类型");

        String[] arr1 = {"电费","水费","燃气费","物业及能耗费"};
        setSelectLListener(slJiafangchegndan,arr1,"甲方承担");

        setDatePickerListener(slZhuangxiujiezhiri);
        setDatePickerListener(slZhuangxiuqisuanri);

    }

    private String oldId;
    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        StringBuilder handoverList = new StringBuilder();
        handoverList.append(numDianshi.getText()+",")
                .append(numDianshi.getText()+",")
                .append(numKongtiao.getText()+",")
                .append(numBingxiang.getText()+",")
                .append(numXiyiji.getText()+",")
                .append(numHongganji.getText()+",")
                .append(numWeibolu.getText()+",")
                .append(numMeiqizao.getText()+",")
                .append(numDiancilu.getText()+",")
                .append(numChuang.getText()+",")
                .append(numChuangdian.getText()+",")
                .append(numShafa.getText()+",")
                .append(numYigui.getText()+",")
                .append(numShuzhuo.getText()+",")
                .append(numYizi.getText()+",")
                .append(numChuanglian.getText()+",")
                .append(numChuangtougui.getText()+",")
                .append(numDitan.getText()+",")
                .append(numXiegui.getText()+",")
                .append(numCanzhuo.getText()+",")
                .append(numCanyi.getText()+",")
                .append(numChaji.getText()+",")
                .append(numAigui.getText()+",")
                .append(numYinshuiji.getText()+",")
                .append(numReshuiqi.getText()+",")
                .append(numMenka.getText() );
        PostRequest postRequest = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("step","2")
                .params("total_id",id)
                .params("house_type",getValue(slGongyuleixing))
                .params("reform_data",getValue(inputGaizaoxinxi))
                .params("contract_type",getValue(inputHetongleixing))
                .params("renovation_begin",getValue(slZhuangxiuqisuanri))
                .params("renovation_end",getValue(slZhuangxiujiezhiri))
                .params("electric_max",getValue(inputDianfeifeng))
                .params("electric_min", getValue(inputDianfeigu))
                .params("electric_num",getValue(inputDianlihuhao))
                .params("total_electric", getValue(inputZongdian))
                .params("total_coal",getValue(inputZongmeiqi))
                .params("first_cost", getValue(slJiafangchegndan))
                .params("config",handoverList.toString());
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
                       intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
    }

}
