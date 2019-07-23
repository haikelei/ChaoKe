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
    @BindView(R.id.container_room)
    GridLayout containerRoom;
    private String zhuangtai;
    private String sex;
    private String laiyuan;
    private String huxing;
    private String dengji;

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
                            zhuangtai = (finalI + 1) + "";
                            for (int j = 0; j < containerStatus.getChildCount(); j++) {
                                if (containerStatus.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerStatus.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
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
                            sex = (finalI + 1) + "";
                            for (int j = 0; j < containerSex.getChildCount(); j++) {
                                if (containerSex.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerSex.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
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
                            dengji = (finalI + 1) + "";
                            for (int j = 0; j < containerGrade.getChildCount(); j++) {
                                if (containerGrade.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerGrade.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
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
                            laiyuan = (finalI + 1) + "";
                            for (int j = 0; j < containerSource.getChildCount(); j++) {
                                if (containerSource.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerSource.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        //        户型
        for (int i = 0; i < containerRoom.getChildCount(); i++) {
            if (containerRoom.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerRoom.getChildAt(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            huxing = (finalI + 1) + "";
                            for (int j = 0; j < containerRoom.getChildCount(); j++) {
                                if (containerRoom.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerRoom.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                huxing = "";
                sex ="";
                dengji = "";
                laiyuan = "";
                zhuangtai = "";
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnShaiXuanSelectListener!=null){
                    mOnShaiXuanSelectListener.onConfirm(laiyuan,zhuangtai,dengji,sex,huxing);
                }
                dismiss();
            }
        });
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_shaixuan_zuke);
    }


    public interface OnShaiXuanSelectListener {
        void  onConfirm(String source,String status,String grade,String sex,String room);
    }

    private OnShaiXuanSelectListener mOnShaiXuanSelectListener;

    public void setOnShaiXuanSelectListener(OnShaiXuanSelectListener listener) {
        mOnShaiXuanSelectListener = listener;
    }


}
