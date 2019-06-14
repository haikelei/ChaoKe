package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.widget.DownMenuButton;
import luyuan.tech.com.chaoke.widget.QuYuPopup;
import luyuan.tech.com.chaoke.widget.ZuJinPopup;
import razerdp.basepopup.BasePopupWindow;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ZiYingFangYuanActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    QuYuPopup quYuPopup;
    ZuJinPopup zuJinPopup;
    @BindView(R.id.quyu_button)
    DownMenuButton quyuButton;
    @BindView(R.id.zujin_button)
    DownMenuButton zujinButton;
    @BindView(R.id.paixu_button)
    DownMenuButton paixuButton;
    @BindView(R.id.shaixuan_button)
    DownMenuButton shaixuanButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziying_fangyuan);
        ButterKnife.bind(this);
        quYuPopup = new QuYuPopup(this);
        quYuPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.NO_GRAVITY);
        zuJinPopup = new ZuJinPopup(this);
        zuJinPopup.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.NO_GRAVITY);
        quYuPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                quyuButton.setChecked(false);
            }
        });
        zuJinPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                zujinButton.setChecked(false);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.quyu_button, R.id.zujin_button, R.id.paixu_button, R.id.shaixuan_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.quyu_button:
                quyuButton.setChecked(true);
                zujinButton.setChecked(false);
                paixuButton.setChecked(false);
                shaixuanButton.setChecked(false);
                quYuPopup.showPopupWindow(R.id.rl_container);
                break;
            case R.id.zujin_button:
                quyuButton.setChecked(false);
                zujinButton.setChecked(true);
                paixuButton.setChecked(false);
                shaixuanButton.setChecked(false);
                zuJinPopup.showPopupWindow(R.id.rl_container);
                break;
            case R.id.paixu_button:
                quyuButton.setChecked(false);
                zujinButton.setChecked(false);
                paixuButton.setChecked(true);
                shaixuanButton.setChecked(false);
                break;
            case R.id.shaixuan_button:
                quyuButton.setChecked(false);
                zujinButton.setChecked(false);
                paixuButton.setChecked(false);
                shaixuanButton.setChecked(true);
                break;
        }
    }
}
