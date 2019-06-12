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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.DaiGenJinAdapter;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class DaiGenJinFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(1);
        }
        recycler.setAdapter(new DaiGenJinAdapter(list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
