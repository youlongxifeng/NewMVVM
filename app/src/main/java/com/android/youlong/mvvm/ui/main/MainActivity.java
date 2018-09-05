package com.android.youlong.mvvm.ui.main;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.youlong.mvvm.R;
import com.android.youlong.mvvm.bean.Gank;
import com.android.youlong.mvvm.bean.Result;
import com.android.youlong.mvvm.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainManagerViewModel mainManagerViewModel = new MainManagerViewModel();
    ActivityMainBinding binding;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<Result> mDatas;

    private RefreshAdapter mRefreshAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //setContentView(R.layout.activity_main);
        binding.setModel(mainManagerViewModel);
        mainManagerViewModel.getGank();
        initRecylerView();
        initListener();
    }

    private void initRecylerView() {
        mRefreshAdapter = new RefreshAdapter(this, mDatas);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mRefreshAdapter);

    }

    private void initPullRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<Result> headDatas = new ArrayList<Result>();
                mRefreshAdapter.AddHeaderItem(headDatas);
                //刷新完成
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initListener() {
        initPullRefresh();
        initLoadMoreListener();
    }

    private void initLoadMoreListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRefreshAdapter.getItemCount()) {
                    List<Result> footerDatas = new ArrayList<Result>();
                    mRefreshAdapter.AddFooterItem(footerDatas);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

    }
}
