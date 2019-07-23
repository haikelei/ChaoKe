package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.HousePeiZhiBean;

/**
 * @author: lujialei
 * @date: 2019/7/23
 * @describe:
 */


public class HousePeiZhiAdapter extends BaseQuickAdapter<HouseDetailBean.ConfigureBean,BaseViewHolder> {
    public HousePeiZhiAdapter(@Nullable List<HouseDetailBean.ConfigureBean> data) {
        super(R.layout.layout_house_peizhi,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseDetailBean.ConfigureBean item) {
        TextView textView = helper.getView(R.id.tv);
        ImageView iv = helper.getView(R.id.iv);
        textView.setText(item.getName());
        RequestOptions requestOptions = new RequestOptions().centerCrop();
        Glide.with(helper.itemView.getContext()).load(item.getIcon()).apply(requestOptions).into(iv);
    }
}
