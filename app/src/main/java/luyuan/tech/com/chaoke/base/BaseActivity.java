package luyuan.tech.com.chaoke.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.utils.AppManager;
import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class BaseActivity extends AppCompatActivity {


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


}
