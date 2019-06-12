package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ItemBean;
import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectDialogFragment;
import luyuan.tech.com.chaoke.widget.SelectLayout;

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
    @BindView(R.id.sl_huxing)
    SelectLayout slHuxing;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house_other_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.sl_huxing, R.id.sl_chaoxiang, R.id.sl_zulingongsi, R.id.sl_daoqiri, R.id.sl_kanfangri, R.id.sl_kanfangfangshi,R.id.btn_submmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.sl_huxing:

                break;
            case R.id.sl_chaoxiang:
                break;
            case R.id.sl_zulingongsi:
                String[] arr = {"房东出租", "58同城", "A公司", "B公司", "C公司", "D公司"};
                createDialog(arr, slZulingongsi);
                break;
            case R.id.sl_daoqiri:
                createDataPickerDialog(true,slDaoqiri);
                break;
            case R.id.sl_kanfangri:
                createDataPickerDialog(false,slKanfangri);
                break;
            case R.id.sl_kanfangfangshi:
                String[] arr1 = {"房东出租", "预约", "直接", "借钥", "有钥", "智能锁"};
                createDialog(arr1, slKanfangfangshi);
                break;
            case R.id.btn_submmit:

                break;
        }
    }

    private void createDataPickerDialog(final boolean single, final SelectLayout selectLayout){
        DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.create(single);
        datePickerDialogFragment.show(getSupportFragmentManager(),"date");
        datePickerDialogFragment.setOnSelectListener(new DatePickerDialogFragment.OnDateSelectListener() {
            @Override
            public void onSelect(List<String> list) {
                if (single){
                    selectLayout.setText(list.get(0));
                }else {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < list.size(); i++) {
                        stringBuffer.append(list.get(i));
                        if (i!=list.size()-1){
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