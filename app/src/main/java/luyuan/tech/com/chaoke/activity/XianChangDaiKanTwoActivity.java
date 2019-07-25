package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.StringDataResponse;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.Constant;
import luyuan.tech.com.chaoke.utils.SettingManager;
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
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_youxiao)
    TextView tvYouxiao;
    @BindView(R.id.tv_bianhao)
    TextView tvBianhao;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private String id;
    private String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xianchang_daikan2);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Intent intent = getIntent();
            id = intent.getStringExtra("id");
            address = intent.getStringExtra("address");
            if (!TextUtils.isEmpty(address)){
                tvContent.setText(address);
            }
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.moren_touxiang);
        Glide.with(this).load(Constant.IMAGE_PRE + UserInfoUtils.getInstance().getAvatar()).apply(requestOptions).into(ivAvatar);
        tvName.setText(UserInfoUtils.getInstance().getUserName());
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

    private String tenant_id;

    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        PostRequest request = HttpManager.post(HttpManager.DAIKAN_YUEKAN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("type", "2")
                .params("rent_id", id)
                .params("tenant_id", SettingManager.getInstance().getZuKeDetailBean().getId() + "")
                .params("result", ((int) slXuanzejieguo.getTag() + 1) + "")
                .params("desc", etInput.getText().toString().trim());
//        if (TextUtils.isEmpty(id)) {
//            request.params("tenant_id", tenant_id);
//        }
        request.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String data) {
                if (NetParser.isOk(data)) {
                    T.showShort(getBaseContext(), "提交成功");
                    StringDataResponse response = NetParser.parse(data, StringDataResponse.class);
                    tenant_id = response.getData();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                } else {
                    T.showShort(getBaseContext(), "提交失败");
                }
            }
        });
    }


}
