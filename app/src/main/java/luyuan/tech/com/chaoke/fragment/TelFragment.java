package luyuan.tech.com.chaoke.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.TelAdapter;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class TelFragment extends Fragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    List<Object> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tel, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        TelAdapter adapter = new TelAdapter(list);
        rv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
