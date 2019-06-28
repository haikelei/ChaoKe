package luyuan.tech.com.chaoke.utils;

import android.media.session.MediaSession;
import android.widget.SimpleCursorTreeAdapter;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.util.List;

import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.bean.TokenBean;
import luyuan.tech.com.chaoke.net.HttpManager;

/**
 * @author: lujialei
 * @date: 2019/6/28
 * @describe:
 */


public class ImageUploadUtils {
//    AK：W1e4o0JMphrAtD4pKgYrPaf-mT4y3lh6Z3jHRoyF
//    SK：gB2e9iMChhw3Dg3Dnfccg5cOImHbI9E1zpJNe2Bo
//    空间名：renting_upload
//    绑定域名：http://rentpic.sayyin.com


    private static ImageUploadUtils uploadUtils = new ImageUploadUtils();
    private static UploadManager uploadManager;

    public static ImageUploadUtils getInstance(){
        if (uploadManager==null){
            Configuration config = new Configuration.Builder()
                    .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                    .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                    .connectTimeout(10)           // 链接超时。默认10秒
                    .useHttps(true)               // 是否使用https上传域名
                    .responseTimeout(60)          // 服务器响应超时。默认60秒
                    .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                    .build();
            uploadManager = new UploadManager(config);
        }
        return uploadUtils;
    }




    public void uploadImage(final File file, final UpCompletionHandler upCompletionHandler){
        HttpManager.post(HttpManager.IMAGE_TOKEN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .execute(new SimpleCallBack<TokenBean>() {

                    @Override
                    public void onError(ApiException e) {
//                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(TokenBean data) {
                        uploadManager.put(file,file.getName(),data.getToken(),upCompletionHandler,null);
                    }
                });

    }

}
