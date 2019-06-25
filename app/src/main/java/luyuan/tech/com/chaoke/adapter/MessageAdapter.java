package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.XiaoXiListBean;

/**
 * @author: lujialei
 * @date: 2019/6/23
 * @describe:
 */


public class MessageAdapter extends BaseQuickAdapter<XiaoXiListBean,BaseViewHolder>{
    public MessageAdapter(@Nullable List<XiaoXiListBean> data) {
        super(R.layout.item_message,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XiaoXiListBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvDesc = helper.getView(R.id.tv_desc);
        ImageView ivRead = helper.getView(R.id.iv_read);
        tvTitle.setText(item.getTitle());
        tvTime.setText(item.getCreatetime());
        tvDesc.setText(item.getDesc());
        ivRead.setVisibility(item.getIs_read()==1? View.VISIBLE:View.INVISIBLE);
    }
}
