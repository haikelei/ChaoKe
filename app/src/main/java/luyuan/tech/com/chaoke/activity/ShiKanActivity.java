package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhouyou.http.body.ProgressResponseCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.ImageSelectAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ImageBean;
import luyuan.tech.com.chaoke.bean.NameBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class ShiKanActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.map)
    MapView mMapView;
    private String id;
    private ImageSelectAdapter adapter;
    private ArrayList<ImageBean> list;
    public static final int CODE_SHIKAN = 160;
    Handler handler = new Handler();
    Runnable uploadRunnable = new Runnable() {
        @Override
        public void run() {
            HttpManager.post(HttpManager.SHIKAN_TUPIAN)
                    .params("token", UserInfoUtils.getInstance().getToken())
                    .params("id", id)
                    .params("pics", getListJson(list))
                    .execute(new SimpleCallBack<String>() {

                        @Override
                        public void onError(ApiException e) {
                            T.showShort(getBaseContext(), e.getMessage());
                        }

                        @Override
                        public void onSuccess(String data) {
                            T.showShort(getBaseContext(), "图片上传成功");
                        }
                    });
        }
    };
    private AMap aMap;

    private String getListJson(List<ImageBean> data) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getPath());
        }
        String s = new Gson().toJson(list);
        return s;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shikan);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initMap();
            }
        },2000);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        init
        list = new ArrayList<>();
        ImageBean bean = new ImageBean();
        bean.setAddItem(true);
        list.add(bean);
        adapter = new ImageSelectAdapter(list);
        rv.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = list.get(position);
                onClick(imageBean, CODE_SHIKAN);
            }
        });
    }

    private void initMap() {
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    private void onClick(ImageBean imageBean, int code) {
        if (imageBean.isAddItem()) {
            PictureSelector.create(getActivity())
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .maxSelectNum(9)// 最大图片选择数量
                    .minSelectNum(0)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(false)// 是否可预览图片
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .enableCrop(false)// 是否裁剪
                    .compress(true)// 是否压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                        .selectionMedia(selectList)// 是否传入已选图片
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .forResult(code);//结果回调onActivityResult code
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_SHIKAN:
                    // 图片选择结果回调
                    onResult(data, list, adapter);
                    break;
            }
        }
    }

    private void onResult(Intent data, final ArrayList<ImageBean> list, final ImageSelectAdapter adapter) {
        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
        for (LocalMedia media : selectList) {
            final String path = media.getCompressPath();
            File file = new File(path);
            HttpManager.post(HttpManager.IMAGE)
                    .params("file", file, new ProgressResponseCallBack() {
                        @Override
                        public void onResponseProgress(long bytesWritten, long contentLength, boolean done) {
//
                        }
                    }).execute(new SimpleCallBack<List<NameBean>>() {
                @Override
                public void onError(ApiException e) {

                }

                @Override
                public void onSuccess(List<NameBean> data) {
                    ImageBean bean = new ImageBean();
                    bean.setPath(data.get(0).getName());
                    list.add(0, bean);
                    adapter.notifyDataSetChanged();
                    handler.post(uploadRunnable);
                }
            });

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


}
