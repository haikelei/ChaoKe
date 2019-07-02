package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ItemBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectDialogFragment;
import luyuan.tech.com.chaoke.widget.SelectLayout;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class AddHouseOtherInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.input_money)
    InputLayout inputMoney;
    @BindView(R.id.input_size)
    InputLayout inputSize;
    @BindView(R.id.sl_chaoxiang)
    SelectLayout slChaoxiang;
    @BindView(R.id.sl_zulingongsi)
    SelectLayout slZulingongsi;
    @BindView(R.id.sl_daoqiri)
    SelectLayout slDaoqiri;
    @BindView(R.id.sl_kanfangri)
    SelectLayout slKanfangri;
    @BindView(R.id.sl_kanfangfangshi)
    SelectLayout slKanfangfangshi;
    @BindView(R.id.et_beizhu)
    EditText etBeizhu;
    @BindView(R.id.input_shi)
    InputLayout inputShi;
    @BindView(R.id.input_ting)
    InputLayout inputTing;
    @BindView(R.id.input_wei)
    InputLayout inputWei;
    @BindView(R.id.btn_submmit)
    Button btnSubmmit;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        setContentView(R.layout.activity_add_house_other_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.sl_chaoxiang, R.id.sl_zulingongsi, R.id.sl_daoqiri, R.id.sl_kanfangri, R.id.sl_kanfangfangshi, R.id.btn_submmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.sl_chaoxiang:
                String[] arrChaoxiang = {"朝南", "朝北", "朝东", "朝西"};
                createDialog(arrChaoxiang, slChaoxiang);
                break;
            case R.id.sl_zulingongsi:
                String[] arr = {"房东出租", "58同城", "A公司", "B公司", "C公司", "D公司"};
                createDialog(arr, slZulingongsi);
                break;
            case R.id.sl_daoqiri:
                createDataPickerDialog(true, slDaoqiri);
                break;
            case R.id.sl_kanfangri:
                createDataPickerDialog(false, slKanfangri);
                break;
            case R.id.sl_kanfangfangshi:
                String[] arr1 = {"房东出租", "预约", "直接", "借钥", "有钥", "智能锁"};
                createDialog(arr1, slKanfangfangshi);
                break;
            case R.id.btn_submmit:
                loadData();
                break;
        }
    }

    private void loadData() {
        if (TextUtils.isEmpty(inputShi.getText().toString())||TextUtils.isEmpty(inputTing.getText().toString())||TextUtils.isEmpty(inputWei.getText().toString())){
            T.showShort(getActivity(),"请完整输入户型,若没有可填写0");
            return;
        }
        if (!checkEmptyInfo()) {
            return;
        }
        HttpManager.post(HttpManager.FABUTWO)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("ren_id", id)
                .params("long_price", inputMoney.getText().trim().trim())
                .params("area", inputSize.getText().trim().trim())
                .params("apartment", "")
                .params("orientation", slChaoxiang.getText().toString())
                .params("company_id", slZulingongsi.getText().toString())
                .params("expiretime", slDaoqiri.getText().toString())
                .params("see_time", slKanfangri.getText().toString())
                .params("see_type", slKanfangfangshi.getText().toString())
                .params("describe", etBeizhu.getText().toString().trim())
                .params("room",inputShi.getText().toString())
                .params("office",inputTing.getText().toString())
                .params("guard",inputWei.getText().toString())
                .execute(new SimpleCallBack<List>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List list) {
                        setResult(RESULT_OK);
                        finish();

                    }
                });
    }

    private void createDataPickerDialog(final boolean single, final SelectLayout selectLayout) {
        DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.create(single);
        datePickerDialogFragment.show(getSupportFragmentManager(), "date");
        datePickerDialogFragment.setOnSelectListener(new DatePickerDialogFragment.OnDateSelectListener() {
            @Override
            public void onSelect(List<String> list) {
                if (single) {
                    selectLayout.setText(list.get(0));
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < list.size(); i++) {
                        stringBuffer.append(list.get(i));
                        if (i != list.size() - 1) {
                            stringBuffer.append(" ");
                        }
                    }
                    selectLayout.setText(stringBuffer.toString());
                }

            }
        });
    }

    private void createDialog(String[] arr, final SelectLayout sl) {
        ArrayList<ItemBean> datas = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setTitle(arr[i]);
            itemBean.setChecked(sl.getText().equals(arr[i]));
            datas.add(itemBean);
        }
        SelectDialogFragment dialogFragment = SelectDialogFragment.create(datas);
        dialogFragment.show(getSupportFragmentManager());
        dialogFragment.setOnSelectListener(new SelectDialogFragment.OnSelectListener() {
            @Override
            public void onSelect(String s) {
                sl.setText(s);
            }
        });
    }

}
