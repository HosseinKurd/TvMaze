package com.hosseinkurd.tvmaze.presenters.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class RcvBaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<?> items;
    protected Context ctxAdapter;
    protected LayoutInflater inflater;
    protected OnItemClickListener onItemClickListener;

    public RcvBaseAdapter() {
        this.items = new ArrayList<>();
    }

    public RcvBaseAdapter(List<?> items) {
        this.items = items;
    }

    public void setItems(List<?> items) {
        if (items == null) {
            return;
        }
        this.items = items;
        /*if(this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.addAll(items);*/
    }

    public List<?> getItemList() {
        return items;
    }

    @Override
    public final int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.onItemClickListener = mListener;
    }

    public interface OnItemClickListener {
        void onClicked(int viewId, int position, Object item);
    }

}