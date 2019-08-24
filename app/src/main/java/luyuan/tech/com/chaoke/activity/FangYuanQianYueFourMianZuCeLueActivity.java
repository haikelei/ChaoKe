package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.CelueParam;
import luyuan.tech.com.chaoke.bean.MianZuCelueBean;
import luyuan.tech.com.chaoke.bean.MianZuCelueParam;
import luyuan.tech.com.chaoke.bean.QianYueBeanFive;
import luyuan.tech.com.chaoke.bean.TotalIdBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.StringUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.MianZuCeLueLayout;
import luyuan.tech.com.chaoke.widget.ZuJinCeLueLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class FangYuanQianYueFourMianZuCeLueActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.rl_add)
    RelativeLayout rlAdd;
    @BindView(R.id.rb0)
    RadioButton rb0;
    private List<MianZuCelueParam> list;
    private String downloadTotalId;
    private String uploadTotalId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_four_mianzu);
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
        rb0.setChecked(true);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        list = new ArrayList<>();

        rlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addZelue();
            }
        });

        if (!TextUtils.isEmpty(downloadTotalId)) {
            loadOldData(downloadTotalId);
        } else {
            addZelue();
        }
    }

    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.MIANZUCELUE_XIAZAI)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id", totalId)
                .execute(new SimpleCallBack<MianZuCelueBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(MianZuCelueBean data) {
                        oldId = data.getTotal_signing_id();
                        if (data.getData() != null && data.getData().size() > 0) {
//                            container.removeAllViews();
                            for (int i = 0; i < data.getData().size(); i++) {
                                String s = data.getData().get(i);
                                MianZuCelueParam param = new MianZuCelueParam();
                                param.setContent(s);
                                list.add(param);
                                addChild(param);
                            }
                        }
                    }
                });

    }

    private void addChild(MianZuCelueParam celueParam) {
        int position = container.getChildCount() - 1;
        MianZuCeLueLayout layout = new MianZuCeLueLayout(getBaseContext(), getSupportFragmentManager());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setTitle("第" + (position + 1) + "年租金策略");
        container.addView(layout, position, layoutParams);
        layout.mInputLayout.setText(celueParam.getContent());
    }

    private String oldId;

    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        PostRequest request = HttpManager.post(HttpManager.MIANZUCELUE_SHANGCHUAN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id", uploadTotalId)
                .params("data", getData())
                .params("type", getType());
        if (!TextUtils.isEmpty(oldId)) {
            request.params("old_id", oldId);
        }

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).getContent();
            if (StringUtils.isZhengInteger(s)) {
                try {
                    Integer integer = Integer.valueOf(s);
                    if (getType().equals("1")){
                        if (integer < 1 || integer > 12) {
                            T.showShort(getBaseContext(), "免租月份必须是1-12之间");
                            return;
                        }
                    }
                } catch (Exception e) {
                    T.showShort(getBaseContext(), "免租月份必须是数字类型");
                    return;
                }
            }else {
                T.showShort(getBaseContext(), "免租月份必须是数字类型并且是整数");
                return;
            }
        }

        request.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String data) {
//                oldId = data.getOld_id();
                if (NetParser.isOk(data)){
                    Intent intent = new Intent(getBaseContext(), FangYuanQianYueFiveActivity.class);
                    intent.putExtra("down_id", downloadTotalId);
                    intent.putExtra("up_id", uploadTotalId);
                    startActivity(intent);
                }

            }
        });
    }

    private String getType() {
        if (rb0.isChecked()) {
            return "1";
        } else {
            return "2";
        }
    }

    private String getData() {
        for (int i = 0; i < container.getChildCount(); i++) {
            if (container.getChildAt(i) instanceof MianZuCeLueLayout) {
                MianZuCeLueLayout zuJinCeLueLayout = (MianZuCeLueLayout) container.getChildAt(i);
                MianZuCelueParam param = list.get(i);
                param.setContent(zuJinCeLueLayout.mInputLayout.getText().toString().trim());
            }
        }

        ArrayList<String> uploadList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            uploadList.add(list.get(i).getContent());
        }
        String s = new Gson().toJson(uploadList);
        return s;
    }

    private void addZelue() {
        int position = container.getChildCount() - 1;
        MianZuCeLueLayout layout = new MianZuCeLueLayout(getBaseContext(), getSupportFragmentManager());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setTitle("第" + (position + 1) + "年租金策略");
        container.addView(layout, position, layoutParams);
        MianZuCelueParam param = new MianZuCelueParam();
        list.add(param);
    }

}
