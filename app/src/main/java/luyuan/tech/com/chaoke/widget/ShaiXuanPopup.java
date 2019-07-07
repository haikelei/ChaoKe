package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class ShaiXuanPopup extends BasePopupWindow {

    @BindView(R.id.container_rent_state)
    GridLayout containerRentState;
    @BindView(R.id.container_fit_up)
    GridLayout containerFitUp;
    @BindView(R.id.container_orientation)
    GridLayout containerOrientation;
    @BindView(R.id.container_source)
    GridLayout containerSource;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    public ShaiXuanPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {
        ButterKnife.bind(this, getContentView());
//        状态
        for (int i = 0; i < containerRentState.getChildCount(); i++) {
            if (containerRentState.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerRentState.getChildAt(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (mOnShaiXuanSelectListener != null) {
                                mOnShaiXuanSelectListener.onRentState((finalI + 1) + "");
                            }
                        }
                    }
                });
            }
        }

//        装修
        for (int i = 0; i < containerFitUp.getChildCount(); i++) {
            if (containerFitUp.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerFitUp.getChildAt(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (mOnShaiXuanSelectListener != null) {
                                mOnShaiXuanSelectListener.onFitUp((finalI + 1) + "");
                            }
                        }
                    }
                });
            }
        }
        //        朝向
        for (int i = 0; i < containerOrientation.getChildCount(); i++) {
            if (containerOrientation.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerOrientation.getChildAt(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (mOnShaiXuanSelectListener != null) {
                                mOnShaiXuanSelectListener.onOrientation((finalI + 1) + "");
                            }
                        }
                    }
                });
            }
        }
        //        来源
        for (int i = 0; i < containerSource.getChildCount(); i++) {
            if (containerSource.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerSource.getChildAt(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (mOnShaiXuanSelectListener != null) {
                                mOnShaiXuanSelectListener.onSource((finalI + 1) + "");
                            }
                        }
                    }
                });
            }
        }

        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnShaiXuanSelectListener!=null){
                    mOnShaiXuanSelectListener.onSource("");
                    mOnShaiXuanSelectListener.onFitUp("");
                    mOnShaiXuanSelectListener.onRentState("");
                    mOnShaiXuanSelectListener.onOrientation("");
                }
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_shaixuan);
    }


    public interface OnShaiXuanSelectListener {
        void onSource(String s);

        void onOrientation(String s);

        void onFitUp(String s);

        void onRentState(String s);
    }

    private OnShaiXuanSelectListener mOnShaiXuanSelectListener;

    public void setOnShaiXuanSelectListener(OnShaiXuanSelectListener listener) {
        mOnShaiXuanSelectListener = listener;
    }


}
