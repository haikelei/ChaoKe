package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class ZuKePaiXuPopup extends BasePopupWindow {


    @BindView(R.id.tv_paixu_buxian)
    TextView tvPaixuBuxian;
    @BindView(R.id.tv_paixu_zujinhigh)
    TextView tvPaixuZujinhigh;
    @BindView(R.id.tv_paixu_zujinlow)
    TextView tvPaixuZujinlow;
    @BindView(R.id.tv_paixu_mianjihigh)
    TextView tvPaixuMianjihigh;
    @BindView(R.id.tv_paixu_mianjilow)
    TextView tvPaixuMianjilow;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;

    public interface OnPaiXuListener {
        void onZuJinSelected(String index);
    }

    private OnPaiXuListener mOnZuJinListener;

    public void setOnPaiXuListener(OnPaiXuListener listener) {
        mOnZuJinListener = listener;
    }

    public ZuKePaiXuPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {
        ButterKnife.bind(this, getContentView());
        tvPaixuBuxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("");
            }
        });
        tvPaixuZujinlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("1");
            }
        });
        tvPaixuZujinhigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("2");
            }
        });
        tvPaixuMianjilow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("3");
            }
        });
        tvPaixuMianjihigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("4");
            }
        });
        tvShijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("5");
            }
        });
    }

    public void setSelect(String index) {
        if (mOnZuJinListener != null) {
            mOnZuJinListener.onZuJinSelected(index);
        }
        dismiss();
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_paixu_zuke);
    }


}
