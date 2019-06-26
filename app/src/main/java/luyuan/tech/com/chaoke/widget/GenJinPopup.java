package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class GenJinPopup extends BasePopupWindow {


    @BindView(R.id.cb_liaojiexuqiu)
    CheckBox cb0;
    @BindView(R.id.cb_tuijianfangyuan)
    CheckBox cb1;
    @BindView(R.id.cb_shihouqiatan)
    CheckBox cb2;
    @BindView(R.id.cb_yuyuedaikan)
    CheckBox cb3;
    @BindView(R.id.cb_qianyuehetong)
    CheckBox cb4;
    @BindView(R.id.cb_qitagenjin)
    CheckBox cb5;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    private String id;

    public GenJinPopup(Context context,String id) {
        super(context);
        this.id = id;
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {
        ButterKnife.bind(this,getContentView());
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        cb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);
            }
        });
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb0.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb1.setChecked(false);
                cb0.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb0.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb0.setChecked(false);
                cb5.setChecked(false);
            }
        });
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb0.setChecked(false);
            }
        });
    }

    private void loadData() {
        int position = 0;
        if (cb0.isChecked()){
            position=1;
        }else if (cb1.isChecked()){
            position=2;
        }else if (cb2.isChecked()){
            position=3;
        }else if (cb3.isChecked()){
            position=4;
        }else if (cb4.isChecked()){
            position=5;
        }else if (cb5.isChecked()){
            position=6;
        }
        if (position==0){
            T.showShort(getContext(),"请选择至少一项");
            return;
        }
        HttpManager.post(HttpManager.GEN_JIN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("tenant_id",id)
                .params("follow",String.valueOf(position+1))
                .params("desc",et.getText().toString().trim())
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(String data) {
                        T.showShort(getContext(),"跟进成功");
                       dismiss();
                    }
                });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_genjin);
    }


}
