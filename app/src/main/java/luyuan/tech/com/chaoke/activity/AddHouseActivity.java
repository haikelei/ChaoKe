package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ItemBean;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectDialogFragment;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class AddHouseActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.sl_unity_name)
    SelectLayout slUnityName;
    @BindView(R.id.input_unity_address)
    InputLayout inputUnityAddress;
    @BindView(R.id.sl_house_from)
    SelectLayout slHouseFrom;
    @BindView(R.id.sl_house_state)
    SelectLayout slHouseState;
    @BindView(R.id.input_host_name)
    InputLayout inputHostName;
    @BindView(R.id.input_host_tel)
    InputLayout inputHostTel;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.sl_unity_name, R.id.sl_house_from, R.id.sl_house_state, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.sl_unity_name:
                break;
            case R.id.sl_house_from:
                String[] arr = {"中介合作","转介绍","老客户","网络端口","地推","房东上门","名单获取","销冠","其他"};
                createDialog(arr,slHouseFrom);
                break;
            case R.id.sl_house_state:
                String[] arr1 = {"业主待租","非自营在租","业主自用"};
                createDialog(arr1,slHouseState);
                break;
            case R.id.btn_next:
                startActivity(new Intent(getBaseContext(),AddHouseOtherInfoActivity.class));
                onBackPressed();
                break;
        }
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
