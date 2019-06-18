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
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.bean.HouseTaskBean;

/**
 * @author: lujialei
 * @date: 2019/6/17
 * @describe:
 */


public class HouseTaskAdapter extends BaseQuickAdapter<HouseTaskBean,BaseViewHolder> {
    public HouseTaskAdapter(@Nullable List<HouseTaskBean> data) {
        super(R.layout.layout_client_task_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseTaskBean item) {
        TextView tvName = helper.getView(R.id.tv_title);
        TextView tvOther = helper.getView(R.id.tv_other);
        tvName.setText(item.getRoom_name());
        tvOther.setText(item.getCreatetime());
    }
}
