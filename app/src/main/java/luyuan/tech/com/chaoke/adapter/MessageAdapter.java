package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;

/**
 * @author: lujialei
 * @date: 2019/6/23
 * @describe:
 */


public class MessageAdapter extends BaseQuickAdapter<Object,BaseViewHolder>{
    public MessageAdapter(@Nullable List<Object> data) {
        super(R.layout.item_message,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
