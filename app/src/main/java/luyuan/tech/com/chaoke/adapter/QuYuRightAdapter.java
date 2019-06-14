package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import luyuan.tech.com.chaoke.R;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class QuYuRightAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {
    public QuYuRightAdapter(@Nullable List<Integer> data) {
        super(R.layout.layout_item_quyu_right,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        TextView textView = helper.getView(R.id.tv);

    }

}
