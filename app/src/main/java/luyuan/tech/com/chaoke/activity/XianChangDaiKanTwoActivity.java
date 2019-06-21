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
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.AppStorageUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class XianChangDaiKanTwoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.sl_xuanzejieguo)
    SelectLayout slXuanzejieguo;
    @BindView(R.id.et_input)
    EditText etInput;
    private String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xianchang_daikan2);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Intent intent = getIntent();
            id = intent.getStringExtra("id");
        }
        String[] arr = {"考虑", "不考虑", "下定金", "电子签约", "其他结果"};
        setSelectLListener(slXuanzejieguo, arr);
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
        HttpManager.post(HttpManager.DAIKAN_YUEKAN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("type", "2")
                .params("tenant_id", AppStorageUtils.getZuKeDetailBean().getId() + "")
                .params("rent_id", id)
                .params("result", ((int) slXuanzejieguo.getTag() + 1) + "")
                .params("desc",etInput.getText().toString().trim())
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(String data) {
                        T.showShort(getBaseContext(), "提交成功");
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                    }
                });
    }


}
