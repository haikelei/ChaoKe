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


public class ZuJinCeLueLayout extends LinearLayout {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.input_meiyuezujin)
    public InputLayout inputMeiyuezujin;
    @BindView(R.id.sl_weituokaishi)
    public SelectLayout slWeituokaishi;
    @BindView(R.id.sl_weituojieshu)
    public SelectLayout slWeituojieshu;
    @BindView(R.id.sl_mianzukaishiri)
    public SelectLayout slMianzukaishiri;
    @BindView(R.id.sl_mianzujieshuri)
    public SelectLayout slMianzujieshuri;
    private FragmentManager fragmentManager;

    public ZuJinCeLueLayout(Context context, FragmentManager fragmentManager) {
        super(context);
        this.fragmentManager = fragmentManager;
        intiView(context);
    }

    public ZuJinCeLueLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intiView(context);
    }

    public ZuJinCeLueLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiView(context);
    }

    private void intiView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_zujincelue, this, true);
        ButterKnife.bind(this, view);
//        setDatePickerListener(slMianzujieshuri);
//        setDatePickerListener(slMianzukaishiri);
        setDatePickerListener(slWeituojieshu);
        setDatePickerListener(slWeituokaishi);
    }


    protected void setDatePickerListener(final SelectLayout selectLayout) {
        selectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.create(true);
                datePickerDialogFragment.show(fragmentManager, "tag");
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

    public void setTitle(String s) {
        tvTitle.setText(s);
    }
}
