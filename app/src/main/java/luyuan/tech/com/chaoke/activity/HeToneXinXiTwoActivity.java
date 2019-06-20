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
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HeTongIdBean;
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


public class HeToneXinXiTwoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.input_zujin)
    InputLayout inputZujin;
    @BindView(R.id.sl_fukuankaishi)
    SelectLayout slFukuankaishi;
    @BindView(R.id.sl_fukuanjieshu)
    SelectLayout slFukuanjieshu;
    @BindView(R.id.sl_yajinleixing)
    SelectLayout slYajinleixing;
    @BindView(R.id.input_yajin)
    InputLayout inputYajin;
    @BindView(R.id.sl_fukuanfangshi)
    SelectLayout slFukuanfangshi;
    @BindView(R.id.sl_fenqijiekuan)
    SelectLayout slFenqijiekuan;
    @BindView(R.id.input_fuwuguanjia)
    InputLayout inputFuwuguanjia;
    private HeTongIdBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hetong_xinxi_two);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            bean = (HeTongIdBean) getIntent().getSerializableExtra("data");
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
        setDatePickerListener(slFukuanjieshu);
        setDatePickerListener(slFukuankaishi);
        setDatePickerListener(slFenqijiekuan);

        String[] arr = {"压1","压2","无押金"};
        setSelectLListener(slYajinleixing,arr,"押金类型");

        String[] arr1 = {"一次性结清","分期"};
        setSelectLListener(slFukuanfangshi,arr1,"付款方式");
    }

    private void loadData() {
        HttpManager.post(HttpManager.HETONG_TWO)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("con_id", bean.getCon_id())
                .params("cons_id", bean.getCons_id())
                .params("price", inputZujin.getText().trim())
                .params("pay_starttime", slFukuankaishi.getText().trim())
                .params("pay_endtime", slFukuanjieshu.getText())
                .params("deposit_type",((int)slYajinleixing.getTag()+1)+"")
                .params("deposit_price", inputYajin.getText().trim())
                .params("pay_type",((int)slFukuanfangshi.getTag()+1)+"")
                .params("by_stages_endtime",slFenqijiekuan.getText())
                .params("other_price",inputFuwuguanjia.getText().trim())
                .execute(new SimpleCallBack<HeTongIdBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HeTongIdBean data) {
                        Intent intent = new Intent(getBaseContext(), HeToneXinXiThreeActivity.class);
                        intent.putExtra("data",bean);
                        startActivity(intent);
                    }
                });
    }

}
