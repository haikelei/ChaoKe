package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import luyuan.tech.com.chaoke.bean.TotalIdBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.ZuJinCeLueLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class FangYuanQianYueFiveActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.rl_add)
    RelativeLayout rlAdd;
    private List<CelueParam> list;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_five);
        ButterKnife.bind(this);
        if (getIntent()!=null){
            id = getIntent().getStringExtra("id");
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        list = new ArrayList<>();
        addZelue();
        rlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addZelue();
            }
        });


    }

    private String oldId;
    private void loadData() {
        PostRequest request = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id", id)
                .params("data", getData())
                .params("step","5");
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
                Intent intent =new Intent(getBaseContext(), FangYuanQianYueSixActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    private String getData(){
        for (int i = 0; i < container.getChildCount(); i++) {
            if (container.getChildAt(i) instanceof ZuJinCeLueLayout) {
                ZuJinCeLueLayout zuJinCeLueLayout = (ZuJinCeLueLayout) container.getChildAt(i);
                CelueParam param = list.get(i);
                param.setMianzu_jieshu(zuJinCeLueLayout.slMianzujieshuri.getText());
                param.setMianzu_kaishi(zuJinCeLueLayout.slMianzukaishiri.getText());
                param.setWeituo_jieshu(zuJinCeLueLayout.slWeituojieshu.getText());
                param.setWeituo_kaishi(zuJinCeLueLayout.slWeituokaishi.getText());
                param.setMoney(zuJinCeLueLayout.inputMeiyuezujin.getText().toString().trim());
            }
        }
        String s = new Gson().toJson(list);
        return s;
    }

    private void addZelue() {
        int position = container.getChildCount() - 1;
        ZuJinCeLueLayout layout = new ZuJinCeLueLayout(getBaseContext(), getSupportFragmentManager());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setTitle("第"+(position+1)+"年租金策略");
        container.addView(layout, position, layoutParams);
        CelueParam param = new CelueParam();
        list.add(param);
    }

}
