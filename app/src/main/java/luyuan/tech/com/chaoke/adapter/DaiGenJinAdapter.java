package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ShouFangShenPiBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class DaiGenJinAdapter extends BaseQuickAdapter<ShouFangShenPiBean.DataBean,BaseViewHolder> {
    public DaiGenJinAdapter(@Nullable List<ShouFangShenPiBean.DataBean> data) {
        super(R.layout.layout_daigenjin_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouFangShenPiBean.DataBean item) {

    }
}
