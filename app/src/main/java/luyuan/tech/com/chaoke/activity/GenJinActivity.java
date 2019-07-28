package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.MainActivity;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class GenJinActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.sl_daikanjilu)
    SelectLayout slDaikanjilu;
    @BindView(R.id.sl_fangyuuanzhuangtai)
    SelectLayout slFangyuuanzhuangtai;
    @BindView(R.id.et_more)
    EditText etMore;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genjin);
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
        String[] arr={"普通跟进","推荐收房","意向跟进","面谈","无法初勘","回访跟进"};
        setSelectLListener(slDaikanjilu,arr,"带看记录");

        String[] arr1={"业主待用 ","非自营在租","业主自用","无效"};
        setSelectLListener(slFangyuuanzhuangtai,arr1,"房源状态");
    }

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        HttpManager.post(HttpManager.GENJIN_FANGYUAN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id",id)
                .params("follow_type",((int)slDaikanjilu.getTag()+1)+"")
                .params("desc",etMore.getText().toString().trim())
                .params("rent_status",((int)slFangyuuanzhuangtai.getTag()+1)+"")
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(String data) {
                        showSuccess();
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                    }
                });
    }


}
