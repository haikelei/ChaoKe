package luyuan.tech.com.chaoke.fragment;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idtk.smallchart.chart.LineChart;
import com.idtk.smallchart.data.LineData;
import com.idtk.smallchart.interfaces.iData.ILineData;

import java.util.ArrayList;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.utils.SizeUtils;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class LineFragment extends Fragment {

    private LineChart lineChart;
    private LineData mLineData = new LineData();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();
    float[][] points = new float[][]{{1,10}, {2,47}, {3,11}, {4,38}, {5,9},{6,52}, {7,14}, {8,37}, {9,29}, {10,31}};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_zhexian, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lineChart = view.findViewById(R.id.chart);
        loadData();

    }

    private void loadData() {
        for (int i = 0; i < 8; i++) {
            mPointArrayList.add(new PointF(points[i][0], points[i][1]));
        }
        mLineData.setValue(mPointArrayList);
        mLineData.setColor(Color.MAGENTA);
        mLineData.setPaintWidth(SizeUtils.px2dp(getContext(),3));
        mLineData.setTextSize(SizeUtils.px2dp(getContext(),10));
        lineChart.setData(mLineData);
    }
}
