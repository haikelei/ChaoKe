package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.utils.T;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class ZuJinPopup extends BasePopupWindow {


    @BindView(R.id.tv_zujinbuxian)
    TextView tvZujinbuxian;
    @BindView(R.id.tv_zujin_1500)
    TextView tvZujin1500;
    @BindView(R.id.tv_zujin_2000)
    TextView tvZujin2000;
    @BindView(R.id.tv_zujin_2500)
    TextView tvZujin2500;
    @BindView(R.id.tv_zujin_4000)
    TextView tvZujin4000;
    @BindView(R.id.tv_zujin_4000_high)
    TextView tvZujin4000High;
    @BindView(R.id.et_from)
    EditText etFrom;
    @BindView(R.id.et_to)
    EditText etTo;
    @BindView(R.id.btn_zujin)
    Button btnZujin;

    public interface OnZuJinListener{
        void onZuJinSelected(String low,String high);
    }
    private OnZuJinListener mOnZuJinListener;
    public void setOnZuJinListener(OnZuJinListener listener){
        mOnZuJinListener = listener;
    }

    public ZuJinPopup(Context context) {
        super(context);
        setPopupFadeEnable(true);
        bindEvent();
    }

    private void bindEvent() {
        ButterKnife.bind(this,getContentView());
        tvZujinbuxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select("","");
            }
        });
        tvZujin1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select("0","1500");
            }
        });
        tvZujin2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select("1500","2000");
            }
        });
        tvZujin2500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select("2000","2500");
            }
        });
        tvZujin4000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select("2500","4000");
            }
        });
        tvZujin4000High.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select("4000","999999999999");
            }
        });
        btnZujin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String low = etFrom.getText().toString().trim();
                String high = etTo.getText().toString().trim();
                if (TextUtils.isEmpty(low)&&TextUtils.isEmpty(high)){
                    T.showShort(getContext(),"租金至少填写一个");
                }else if (TextUtils.isEmpty(low)&&!TextUtils.isEmpty(high)){
                    select("0",high);
                }else if (!TextUtils.isEmpty(low)&&TextUtils.isEmpty(high)){
                    select(low,"999999999");
                }else if (!TextUtils.isEmpty(low)&&!TextUtils.isEmpty(high)){
                    select(low,high);
                }
            }
        });
    }

    private void select(String low,String high){
        if (mOnZuJinListener!=null){
            mOnZuJinListener.onZuJinSelected(low,high);
        }
        dismiss();
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_zujin);
    }


}
