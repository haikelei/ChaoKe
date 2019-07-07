package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.QuYuCenterAdapter;
import luyuan.tech.com.chaoke.adapter.QuYuLeftAdapter;
import luyuan.tech.com.chaoke.adapter.QuYuRightAdapter;
import luyuan.tech.com.chaoke.bean.CityBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 * gravityPopup
 */
public class QuYuPopup extends BasePopupWindow {

    @BindView(R.id.recycler_center)
    RecyclerView recyclerCenter;
    private RecyclerView recyclerLeft;
    private RecyclerView recyclerRight;
    private QuYuLeftAdapter leftAdapter;
    private QuYuCenterAdapter centerAdapter;
    private QuYuRightAdapter rightAdapter;
    private ArrayList<CityBean> listLeft;
    private ArrayList<CityBean.ChildCityBean> listCenter;
    private ArrayList<CityBean.ChildCityBean.ChildAreaBean> listRight;

    public QuYuPopup(Context context) {
        super(context);
        listLeft = new ArrayList<>();
        listCenter = new ArrayList<>();
        listRight = new ArrayList<>();
        setPopupFadeEnable(true);
        bindEvent();
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.CITYS)
                .params("token", UserInfoUtils.getInstance().getToken())
                .execute(new SimpleCallBack<List<CityBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<CityBean> datas) {
                        listCenter.clear();
                        listRight.clear();
                        listLeft.clear();
                        listLeft.addAll(datas);
                        leftAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void bindEvent() {
        ButterKnife.bind(this,getContentView());

//        left
        recyclerLeft = findViewById(R.id.recycler_left);
        recyclerLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        leftAdapter = new QuYuLeftAdapter(listLeft);
        recyclerLeft.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                listCenter.clear();
                listCenter.addAll(listLeft.get(position).getChild_city());
                centerAdapter.notifyDataSetChanged();
            }
        });


//        center
        recyclerCenter.setLayoutManager(new LinearLayoutManager(getContext()));
        centerAdapter = new QuYuCenterAdapter(listCenter);
        recyclerCenter.setAdapter(centerAdapter);
        centerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                listRight.clear();
                listRight.addAll(listCenter.get(position).getChild_area());
                rightAdapter.notifyDataSetChanged();
            }
        });

//right
        recyclerRight = findViewById(R.id.recycler_right);
        recyclerRight.setLayoutManager(new LinearLayoutManager(getContext()));
        rightAdapter = new QuYuRightAdapter(listRight);
        recyclerRight.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mOnQuYuSelectListener!=null){
                    mOnQuYuSelectListener.onQuYuSelect(listRight.get(position));
                }
                dismiss();
            }
        });
    }

    public interface OnQuYuSelectListener{
        void onQuYuSelect(CityBean.ChildCityBean.ChildAreaBean bean);
    }
    private OnQuYuSelectListener mOnQuYuSelectListener;
    public void setOnQuYuSelectListener(OnQuYuSelectListener listener){
        mOnQuYuSelectListener = listener;
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_quyu);
    }


}
