package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ItemBean;
import luyuan.tech.com.chaoke.bean.PeiZhiBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.KeyBoardUtil;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.ChooesLayout;
import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.PeiZhiPopup;
import luyuan.tech.com.chaoke.widget.SelectDialogFragment;
import luyuan.tech.com.chaoke.widget.SelectLayout;
import razerdp.basepopup.BasePopupWindow;

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
    @BindView(R.id.sl_zhuangxiu)
    SelectLayout slZhuangxiu;
    @BindView(R.id.sl_fangwuyongtu)
    SelectLayout slFangwuyongtu;
    //    @BindView(R.id.sl_duanzu)
//    SelectLayout slDuanzu;
    @BindView(R.id.sl_shoucichuzu)
    SelectLayout slShoucichuzu;
    @BindView(R.id.cl_dianti)
    ChooesLayout clDianti;
    @BindView(R.id.cl_zhinengsuo)
    ChooesLayout clZhinengsuo;
    @BindView(R.id.cl_duwei)
    ChooesLayout clDuwei;
    @BindView(R.id.cl_yangtai)
    ChooesLayout clYangtai;
    @BindView(R.id.cl_piaochaung)
    ChooesLayout clPiaochaung;
    @BindView(R.id.sl_peizhi)
    SelectLayout slPeizhi;
    private String id;
    PeiZhiPopup peiZhiPopup;
    private String config = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        setContentView(R.layout.activity_add_house_other_info);
        ButterKnife.bind(this);
//        1为 2为 3为 4为 5为
        String[] arr = {"毛坯", "简装", "精装配置齐全", "精装配置不齐全", "豪华装修"};
        setSelectLListener(slZhuangxiu, arr);

        String[] arr1 = {"住宅", "出租"};
        setSelectLListener(slFangwuyongtu, arr1);

//        String[] arr2 = {"仅长租", "仅短租", "都可租"};
//        setSelectLListener(slDuanzu, arr2);

        String[] arr3 = {"首次出租", "非首次出租"};
        setSelectLListener(slShoucichuzu, arr3);

        String[] arrChaoxiang = {"朝南", "朝北", "朝东", "朝西"};
        setSelectLListener(slChaoxiang, arrChaoxiang);

        String[] arr4 = {"房东出租", "58同城", "A公司", "B公司", "C公司", "D公司"};
        setSelectLListener(slZulingongsi, arr4);

        String[] arr5 = {"房东出租", "预约", "直接", "借钥", "有钥", "智能锁"};
        setSelectLListener(slKanfangfangshi, arr5);

        setDatePickerListener(slDaoqiri);

        setDatePickerListener(slKanfangri);


        slPeizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyBoardUtil.hideKeyBoard(getActivity());
                peiZhiPopup.showPopupWindow();
            }
        });
        peiZhiPopup = new PeiZhiPopup(getActivity());
        peiZhiPopup.setPopupGravity(Gravity.CENTER);
        peiZhiPopup.setOnPeiZhiListener(new PeiZhiPopup.OnPeiZhiListener() {
            @Override
            public void onGetData(String s) {
                config = s;
            }
        });
        loadPeiZhi();
    }

    private void loadPeiZhi() {
        HttpManager.post(HttpManager.PEIZHI)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<List<PeiZhiBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<PeiZhiBean> datas) {
                        if (datas != null && datas.size() > 0) {
                            peiZhiPopup.setData(datas);
                        }
                    }
                });

    }

    @OnClick({R.id.iv_back, R.id.btn_submmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.btn_submmit:
                loadData();
                break;
        }
    }

    private void loadData() {
        if (TextUtils.isEmpty(inputShi.getText().toString()) || TextUtils.isEmpty(inputTing.getText().toString()) || TextUtils.isEmpty(inputWei.getText().toString())) {
            T.showShort(getActivity(), "请完整输入户型,若没有可填写0");
            return;
        }
        if (!checkEmptyInfo()) {
            return;
        }
        PostRequest postRequest = HttpManager.post(HttpManager.FABUTWO)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("ren_id", id)
                .params("long_price", getValue(inputMoney))
                .params("area", getValue(inputSize))
                .params("apartment", "")
                .params("is_first_rent", getValue(slShoucichuzu))
                .params("used_type", getValue(slFangwuyongtu))
//                .params("type", getValue(slDuanzu))
                .params("fit_up", getValue(slZhuangxiu))
                .params("is_lift", getValue(clDianti))
                .params("is_mind", getValue(clZhinengsuo))
                .params("is_toilet", getValue(clDuwei))
                .params("is_balcony", getValue(clYangtai))
                .params("is_bw", getValue(clPiaochaung))
                .params("orientation", getValue(slChaoxiang))
                .params("company_id", getValue(slZulingongsi))
                .params("see_time", slKanfangri.getText().toString())
                .params("see_type", getValue(slKanfangfangshi))
                .params("describe", etBeizhu.getText().toString().trim())
                .params("room", getValue(inputShi))
                .params("office", getValue(inputTing))
                .params("guard", getValue(inputWei));
        if (!TextUtils.isEmpty(slDaoqiri.getText().toString())) {
            postRequest.params("expiretime", slDaoqiri.getText().toString());
        }
        if (!TextUtils.isEmpty(config)) {
            postRequest.params("configure", config);
            return;
        }
        postRequest.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String s) {
                if (NetParser.isOk(s)) {
                    T.showShort(getActivity(), "新增成功");
                    setResult(RESULT_OK);
                    finish();
                }
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
