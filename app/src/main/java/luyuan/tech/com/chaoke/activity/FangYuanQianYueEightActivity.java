package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.zhouyou.http.body.ProgressResponseCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;

import org.json.JSONException;
import org.json.JSONObject;

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
import luyuan.tech.com.chaoke.bean.TotalIdBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.ImageUploadUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class FangYuanQianYueEightActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.sl_chanquanrenleixing)
    SelectLayout slChanquanrenleixing;
    @BindView(R.id.sl_zhengjianleixing)
    SelectLayout slZhengjianleixing;
    @BindView(R.id.input_xingming)
    InputLayout inputXingming;
    @BindView(R.id.input_zhengjianhaoma)
    InputLayout inputZhengjianhaoma;
    @BindView(R.id.sl_zhengjiankaishiri)
    SelectLayout slZhengjiankaishiri;
    @BindView(R.id.sl_zhengjianjiezhiri)
    SelectLayout slZhengjianjiezhiri;
    @BindView(R.id.rv_shenfenzhengmian)
    RecyclerView rvShenfenzhengmian;
    @BindView(R.id.rv_shenfenfanmian)
    RecyclerView rvShenfenfanmian;
    @BindView(R.id.input_chanquanzhengbianhao)
    InputLayout inputChanquanzhengbianhao;
    private String id;

    private ImageSelectAdapter adapterShenfenzhengmian;
    private ImageSelectAdapter adapterShenfenfanmian;
    private ArrayList<ImageBean> listShenfenzhengmian;
    private ArrayList<ImageBean> listShenfenfanmian;
    public static final int CODE_Shenfenzhengmian = 180;
    public static final int CODE_Shenfenfanmian = 181;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuanqianyue_seven);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();

            }
        });
        String[] arr = {"个人", "企业"};
        setSelectLListener(slChanquanrenleixing, arr, "产权人类型");

        String[] arr1 = {"身份证", "护照", "军人证"};
        setSelectLListener(slZhengjianleixing, arr1, "产权人类型");


        setDatePickerListener(slZhengjianjiezhiri);
        setDatePickerListener(slZhengjiankaishiri);
        initView();
        initListener();
    }

    private void initListener() {
        adapterShenfenzhengmian.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listShenfenzhengmian.get(position);
                onSingleClick(imageBean, CODE_Shenfenzhengmian);
            }
        });
        adapterShenfenfanmian.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listShenfenfanmian.get(position);
                onSingleClick(imageBean, CODE_Shenfenfanmian);
            }
        });
    }

    private void onSingleClick(ImageBean imageBean, int code) {
        if (imageBean.isAddItem()) {
            PictureSelector.create(getActivity())
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .maxSelectNum(1)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
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


    private void initView() {
        //身份正面
        listShenfenzhengmian = new ArrayList<>();
        ImageBean bean = new ImageBean();
        bean.setAddItem(true);
        listShenfenzhengmian.add(bean);
        adapterShenfenzhengmian = new ImageSelectAdapter(listShenfenzhengmian);
        rvShenfenzhengmian.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rvShenfenzhengmian.setAdapter(adapterShenfenzhengmian);
        //身份反面
        listShenfenfanmian = new ArrayList<>();
        ImageBean bean1 = new ImageBean();
        bean1.setAddItem(true);
        listShenfenfanmian.add(bean1);
        adapterShenfenfanmian = new ImageSelectAdapter(listShenfenfanmian);
        rvShenfenfanmian.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rvShenfenfanmian.setAdapter(adapterShenfenfanmian);

    }


    private String oldId;

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        PostRequest request = HttpManager.post(HttpManager.FANGYUANQIANYUE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("total_id", id)
                .params("step", "8")
                .params("people_type", getValue(slChanquanrenleixing))
                .params("card_type", getValue(slZhengjianleixing))
                .params("username", getValue(inputXingming))
                .params("card_num", getValue(inputZhengjianhaoma))
                .params("card_begin", getValue(slZhengjiankaishiri))
                .params("card_end", getValue(slZhengjianjiezhiri))
                .params("card_zpic", getSingleJson(listShenfenzhengmian))
                .params("card_fpic", getSingleJson(listShenfenfanmian))
                .params("property_num", getValue(inputChanquanzhengbianhao));
        if (!TextUtils.isEmpty(oldId)) {
            request.params("old_id", oldId);
        }

        request.execute(new SimpleCallBack<TotalIdBean>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(TotalIdBean data) {
                oldId = data.getOld_id();
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueNineActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }


    private String getSingleJson(List<ImageBean> data) {
        String path = "";
        for (int i = 0; i < data.size(); i++) {
            ImageBean imageBean = data.get(i);
            if (!imageBean.isAddItem()) {
                path = imageBean.getPath();
            }
        }
        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_Shenfenzhengmian:
                    // 图片选择结果回调
                    onSingleResult(data, listShenfenzhengmian, adapterShenfenzhengmian);
                    break;
                case CODE_Shenfenfanmian:
                    // 图片选择结果回调
                    onSingleResult(data, listShenfenfanmian, adapterShenfenfanmian);
                    break;

            }
        }
    }

    private void onSingleResult(Intent data, final ArrayList<ImageBean> list, final ImageSelectAdapter adapter) {
        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
        for (LocalMedia media : selectList) {
            final String path = media.getCompressPath();
            File file = new File(path);
            ImageUploadUtils.getInstance().uploadImage(file, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    //res包含hash、key等信息，具体字段取决于上传策略的设置
                    if (info.isOK()) {
                        try {
                            String name = response.getString("key");
                            ImageBean bean = new ImageBean();
                            bean.setPath(name);
                            if (list.size() == 2) {
                                list.remove(0);
                            }
                            list.add(0, bean);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

}
