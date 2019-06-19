package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhouyou.http.body.ProgressResponseCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HeTongIdBean;
import luyuan.tech.com.chaoke.bean.NameBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.DatePickerDialogFragment;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;
import okhttp3.MediaType;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class HeToneXinXiThreeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.iv_zhengmian)
    ImageView ivZhengmian;
    @BindView(R.id.iv_fanmian)
    ImageView ivFanmian;
    @BindView(R.id.input_name)
    InputLayout inputName;
    @BindView(R.id.sl_xingbie)
    SelectLayout slXingbie;
    @BindView(R.id.input_nianling)
    InputLayout inputNianling;
    @BindView(R.id.input_shenfenzhenghaoma)
    InputLayout inputShenfenzhenghaoma;
    @BindView(R.id.sl_zhengjianyouxiaokaishi)
    SelectLayout slZhengjianyouxiaokaishi;
    @BindView(R.id.sl_zhengjianyouxiaojiezhi)
    SelectLayout slZhengjianyouxiaojiezhi;
    @BindView(R.id.cb_chengzuren)
    CheckBox cbChengzuren;
    @BindView(R.id.cb_dailiren)
    CheckBox cbDailiren;
    @BindView(R.id.rl_zhengmian)
    RelativeLayout rlZhengmian;
    @BindView(R.id.rl_fanmian)
    RelativeLayout rlFanmian;
    private HeTongIdBean bean;

    public static final int CODE_ZHENGMIAN = 101;
    public static final int CODE_FANMIAN = 102;
    private String zhengmianImageName;
    private String fanmianImageName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hetong_xinxi_three);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            bean = (HeTongIdBean) getIntent().getSerializableExtra("data");
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        slXingbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] arr = {"男","女"};
                final NormalListDialog dialog = new NormalListDialog(getBaseContext(),arr);
                dialog.show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        slXingbie.setText(arr[position]);
                        dialog.dismiss();
                    }
                });
            }
        });

        setDatePickerListener(slZhengjianyouxiaojiezhi);
        setDatePickerListener(slZhengjianyouxiaokaishi);

        rlZhengmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelector.create(HeToneXinXiThreeActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                        .selectionMedia(selectList)// 是否传入已选图片
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(CODE_ZHENGMIAN);//结果回调onActivityResult code

            }
        });
        rlFanmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelector.create(HeToneXinXiThreeActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                        .selectionMedia(selectList)// 是否传入已选图片
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(CODE_FANMIAN);//结果回调onActivityResult code

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_ZHENGMIAN:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }
                    final String path = selectList.get(0).getCompressPath();
                    File file = new File(path);
                    HttpManager.post(HttpManager.IMAGE)
                            .params("file", file,path, MediaType.parse("multipart/form-data"),new ProgressResponseCallBack() {
                                @Override
                                public void onResponseProgress(long bytesWritten, long contentLength, boolean done) {
                                    if (done) {
                                        Glide.with(getBaseContext()).load(path).into(ivZhengmian);
                                    }
                                }
                            }).execute(new SimpleCallBack<List<NameBean>>() {
                        @Override
                        public void onError(ApiException e) {

                        }

                        @Override
                        public void onSuccess(List<NameBean> list) {
                            zhengmianImageName = list.get(0).getName();
                        }
                    });
                    break;
                case CODE_FANMIAN:
                    // 图片选择结果回调
                    final String path1 = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
                    File file1 = new File(path1);
                    HttpManager.post(HttpManager.IMAGE)
                            .params("file", file1,path1, MediaType.parse("multipart/form-data"),new ProgressResponseCallBack() {
                                @Override
                                public void onResponseProgress(long bytesWritten, long contentLength, boolean done) {
                                    if (done) {
                                        Glide.with(getBaseContext()).load(path1).into(ivFanmian);
                                    }
                                }
                            }).execute(new SimpleCallBack<List<NameBean>>() {
                        @Override
                        public void onError(ApiException e) {

                        }

                        @Override
                        public void onSuccess(List<NameBean> list) {
                            fanmianImageName = list.get(0).getName();
                        }
                    });
                    break;
            }
        }
    }

    private void loadData() {
        HttpManager.post(HttpManager.HETONG_THREE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("con_id",bean.getCon_id())
                .params("cons_id",bean.getCons_id())
                .params("idcard",inputShenfenzhenghaoma.getText().trim())
                .params("front_idcard",zhengmianImageName)
                .params("side_idcard",fanmianImageName)
                .params("username",inputName.getText().trim())
                .params("sex",slXingbie.getText().trim().equals("男")?"1":"2")
                .params("age",inputNianling.getText().trim())
                .params("effective_time",slZhengjianyouxiaokaishi.getText().trim())
                .params("Invalid_time", slZhengjianyouxiaojiezhi.getText().trim())
                .execute(new SimpleCallBack<HeTongIdBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HeTongIdBean data) {
                        Intent intent = new Intent(getBaseContext(), HeToneXinXiFourActivity.class);
                        intent.putExtra("data",bean);
                        startActivity(intent);
                    }
                });
    }

}
