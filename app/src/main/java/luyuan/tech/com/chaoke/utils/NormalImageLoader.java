package luyuan.tech.com.chaoke.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

/**
 * @author: lujialei
 * @date: 2019/6/19
 * @describe:
 */


public class NormalImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        Glide.with(context).load(Constant.IMAGE_PRE+path).apply(requestOptions).into(imageView);
    }
}
