package com.hosseinkurd.tvmaze.presenters.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hosseinkurd.kurdiautils.toolbox.helpers.LTH;
import com.hosseinkurd.tvmaze.R;
import com.hosseinkurd.tvmaze.models.MovieMdl;

import java.util.List;

public class MovieAdapter extends RcvBaseAdapter<MovieAdapter.MovieViewHolder> {

    public MovieAdapter(List items) {
        super(items);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ctxAdapter = viewGroup.getContext();
        inflater = LayoutInflater.from(ctxAdapter);
        return new MovieViewHolder(inflater.inflate(R.layout.adapter_movie_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        holder.onBind(holder.getAdapterPosition());
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgThumb;

        MovieViewHolder(View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_adapter_movie_item);

        }

        void onBind(final int position) {
            imgThumb.post(() -> {
                imgThumb.getLayoutParams().height = (int) (imgThumb.getMeasuredWidth() * 1.470588235);
                // LTH.eLog("TAG_TAG", "MeasuredWidth : " + imgThumb.getMeasuredWidth());
                // LTH.eLog("TAG_TAG", "MeasuredHeight : " + imgThumb.getMeasuredHeight());
            });
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.colorAccent)
                    .fallback(R.color.swipe_3)
                    .error(R.color.swipe_3);

            Glide.with(ctxAdapter)
                    .load(getImage(position))
                    .apply(requestOptions)
                    .into(imgThumb);
        }

        private MovieMdl getItem(int position) {
            return (MovieMdl) items.get(position);
        }

        private String getImage(int position) {
            if (getItem(position).getImage() != null) {
                return getItem(position).getImage().getOriginal();
            }
            return "";
        }
    }

}