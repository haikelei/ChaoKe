package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.MainActivity;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.KaiFaFangYuanAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.bean.StringDataResponse;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.Constant;
import luyuan.tech.com.chaoke.utils.SettingManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class YuYueDaiKanActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    ArrayList<HouseBean> data;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.iv_name)
    TextView ivName;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.sl_yyueshijian)
    SelectLayout slYyueshijian;
    @BindView(R.id.input_jianmiandizhi)
    InputLayout inputJianmiandizhi;
    @BindView(R.id.cb_duanxin)
    CheckBox cbDuanxin;
    @BindView(R.id.input_shoujihao)
    InputLayout inputShoujihao;
    @BindView(R.id.recycler)
    RecyclerView recycler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyue_daikan);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            data = getIntent().getParcelableArrayListExtra("data");
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
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.moren_touxiang);
        Glide.with(this).load(Constant.IMAGE_PRE + UserInfoUtils.getInstance().getAvatar()).apply(requestOptions).into(ivAvatar);
        ivName.setText(UserInfoUtils.getInstance().getUserName());

        setDatePickerListener(slYyueshijian);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new KaiFaFangYuanAdapter(data));
    }

    private String id;

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            stringBuilder.append(data.get(i).getId());
            if (i != data.size() - 1) {
                stringBuilder.append(",");
            }
        }
        PostRequest request = HttpManager.post(HttpManager.DAIKAN_YUEKAN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("type", "1")
                .params("tenant_id", SettingManager.getInstance().getZuKeDetailBean().getId()+"")
                .params("rent_id", stringBuilder.toString())
                .params("time", slYyueshijian.getText().toString())
                .params("see_address", getValue(inputJianmiandizhi))
                .params("is_msg", cbDuanxin.isChecked() + "")
                .params("phone", getValue(inputShoujihao));
        request.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String data) {
                if (NetParser.isOk(data)){
                    T.showShort(getBaseContext(), "提交成功");
                    StringDataResponse response = NetParser.parse(data, StringDataResponse.class);
                    id = response.getData();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                }else {
                    StringDataResponse response = NetParser.parse(data, StringDataResponse.class);
                    T.showShort(getBaseContext(), "提交失败:"+response.getMsg());
                }
            }
        });
    }

}
