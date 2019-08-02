package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.StringDataResponse;
import luyuan.tech.com.chaoke.bean.ZukeOneBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
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
    private String tenant_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuke_xinxi);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });
        btnSubmmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        String[] arr = {"未知","男", "女"};
        setSelectLListener(slXingbie, arr, "性别");

        String[] arr1 = {"安居客", "贝壳", "老带新", "58同城", "自己开发"};
        setSelectLListener(slZukelaiyuan, arr1, "租客来源");

        String[] arr2 = {"A类", "B类", "C类", "未分类"};
        setSelectLListener(slZukedengji, arr2, "租客等级");

        String[] arr3 = {"技术员", "老板", "个体户", "其他"};
        setSelectLListener(slShehuizizhi, arr3, "社会资质");
        if (getIntent()!=null){
            tenant_id = getIntent().getStringExtra("id");
            if (!TextUtils.isEmpty(tenant_id)){
                loadOldData();
            }
        }
    }

    private void loadOldData() {
        HttpManager.post(HttpManager.ZUKEBIANJI_ONE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("tenant_id", tenant_id)
                .execute(new SimpleCallBack<ZukeOneBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(ZukeOneBean data) {
                        inputName.setText(data.getUsername());
                        inputShouji.setText(data.getPhone());
                        slXingbie.setSelect(data.getSex()+"");
                        slZukelaiyuan.setSelect(data.getSource()+"");
                        slZukedengji.setSelect(data.getGrade()+"");
                        slShehuizizhi.setSelect(data.getSocial()+"");
                    }
                });
    }

    private void showDialog() {
        final NormalDialog dialog = new NormalDialog(getActivity());
        dialog.content("退出将丢失填写的信息")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                        onBackPressed();
                    }
                });

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
        if (!TextUtils.isEmpty(tenant_id)) {
            request.params("id", tenant_id);
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
                    if (!TextUtils.isEmpty(tenant_id)){
                        intent.putExtra("tenant_id", tenant_id);
                    }
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else {
                    T.showShort(getBaseContext(), "提交失败");
                }

            }
        });
    }


}
