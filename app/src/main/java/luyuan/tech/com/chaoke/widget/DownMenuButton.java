package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;

/**
 * @author: lujialei
 * @date: 2019/6/13
 * @describe:
 */


public class DownMenuButton extends LinearLayout {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.iv)
    ImageView iv;
    private boolean checked;
    private String text;

    public DownMenuButton(Context context) {
        super(context);
        initView(context);
    }

    public DownMenuButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputLayout);
            text = a.getString(R.styleable.InputLayout_mTitle);
        }
        initView(context);
    }

    public DownMenuButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputLayout);
            text = a.getString(R.styleable.InputLayout_mTitle);
        }
        initView(context);
    }

    private void initView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_down_menu_button, this, true);
        ButterKnife.bind(this,view);
        if (!TextUtils.isEmpty(text)){
            tv.setText(text);
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checked = !checked;
                updateUI();
                if (mOnCheckedChangeListener!=null){
                    mOnCheckedChangeListener.onCheckedChage(view,checked);
                }
            }
        });
    }

    private void updateUI() {
        if (checked) {
            tv.setTextColor(Color.parseColor("#60d1c8"));
            iv.setImageResource(R.mipmap.tri_up);
        }else {
            tv.setTextColor(Color.parseColor("#898989"));
            iv.setImageResource(R.mipmap.tri_down);
        }
    }

    public void setChecked(boolean b) {
        this.checked = b;
        updateUI();
    }

    public interface OnCheckedChangeListener{
        void onCheckedChage(View view,boolean checked);
    }
    private OnCheckedChangeListener mOnCheckedChangeListener;
    public void setOnCheckedChangeListener(OnCheckedChangeListener mOnCheckedChangeListener){
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

}
