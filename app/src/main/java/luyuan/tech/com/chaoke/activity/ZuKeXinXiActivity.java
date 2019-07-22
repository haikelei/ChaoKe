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
import luyuan.tech.com.chaoke.bean.StringDataResponse;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.AppStorageUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/18
 * @describe:
 */


public class ZuKeXinXiActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_submmit)
    Button btnSubmmit;
    @BindView(R.id.input_name)
    InputLayout inputName;
    @BindView(R.id.sl_xingbie)
    SelectLayout slXingbie;
    @BindView(R.id.input_shouji)
    InputLayout inputShouji;
    @BindView(R.id.sl_zukelaiyuan)
    SelectLayout slZukelaiyuan;
    @BindView(R.id.sl_zukedengji)
    SelectLayout slZukedengji;
    @BindView(R.id.sl_shehuizizhi)
    SelectLayout slShehuizizhi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuke_xinxi);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnSubmmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        String[] arr = {"男", "女"};
        setSelectLListener(slXingbie, arr, "性别");

        String[] arr1 = {"安居客", "贝壳", "老带新", "58同城", "自己开发"};
        setSelectLListener(slZukelaiyuan, arr1, "租客来源");

        String[] arr2 = {"A类", "B类", "C类", "未分类"};
        setSelectLListener(slZukedengji, arr2, "租客等级");

        String[] arr3 = {"技术员", "老板", "个体户", "其他"};
        setSelectLListener(slShehuizizhi, arr3, "社会资质");

    }

    private String id;
    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        PostRequest request = HttpManager.post(HttpManager.ZUKEXINXI_ONE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("username", inputName.getText().trim())
                .params("sex", ((int) slXingbie.getTag() + 1) + "")
                .params("phone", inputShouji.getText().trim())
                .params("source", ((int) slZukelaiyuan.getTag() + 1) + "")
                .params("grade", ((int) slZukedengji.getTag() + 1) + "")
                .params("social", ((int) slShehuizizhi.getTag() + 1) + "");
        if (!TextUtils.isEmpty(id)) {
            request.params("tenant_id", id);
        }
        request.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String data) {
                if (NetParser.isOk(data)){
                    Intent intent = new Intent(getBaseContext(), ZuKeXinXiQiTaActivity.class);
                    StringDataResponse stringDataResponse = NetParser.parse(data, StringDataResponse.class);
                    id = stringDataResponse.getData();
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else {
                    T.showShort(getBaseContext(), "提交失败");
                }

            }
        });
    }


}
