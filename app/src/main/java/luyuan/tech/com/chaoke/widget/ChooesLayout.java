package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.PeiZhiBean;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class ChooesLayout extends RelativeLayout {
    String title;
    String content;


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rb0)
    RadioButton rb0;
    @BindView(R.id.rb01)
    RadioButton rb01;
    @BindView(R.id.rg)
    RadioGroup rg;

    public ChooesLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public ChooesLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ChooesLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputLayout);
            content = a.getString(R.styleable.InputLayout_mHint);
            title = a.getString(R.styleable.InputLayout_mTitle);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_choose, this, true);
        ButterKnife.bind(this, view);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        rb01.setChecked(true);

    }

    public void setText(String s) {
        tvTitle.setText(s);
    }


    public String getTitle() {
        return title;
    }

    public String getCheckedPosition() {
        if (rb0.isChecked()) {
            return "1";
        } else if (rb01.isChecked()) {
            return "0";
        }
        return "0";
    }


    public void setData(final PeiZhiBean item) {
        rb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    item.checked = true;
                }
            }
        });
        rb01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    item.checked = false;
                }
            }
        });
    }
}
