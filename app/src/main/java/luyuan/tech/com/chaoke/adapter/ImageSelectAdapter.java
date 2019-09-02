package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ImageBean;
import luyuan.tech.com.chaoke.utils.Constant;

/**
 * @author: lujialei
 * @date: 2019/6/22
 * @describe:
 */


public class ImageSelectAdapter extends BaseMultiItemQuickAdapter<ImageBean,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ImageSelectAdapter(List<ImageBean> data) {
        super(data);
        addItemType(0, R.layout.item_image);
        addItemType(1, R.layout.item_add);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ImageBean item) {
        if (!item.isAddItem()){
            ImageView iv = helper.getView(R.id.iv);
            ImageView ivDelete = helper.getView(R.id.iv_delete);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(R.mipmap.default_image);
            String path;
            if (item.getPath().startsWith("http")){
                path = item.getPath();
            }else {
                path = Constant.IMAGE_PRE+item.getPath();
            }
            Glide.with(helper.itemView.getContext()).load(path).apply(requestOptions).into(iv);
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData().remove(helper.getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
