package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.MainActivity;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.StringDataResponse;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class WeiTuoXuQianActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.input_kehuxingming)
    InputLayout inputKehuxingming;
    @BindView(R.id.input_wuyedizhi)
    InputLayout inputWuyedizhi;
    @BindView(R.id.input_lianxidianhua)
    InputLayout inputLianxidianhua;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuqian);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
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
    }

    private void loadData() {
        HttpManager.post(HttpManager.XUANQIAN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id",id)
                .params("username",getValue(inputKehuxingming))
                .params("address", getValue(inputWuyedizhi))
                .params("phone",getValue(inputLianxidianhua))
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(String data) {

                        if (NetParser.isOk(data)){
                            startActivity(new Intent(getActivity(),MainActivity.class));
                            T.showShort(getActivity(),"续签成功");
                        }else {
                            StringDataResponse stringDataResponse = NetParser.parse(data,StringDataResponse.class);
                            T.showShort(getActivity(),stringDataResponse.getMsg());
                        }


                    }
                });
    }

}
