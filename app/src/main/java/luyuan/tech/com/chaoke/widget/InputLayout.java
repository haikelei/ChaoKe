package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class InputLayout extends RelativeLayout {
    String title;
    String hint;
    boolean must;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et)
    AppCompatEditText et;

    public InputLayout(Context context) {
        super(context);
        initView(context,null);
    }

    public InputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public InputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs) {
        if (attrs!=null){
            TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.InputLayout);
            hint = a.getString(R.styleable.InputLayout_mHint);
            title = a.getString(R.styleable.InputLayout_mTitle);
            must = a.getBoolean(R.styleable.InputLayout_must,true);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_input, this, true);
        ButterKnife.bind(this,view);
        if (!TextUtils.isEmpty(title)) {
            tv.setText(title);
        }
        if (!TextUtils.isEmpty(hint)) {
            et.setHint(hint);
        }
    }

    public String getText(){
        return et.getText().toString().trim();
    }

    public String getTitle(){
        return title;
    }

    public boolean getMust(){
        return must;
    }
}
