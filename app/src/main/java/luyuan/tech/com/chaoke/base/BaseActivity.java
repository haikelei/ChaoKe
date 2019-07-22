package luyuan.tech.com.chaoke.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;
import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.utils.AppManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.widget.ChooesLayout;
import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class BaseActivity extends AppCompatActivity {


    private List<View> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.get().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        AppManager.get().removeActivity(this);
        super.onDestroy();
    }

    public Activity getActivity() {
        return this;
    }

    protected void setSelectLListener(final SelectLayout selectLayout, final String[] arr) {
        setSelectLListener(selectLayout, arr, null);
    }


    protected void setSelectLListener(final SelectLayout selectLayout, final String[] arr, final String title) {
        selectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NormalListDialog dialog = new NormalListDialog(getActivity(), arr);
                dialog.titleBgColor(getResources().getColor(R.color.colorPrimary));
                dialog.titleTextColor(getResources().getColor(R.color.white));
                if (!TextUtils.isEmpty(title)) {
                    dialog.title(title);
                }
                dialog.show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = arr[position];
                        selectLayout.setText(s);
                        selectLayout.setTag(position);
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    protected void setDatePickerListener(final SelectLayout selectLayout) {
        selectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.create(true);
                datePickerDialogFragment.show(getSupportFragmentManager(), "tag");
                datePickerDialogFragment.setOnSelectListener(new DatePickerDialogFragment.OnDateSelectListener() {
                    @Override
                    public void onSelect(List<String> date) {
                        selectLayout.setText(date.get(0));
                        datePickerDialogFragment.dismiss();
                    }
                });
            }
        });
    }

    protected String getValue(InputLayout inputLayout){
        return inputLayout.getText().toString().trim();
    }

    protected String getValue(SelectLayout selectLayout){
        if (selectLayout.getTag()==null){
            return "";
        }
        return ((int)selectLayout.getTag()+1)+"";
    }

    protected String getValue(ChooesLayout chooesLayout){
        return chooesLayout.getCheckedPosition();
    }


    protected void showSuccess() {
        T.showShort(getBaseContext(), "提交成功");
    }


    protected boolean checkEmptyInfo(){
        List<View> list = getAllChildViews(getWindow().getDecorView());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof SelectLayout){
                SelectLayout selectLayout = (SelectLayout) list.get(i);
                if (selectLayout.getMust()&&TextUtils.isEmpty(selectLayout.getText())){
                    T.showShort(this,"请选择"+selectLayout.getTitle());
                    return false;
                }
            }else if (list.get(i) instanceof InputLayout){
                InputLayout inputLayout = (InputLayout) list.get(i);
                if (inputLayout.getMust()&&TextUtils.isEmpty(inputLayout.getText().toString().trim())){
                    T.showShort(this,"请输入"+inputLayout.getTitle());
                    return false;
                }
            }
        }
        return true;
    }

    //获取 activity中的所有view
    private List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                //再次 调用本身（递归）
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }



}
