package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.WeiTuoHeTongBean;
import luyuan.tech.com.chaoke.bean.WeiTuoHeTongDetailBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ZuJinCeLueAdapter extends BaseQuickAdapter<WeiTuoHeTongDetailBean.StrategyBean,BaseViewHolder> {
    public ZuJinCeLueAdapter(@Nullable List<WeiTuoHeTongDetailBean.StrategyBean> data) {
        super(R.layout.item_zujin_celue,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeiTuoHeTongDetailBean.StrategyBean item) {
        TextView tvMianzu = helper.getView(R.id.tv_mianzuzhouqi);
        TextView tvWeituo = helper.getView(R.id.tv_weituozhouqi);
        TextView tvFangzu = helper.getView(R.id.tv_meiyuefangzu);
        TextView tvTitle = helper.getView(R.id.tv_title);

        tvMianzu.setText(item.getFree_cycle_begin()+"-"+item.getFree_cycle_end());
        tvWeituo.setText(item.getEntrust_cycle_begin()+"-"+item.getEntrust_cycle_end());
        tvFangzu.setText(item.getRent_price());
        tvTitle.setText("第"+(helper.getAdapterPosition()+1)+"年租金策略");
    }
}
