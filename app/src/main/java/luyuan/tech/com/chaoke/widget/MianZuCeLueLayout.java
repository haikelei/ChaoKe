package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;

/**
 * @author: lujialei
 * @date: 2019/6/22
 * @describe:
 */


public class MianZuCeLueLayout extends LinearLayout {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.input_mianzuyueshu)
    public InputLayout mInputLayout;
    private FragmentManager fragmentManager;

    public MianZuCeLueLayout(Context context, FragmentManager fragmentManager) {
        super(context);
        this.fragmentManager = fragmentManager;
        intiView(context);
    }

    public MianZuCeLueLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intiView(context);
    }

    public MianZuCeLueLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiView(context);
    }

    private void intiView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mianzujincelue, this, true);
        ButterKnife.bind(this, view);
    }



    public void setTitle(String s) {
        tvTitle.setText(s);
    }
}
