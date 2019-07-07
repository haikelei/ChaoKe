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
public class ZuKeShaiXuanPopup extends BasePopupWindow {
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.container_status)
    GridLayout containerStatus;
    @BindView(R.id.container_sex)
    GridLayout containerSex;
    @BindView(R.id.container_source)
    GridLayout containerSource;
    @BindView(R.id.container_grade)
    GridLayout containerGrade;

    public ZuKeShaiXuanPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {
        ButterKnife.bind(this, getContentView());
//        状态
        for (int i = 0; i < containerStatus.getChildCount(); i++) {
            if (containerStatus.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerStatus.getChildAt(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (mOnShaiXuanSelectListener != null) {
                                mOnShaiXuanSelectListener.onStatus((finalI + 1) + "");
                            }
                        }
                    }
                });
            }
        }

//        性别
        for (int i = 0; i < containerSex.getChildCount(); i++) {
            if (containerSex.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerSex.getChildAt(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (mOnShaiXuanSelectListener != null) {
                                mOnShaiXuanSelectListener.onSex((finalI + 1) + "");
                            }
                        }
                    }
                });
            }
        }
        //        等级
        for (int i = 0; i < containerGrade.getChildCount(); i++) {
            if (containerGrade.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerGrade.getChildAt(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (mOnShaiXuanSelectListener != null) {
                                mOnShaiXuanSelectListener.onGrade((finalI + 1) + "");
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
                if (mOnShaiXuanSelectListener != null) {
                    mOnShaiXuanSelectListener.onSource("");
                    mOnShaiXuanSelectListener.onGrade("");
                    mOnShaiXuanSelectListener.onSex("");
                    mOnShaiXuanSelectListener.onStatus("");
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
        return createPopupById(R.layout.layout_popup_shaixuan_zuke);
    }


    public interface OnShaiXuanSelectListener {
        void onSource(String s);

        void onStatus(String s);

        void onGrade(String s);

        void onSex(String s);
    }

    private OnShaiXuanSelectListener mOnShaiXuanSelectListener;

    public void setOnShaiXuanSelectListener(OnShaiXuanSelectListener listener) {
        mOnShaiXuanSelectListener = listener;
    }


}
