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
import luyuan.tech.com.chaoke.bean.QianYueBeanNien;
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


public class FangYuanQianYueNineActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.sl_shoukuanrenleixing)
    SelectLayout slShoukuanrenleixing;
    @BindView(R.id.sl_zhengjianleixing)
    SelectLayout slZhengjianleixing;
    @BindView(R.id.input_xinging)
    InputLayout inputXinging;
    @BindView(R.id.input_zhengjianhaoma)
    InputLayout inputZhengjianhaoma;
    @BindView(R.id.sl_zhanghaoleixing)
    SelectLayout slZhanghaoleixing;
    @BindView(R.id.input_shoukuanzhenghao)
    InputLayout inputShoukuanzhenghao;
    @BindView(R.id.input_shoukuanjigou)
    InputLayout inputShoukuanjigou;
    @BindView(R.id.input_shoukuanzhihang)
    InputLayout inputShoukuanzhihang;
    private String downloadTotalId;
    private String uploadTotalId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_nine);
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

        String[] arr = {"产权人","共有产权人","出租人"};
        setSelectLListener(slShoukuanrenleixing,arr,"收款人类型");
        String[] arr1 = {"居民身份证","护照","军人证"};
        setSelectLListener(slZhengjianleixing,arr1,"证件类型");
        String[] arr2 = {"银行卡"};
        setSelectLListener(slZhanghaoleixing,arr2,"账号类型");
        if (!TextUtils.isEmpty(downloadTotalId)){
            loadOldData(downloadTotalId);
        }
    }

    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.QIANYUESHUJU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",totalId)
                .params("step", "12")
                .execute(new SimpleCallBack<QianYueBeanNien>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(QianYueBeanNien data) {
                        oldId = data.getOld_id();
                        slZhanghaoleixing.setSelect("1");
                        slZhengjianleixing.setSelect(data.getCard_type());
                        slShoukuanrenleixing.setSelect(data.getPeople_type());
                        inputXinging.setText(data.getUsername());
                        inputZhengjianhaoma.setText(data.getCard_num());
                        inputShoukuanzhenghao.setText(data.getNumber_num());
                        inputShoukuanjigou.setText(data.getMechanism());
                        inputShoukuanzhihang.setText(data.getBranch());
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
                .params("step","12")
                .params("people_type",getValue(slShoukuanrenleixing))
                .params("card_type", getValue(slZhengjianleixing))
                .params("username", getValue(inputXinging))
                .params("card_num", getValue(inputZhengjianhaoma))
                .params("number_type", "2")
                .params("number_num",getValue(inputShoukuanzhenghao) )
                .params("mechanism", getValue(inputShoukuanjigou))
                .params("branch", getValue(inputShoukuanzhihang));
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
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueTenActivity.class);
                intent.putExtra("down_id",downloadTotalId);
                intent.putExtra("up_id",uploadTotalId);
                startActivity(intent);
            }
        });
    }

}
