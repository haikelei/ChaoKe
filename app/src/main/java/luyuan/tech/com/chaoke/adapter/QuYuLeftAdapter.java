package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class QuYuLeftAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {
    public QuYuLeftAdapter(@Nullable List<Integer> data) {
        super(R.layout.layout_item_quyu_left,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        TextView textView = helper.getView(R.id.tv);
        if (item==0){
            textView.setText("不限");
        }else if (item==1){
            textView.setText("城区");
        }
    }
}
