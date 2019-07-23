package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.MultySelectBean;
import luyuan.tech.com.chaoke.bean.ShaiXuanCityBean;

/**
 * @author: lujialei
 * @date: 2019/6/25
 * @describe:
 */


public class MultySelectAdapter extends BaseQuickAdapter<MultySelectBean,BaseViewHolder> {
    public MultySelectAdapter(@Nullable List<MultySelectBean> data) {
        super(R.layout.layout_item_multy_select,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultySelectBean item) {
        TextView textView = helper.getView(R.id.tv);
        ImageView iv = helper.getView(R.id.iv);
        textView.setText(item.title);
        Glide.with(helper.itemView.getContext()).load(item.checked?R.mipmap.selected:R.mipmap.not_select).into(iv);
    }
}
