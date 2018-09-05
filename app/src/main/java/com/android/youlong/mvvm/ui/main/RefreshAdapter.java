package com.android.youlong.mvvm.ui.main;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.youlong.mvvm.BR;
import com.android.youlong.mvvm.R;
import com.android.youlong.mvvm.bean.Gank;
import com.android.youlong.mvvm.bean.Result;
import com.android.youlong.mvvm.databinding.ItemRefreshRecylerviewBinding;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.android.youlong.mvvm.ui.main
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: xiezhenggen
 * @date: 2018/9/5 12:21
 * @Copyright: 2018  xzg All rights reserved.
 */


public class RefreshAdapter extends RecyclerView.Adapter<RefreshAdapter.ItemViewHolder> {

    Context mContext;
    LayoutInflater mInflater;
    List<Result> mDatas;


    public RefreshAdapter(Context context, List<Result> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRefreshRecylerviewBinding binding;//audite_task_layout_item.xml
        binding = ItemRefreshRecylerviewBinding.inflate(mInflater, parent, false);
        //binding=DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_refresh_recylerview,parent,false);
        return new ItemViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemRefreshRecylerviewBinding binding = (ItemRefreshRecylerviewBinding) holder.getBinding();//audite_task_layout_item.xml
        binding.setVariable(BR.model, mDatas.get(position));
        binding.setResultitem(mDatas.get(position));
        binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding mDataBinding;

        public ItemViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            mDataBinding = itemView;
        }

        public ViewDataBinding getBinding() {
            return mDataBinding;
        }

    }


    public void AddHeaderItem(List<Result> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<Result> items) {
        mDatas.addAll(items);
        notifyDataSetChanged();
    }
}
