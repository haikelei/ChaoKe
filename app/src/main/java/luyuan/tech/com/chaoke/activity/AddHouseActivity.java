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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ItemBean;
import luyuan.tech.com.chaoke.bean.StringDataResponse;
import luyuan.tech.com.chaoke.bean.XiaoQuBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectDialogFragment;
import luyuan.tech.com.chaoke.widget.SelectLayout;

import static com.zhouyou.http.EasyHttp.getContext;

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
    @BindView(R.id.input_lou)
    InputLayout inputLou;
    @BindView(R.id.input_danyuan)
    InputLayout inputDanyuan;
    @BindView(R.id.input_hao)
    InputLayout inputHao;
    @BindView(R.id.sl_chuzufangshi)
    SelectLayout slChuzufangshi;
    private String houseId;
    private XiaoQuBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);
        ButterKnife.bind(this);

        String[] arr = {"中介合作", "转介绍", "老客户", "网络端口", "地推", "房东上门", "名单获取", "销冠", "其他"};
        setSelectLListener(slHouseFrom,arr);

        String[] arr1 = {"业主待租", "非自营在租", "业主自用"};
        setSelectLListener(slHouseState,arr1);

        String[] arr2 = {"整租", "分租"};
        setSelectLListener(slChuzufangshi,arr2);
    }

    @OnClick({R.id.iv_back, R.id.sl_unity_name, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.sl_unity_name:
                startActivityForResult(new Intent(getActivity(),XuanZeXiaoQuActivity.class),133);
                break;
            case R.id.btn_next:
                loadData();
                break;
        }
    }

    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        PostRequest postRequest = HttpManager.post(HttpManager.FABUONE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("address", inputUnityAddress.getText().toString().trim())
                .params("source", getValue(slHouseFrom))
                .params("rent_state", getValue(slHouseState))
                .params("landlady_name", inputHostName.getText().toString().trim())
                .params("mode",getValue(slChuzufangshi))
                .params("floor_count",inputLou.getText().toString().trim())
                .params("unit",inputDanyuan.getText().toString().trim())
                .params("number",inputHao.getText().toString().trim())
                .params("landlady_phone", inputHostTel.getText().toString().trim());

        if (bean!=null){
            postRequest.params("rid", bean.getId()+"");
        }
        if (!TextUtils.isEmpty(houseId)) {
            postRequest.params("first_id", houseId);
        }
        postRequest.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String s) {
                if (NetParser.isOk(s)) {
                    StringDataResponse response = NetParser.parse(s, StringDataResponse.class);
                    houseId = response.getData();
                    if (!TextUtils.isEmpty(response.getMsg())){
                        T.showShort(getActivity(),response.getMsg());
                        return;
                    }
                    Intent intent = new Intent(getBaseContext(), AddHouseOtherInfoActivity.class);
                    intent.putExtra("id", houseId);
                    startActivityForResult(intent, 199);
                }else {
                    StringDataResponse dataResponse = NetParser.parse(s,StringDataResponse.class);
                    T.showShort(getActivity(),dataResponse.getMsg());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 199) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }else if (requestCode==133){
            if (resultCode == RESULT_OK) {
                bean = (XiaoQuBean) data.getSerializableExtra("data");
                slUnityName.setText(bean.getReside_name());
            }
        }
    }
}
