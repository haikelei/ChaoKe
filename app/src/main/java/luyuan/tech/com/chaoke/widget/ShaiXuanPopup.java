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
    @BindView(R.id.container_has_pic)
    GridLayout containerHasPic;
    @BindView(R.id.input_floor_min)
    InputLayout inputFloorMin;
    @BindView(R.id.input_floor_max)
    InputLayout inputFloorMax;

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
                checkBox.setTag(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            rentState = (finalI + 1) + "";
                            for (int j = 0; j < containerRentState.getChildCount(); j++) {
                                if (containerRentState.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerRentState.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
                            }
                        }else {
                            int i = (int) compoundButton.getTag();
                            if (String.valueOf(i+1).equals(rentState)){
                                rentState = "";
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
                checkBox.setTag(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            fitUp = (finalI + 1) + "";
                            for (int j = 0; j < containerFitUp.getChildCount(); j++) {
                                if (containerFitUp.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerFitUp.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
                            }
                        }else {
                            int i = (int) compoundButton.getTag();
                            if (String.valueOf(i+1).equals(fitUp)){
                                fitUp = "";
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
                checkBox.setTag(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            orientation = (finalI + 1) + "";
                            for (int j = 0; j < containerOrientation.getChildCount(); j++) {
                                if (containerOrientation.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerOrientation.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
                            }
                        }else {
                            int i = (int) compoundButton.getTag();
                            if (String.valueOf(i+1).equals(orientation)){
                                orientation = "";
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
                checkBox.setTag(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            source = (finalI + 1) + "";
                            for (int j = 0; j < containerSource.getChildCount(); j++) {
                                if (containerSource.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerSource.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
                            }
                        }else {
                            int i = (int) compoundButton.getTag();
                            if (String.valueOf(i+1).equals(source)){
                                source = "";
                            }
                        }
                    }
                });
            }
        }
        ////        快捷
        for (int i = 0; i < containerHasPic.getChildCount(); i++) {
            if (containerHasPic.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) containerHasPic.getChildAt(i);
                checkBox.setTag(i);
                final int finalI = i;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            hasPic = (finalI + 1) + "";
                            for (int j = 0; j < containerHasPic.getChildCount(); j++) {
                                if (containerHasPic.getChildAt(j) instanceof CheckBox){
                                    CheckBox checkBox = (CheckBox) containerHasPic.getChildAt(j);
                                    if (!checkBox.equals(compoundButton)){
                                        checkBox.setChecked(false);
                                    }
                                }
                            }
                        }else {
                            int i = (int) compoundButton.getTag();
                            if (String.valueOf(i+1).equals(hasPic)){
                                hasPic = "";
                            }
                        }
                    }
                });
            }
        }

        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnShaiXuanSelectListener!=null){
                    floormax = inputFloorMax.getText().toString().trim();
                    floormin = inputFloorMin.getText().toString().trim();
                    mOnShaiXuanSelectListener.onConfirm(source,orientation,fitUp,rentState,hasPic,floormin,floormax);
                }
                dismiss();
            }
        });
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_shaixuan);
    }


    String source = "";
    String orientation= "";
    String fitUp= "";
    String rentState= "";
    String hasPic= "";
    String floormin= "";
    String floormax= "";

    public interface OnShaiXuanSelectListener {
        void onConfirm(String source, String orientation, String fitUp, String rentState, String hasPic, String floormin, String floormax);
    }

    private OnShaiXuanSelectListener mOnShaiXuanSelectListener;

    public void setOnShaiXuanSelectListener(OnShaiXuanSelectListener listener) {
        mOnShaiXuanSelectListener = listener;
    }


}
