package luyuan.tech.com.chaoke.base;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.List;

import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class BaseActivity extends AppCompatActivity {




    protected void setSelectLListener(final SelectLayout selectLayout, final String[] arr) {
        selectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NormalListDialog dialog = new NormalListDialog(getBaseContext(),arr);
                dialog.show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = arr[position];
                        selectLayout.setText(s);
                    }
                });
            }
        });
    }

    protected void setDatePickerListener(final SelectLayout selectLayout){
        selectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.create(true);
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
