package luyuan.tech.com.chaoke.fragment;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.LineChartData;
import luyuan.tech.com.chaoke.bean.ZheXianBean;
import luyuan.tech.com.chaoke.event.LoginEvent;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.LineChart;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class LineFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.line_chart)
    LineChart lineChart;
    private ArrayList<LineChartData> list = new ArrayList<>();
    private int id = 1;//1进客 2出租 3委托

    public static Fragment newInstance(int id) {
        LineFragment fragment = new LineFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_zhexian, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            id = getArguments().getInt("id", 1);
        }
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.ZHEXIANTU)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id + "")
                .execute(new SimpleCallBack<List<ZheXianBean>>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getActivity(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<ZheXianBean> data) {
                        handleData(data);
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(LoginEvent event) {
        if (list.size() == 0&&UserInfoUtils.getInstance().isLogin()) {
            loadData();
        }
    }

    private void handleData(List<ZheXianBean> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        list.clear();
        for (int i = 0; i < data.size(); i++) {
            ZheXianBean bean = data.get(i);
            LineChartData lineChartData = new LineChartData();
            lineChartData.setItem(bean.getMonth()+"月");
            lineChartData.setPoint(bean.getCount());
            list.add(lineChartData);
        }
        lineChart.setData(list);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
