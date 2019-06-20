package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.AppStorageUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.GenJinPopup;
import luyuan.tech.com.chaoke.widget.ZuKeBianJiPopup;
import razerdp.basepopup.BasePopupWindow;

/**
 * @author: lujialei
 * @date: 2019/6/18
 * @describe:
 */


public class ZuKeDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.ll_qianyue_bottom)
    LinearLayout llQianyueBottom;
    @BindView(R.id.ll_daikan_bottom)
    LinearLayout llDaikanBottom;
    @BindView(R.id.ll_genjin_bottom)
    LinearLayout llGenjinBottom;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    @BindView(R.id.tv_quyu)
    TextView tvQuyu;
    @BindView(R.id.tv_zujin)
    TextView tvZujin;
    @BindView(R.id.tv_jushi)
    TextView tvJushi;
    @BindView(R.id.tv_zhuangxiu)
    TextView tvZhuangxiu;
    @BindView(R.id.tv_xiwangruzhuri)
    TextView tvXiwangruzhuri;
    @BindView(R.id.tv_renshu)
    TextView tvRenshu;
    @BindView(R.id.container)
    LinearLayout container;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuke_detail);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.ZUKE_DETAIL)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<ZuKeDetailBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(ZuKeDetailBean data) {
                        AppStorageUtils.getInstance().setZuKeDetailBean(data);
                        fillData(data);
                    }
                });
    }

    private void fillData(ZuKeDetailBean data) {
        tvQuyu.setText("区域:"+data.getCity_name());
        tvZujin.setText("租金:"+data.getRent_min());
        tvJushi.setText("居室:"+data.getRoom()+"室"+data.getOffice()+"厅"+data.getToilet()+"卫");
//        tvZhuangxiu.setText("装修:"+);
        tvXiwangruzhuri.setText("希望入住日:"+data.getCheckin_time());
        tvRenshu.setText("人数:"+data.getPeople_num());
    }

    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.ll_qianyue_bottom, R.id.ll_daikan_bottom, R.id.ll_genjin_bottom, R.id.ll_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_edit:
                ZuKeBianJiPopup quYuPopup = new ZuKeBianJiPopup(getBaseContext());
                quYuPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.BOTTOM);
                quYuPopup.showPopupWindow(view);
                quYuPopup.setOnPopupClickListener(new ZuKeBianJiPopup.OnPopupClickListener() {
                    @Override
                    public void onZukexinxiClick(View view) {

                    }

                    @Override
                    public void onQiuzuxuqiu(View view) {

                    }

                    @Override
                    public void onXiugaizhuangtai(View view) {

                    }
                });
                break;
            case R.id.ll_qianyue_bottom:
                startActivity(new Intent(getBaseContext(), QianYueFangYuanActivity.class));
                break;
            case R.id.ll_daikan_bottom:
                startActivity(new Intent(getBaseContext(), DaiKanFangYuanActivity.class));
                break;
            case R.id.ll_genjin_bottom:
                GenJinPopup genJinPopup = new GenJinPopup(ZuKeDetailActivity.this);
                genJinPopup.setPopupGravity(Gravity.CENTER);
                genJinPopup.showPopupWindow();
                break;
            case R.id.ll_call:
                callPhone("13800000000");
                break;
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
