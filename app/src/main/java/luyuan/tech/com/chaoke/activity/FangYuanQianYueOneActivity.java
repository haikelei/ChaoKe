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
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.ItemBean;
import luyuan.tech.com.chaoke.bean.QianYueBeanOne;
import luyuan.tech.com.chaoke.bean.TotalIdBean;
import luyuan.tech.com.chaoke.bean.XiaoQuBean;
import luyuan.tech.com.chaoke.net.HttpManager;
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


public class FangYuanQianYueOneActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.input_fangyuanbianhao)
    InputLayout inputFangyuanbianhao;
    @BindView(R.id.sl_fangwulaiyuan)
    SelectLayout slFangwulaiyuan;
    @BindView(R.id.sl_qianyueleixing)
    SelectLayout slQianyueleixing;
    @BindView(R.id.sl_qianyueriqi)
    SelectLayout slQianyueriqi;
    @BindView(R.id.sl_jiaofangriqi)
    SelectLayout slJiaofangriqi;
    @BindView(R.id.sl_jiafangchizheng)
    SelectLayout slJiafangchizheng;
    @BindView(R.id.sl_fangwuyongtu)
    SelectLayout slFangwuyongtu;
    @BindView(R.id.input_lou)
    InputLayout inputLou;
    @BindView(R.id.input_danyuan)
    InputLayout inputDanyuan;
    @BindView(R.id.input_hao)
    InputLayout inputHao;
    @BindView(R.id.sl_unity_name)
    SelectLayout slUnityName;
    private String id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuan_qianyue_one);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
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

        String[] arr = {"中介合作", "转介绍", "老客户", "网络端口", "地推", "房东上门", "名单获取", "销冠"};
        setSelectLListener(slFangwulaiyuan, arr, "房源来源");

        String[] arr1 = {"房屋所有权证书", "原购房发票", "房屋买卖合同", "其他"};
        setSelectLListener(slJiafangchizheng, arr1, "甲方持证");

        setDatePickerListener(slQianyueriqi);
        setDatePickerListener(slJiaofangriqi);

        String[] arr2 = {"居住", "商用"};
        setSelectLListener(slFangwuyongtu, arr2, "房屋用途");

        String[] arr3 = {"首签", "续签"};
        setSelectLListener(slQianyueleixing, arr3, "签约类型");

        slUnityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),XuanZeXiaoQuActivity.class),134);
            }
        });
        if (getIntent()!=null){
            downloadTotalId = getIntent().getStringExtra("total_id");
            if (!TextUtils.isEmpty(downloadTotalId)){
                loadOldData(downloadTotalId);
            }
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


    private void loadOldData(String totalId) {
        HttpManager.post(HttpManager.QIANYUESHUJU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id",totalId)
                .params("step", "1")
                .execute(new SimpleCallBack<QianYueBeanOne>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(QianYueBeanOne data) {
                        id = data.getId()+"";
                        inputLou.setText(data.getFloor_count());
                        inputDanyuan.setText(data.getUnit());
                        inputHao.setText(data.getNumber());
                        inputFangyuanbianhao.setText(data.getRent_num());
                        slQianyueleixing.setSelect(data.getSigning_type());
                        slQianyueriqi.setText(data.getSigning_time());
                        slJiaofangriqi.setText(data.getOver_time());
                        slJiafangchizheng.setSelect(data.getCertificate());
                        slFangwuyongtu.setSelect(data.getUsed_by());
                        slFangwulaiyuan.setSelect(data.getFrom_by());
                        slUnityName.setText(data.getReside_name());
                        bean = new XiaoQuBean();
                        bean.setReside_name(data.getReside_name());
                        bean.setId(Integer.valueOf(data.getReside_id()));
                    }
                });

    }

    private String downloadTotalId;
    private String uploadTotalId;
    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        PostRequest postRequest = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .params("step", "1")
                .params("floor_count",inputLou.getText().toString().trim())
                .params("unit",inputDanyuan.getText().toString().trim())
                .params("number",inputHao.getText().toString().trim())
                .params("rent_num", getValue(inputFangyuanbianhao))
                .params("from_by", getValue(slFangwulaiyuan))
                .params("signing_type", getValue(slQianyueleixing))
                .params("signing_time", slQianyueriqi.getText().toString())
                .params("over_time", slJiaofangriqi.getText().toString())
                .params("certificate", getValue(slJiafangchizheng))
                .params("used_by", getValue(slFangwuyongtu));
        if (bean!=null){
            postRequest.params("reside_id", bean.getId()+"");
        }
        if (!TextUtils.isEmpty(downloadTotalId)) {
            postRequest.params("total_id", downloadTotalId);
        }
        postRequest.execute(new SimpleCallBack<TotalIdBean>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(TotalIdBean data) {
                uploadTotalId = data.getTotal_id();
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueTwoActivity.class);
                intent.putExtra("down_id",downloadTotalId);
                intent.putExtra("up_id",uploadTotalId);
                startActivity(intent);
            }
        });
    }

    private XiaoQuBean bean;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==134){
            if (resultCode == RESULT_OK) {
                bean = (XiaoQuBean) data.getSerializableExtra("data");
                slUnityName.setText(bean.getReside_name());
            }
        }
    }

}
