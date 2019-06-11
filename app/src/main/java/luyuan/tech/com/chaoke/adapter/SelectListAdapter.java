package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ItemBean;

/**
 * @author: lujialei
 * @date: 2019/6/11
 * @describe:
 */


public class SelectListAdapter extends BaseQuickAdapter<ItemBean,BaseViewHolder>{


    public SelectListAdapter(@Nullable List<ItemBean> data) {
        super(R.layout.layout_item_select,data);
    }



    @Override
    protected void convert(BaseViewHolder helper, ItemBean data) {
        TextView tv = helper.getView(R.id.tv);
        RadioButton radioButton = helper.getView(R.id.rb);
        tv.setText(data.getTitle());
        radioButton.setChecked(data.isChecked());
    }
}
