package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class SelectLayout extends RelativeLayout {
    String title;
    String content;
    boolean must;//是否必填 默认必填


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv)
    TextView tv;

    public SelectLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public SelectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public SelectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputLayout);
            content = a.getString(R.styleable.InputLayout_mHint);
            title = a.getString(R.styleable.InputLayout_mTitle);
            must = a.getBoolean(R.styleable.InputLayout_must,true);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_select, this, true);
        ButterKnife.bind(this, view);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(content)) {
            tvContent.setText(content);
        }
    }

    public void setText(String s){
        tvContent.setVisibility(GONE);
        tv.setVisibility(VISIBLE);
        tv.setText(s);
    }

    public String getText(){
        return tv.getText().toString().trim();
    }

    public String getTitle() {
        return title;
    }

    public boolean getMust(){
        return must;
    }

    private String value="";
    public void setvalue(String s) {
        value = s;
    }
    public String getValue() {
        return value;
    }

    private String[] arr;
    public void setArray(String[] arr) {
        this.arr = arr;
    }

    public void setSelect(String s){
        int index = Integer.valueOf(s)-1;
        setText(arr[index]);
        setTag(index);
    }

    public void setMlttySelect(List<String> first_cost) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < first_cost.size(); i++) {
            int index = Integer.valueOf(first_cost.get(i))-1;
            if (index>=0){
                stringBuilder.append(arr[index]+",");
                stringBuilder1.append((index+1)+",");
            }

        }
        setText(stringBuilder.toString());
        setvalue(stringBuilder1.toString());
    }
}
