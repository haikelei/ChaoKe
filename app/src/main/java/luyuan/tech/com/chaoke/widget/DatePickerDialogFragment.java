package luyuan.tech.com.chaoke.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.aigestudio.datepicker.bizs.themes.DPTManager;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import luyuan.tech.com.chaoke.R;

/**
 * @author: lujialei
 * @date: 2019/6/11
 * @describe:
 */


public class DatePickerDialogFragment  extends DialogFragment {

    DatePicker datePicker;

    public static DatePickerDialogFragment create(boolean singleMode) {
        DatePickerDialogFragment dialogFragment = new DatePickerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("single",singleMode);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //无标题
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.layout_datepicker_dialog, container);
        boolean single = false;
        if (getArguments()!=null){
            single = getArguments().getBoolean("single",false);
        }
        DPTManager.getInstance().initCalendar(new MyCalendarTheme());
        datePicker = view.findViewById(R.id.date_picker);
        datePicker.setDate(getYear(), getMonth());
        if (single){
            datePicker.setMode(DPMode.SINGLE);
            datePicker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
                @Override
                public void onDatePicked(String date) {
                    if (mOnSelectListener!=null){
                        ArrayList<String> list = new ArrayList<>();
                        list.add(date);
                        mOnSelectListener.onSelect(list);
                    }
                    dismiss();
                }
            });
        }else {
            datePicker.setMode(DPMode.MULTIPLE);
            datePicker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
                @Override
                public void onDateSelected(List<String> date) {
                    if (mOnSelectListener!=null){
                        mOnSelectListener.onSelect(date);
                    }
                    dismiss();
                }
            });
        }

        return view;
    }

    /**
     * 获取年
     * @return
     */
    public static int getYear(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.YEAR);
    }

    /**
     * 获取月
     * @return
     */
    public static int getMonth(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.MONTH)+1;
    }

    @Override
    public void onStart() {
        super.onStart();
        int dialogHeight = (int) (getContext().getResources().getDisplayMetrics().heightPixels * 0.8);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, dialogHeight);
        getDialog().setCanceledOnTouchOutside(true); //点击边际可消失
    }

    public interface OnDateSelectListener{
        void onSelect(List<String> date);
    }
    private OnDateSelectListener mOnSelectListener;
    public void setOnSelectListener(OnDateSelectListener mOnSelectListener) {
        this.mOnSelectListener = mOnSelectListener;
    }

}
