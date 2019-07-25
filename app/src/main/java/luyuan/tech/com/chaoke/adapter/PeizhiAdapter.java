package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.PeiZhiBean;
import luyuan.tech.com.chaoke.bean.ShaiXuanCityBean;
import luyuan.tech.com.chaoke.widget.ChooesLayout;

/**
 * @author: lujialei
 * @date: 2019/6/25
 * @describe:
 */


public class PeizhiAdapter extends BaseQuickAdapter<PeiZhiBean,BaseViewHolder> {
    public PeizhiAdapter(@Nullable List<PeiZhiBean> data) {
        super(R.layout.item_peizhi,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeiZhiBean item) {
        ChooesLayout chooesLayout = helper.getView(R.id.cl);
        chooesLayout.setText(item.getName());
        chooesLayout.setData(item);
    }
}
