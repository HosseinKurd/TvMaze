package com.hosseinkurd.tvmaze.views.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hosseinkurd.kurdiautils.toolbox.helpers.AMH;
import com.hosseinkurd.kurdiautils.toolbox.helpers.LTH;
import com.hosseinkurd.kurdiautils.toolbox.helpers.UIH;
import com.hosseinkurd.kurdiautils.toolbox.helpers.WMH;
import com.hosseinkurd.kurdiautils.views.fragments.AbsBaseFrg;
import com.hosseinkurd.tvmaze.R;
import com.hosseinkurd.tvmaze.models.MovieMdl;
import com.hosseinkurd.tvmaze.presenters.MoviesListener;
import com.hosseinkurd.tvmaze.presenters.MoviesPresenter;
import com.hosseinkurd.tvmaze.presenters.adapters.MovieAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class MoviesFrg extends AbsBaseFrg implements MoviesListener {

    private final String ArgLoading = "ARG_Loading";
    private ArrayList<MovieMdl> movies = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private MoviesPresenter moviesPresenter;
    private RecyclerView recyclerView;
    private int position = 0;
    private boolean isLoading = false;

    public static MoviesFrg newInstance() {
        Bundle args = new Bundle();
        MoviesFrg fragment = new MoviesFrg();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(AMH.ArgItems, movies);
        outState.putInt(AMH.ArgPosition, position);
        outState.putBoolean(ArgLoading, isLoading);
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet()) {
                if (key.equals(AMH.ArgItems)) {
                    movies = savedInstanceState.getParcelableArrayList(key);
                } else if (key.equals(ArgLoading)) {
                    isLoading = savedInstanceState.getBoolean(key);
                }
            }
        }
        View view = inflater.inflate(R.layout.frg_movies, container, false);
        setResources(view);
        return view;
    }

    @Override
    protected void setPreView() {
        moviesPresenter = new MoviesPresenter();
        moviesPresenter.attachListener(this);
    }

    @Override
    protected void setResources(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.swipe_1,
                R.color.swipe_2,
                R.color.swipe_3,
                R.color.swipe_4,
                R.color.swipe_5);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (recyclerView.getAdapter() != null) {
                recyclerView.setAdapter(null);
            }
            getContent();
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isLoading) {
                    return;
                }
                if (recyclerView.getAdapter() == null) {
                    return;
                }
                if (recyclerView.getLayoutManager() == null) {
                    return;
                }
                if (recyclerView.getAdapter().getItemCount() > 0) {
                    GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                    position = layoutManager.findFirstVisibleItemPosition();
                    if (layoutManager.findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 2) {
                        getContent();
                    }
                }
            }
        });*/
        getContent();
    }

    @Override
    public void onDetach() {
        moviesPresenter.detachListener();
        super.onDetach();
    }

    private void getContent() {
        if (!WMH.isConnected(Objects.requireNonNull(getContext()))) {
            showSnackBar(R.string.connect_to_internet, v -> getContent());
            return;
        }
        moviesPresenter.checkForUpdate();
    }

    private void initRecyclerView() {
        MovieAdapter movieAdapter = new MovieAdapter(movies);
        movieAdapter.setItems(movies);
        movieAdapter.setOnItemClickListener((viewId, position, item) -> {
            if (viewId == R.id.img_adapter_movie_item) {

            }
        });

        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onMoviesResponse(int exception, ArrayList<MovieMdl> items) {
        LTH.eLog("TAG_TAG", "exception : " + exception);
        swipeRefreshLayout.setRefreshing(false);
        if (exception == WMH.SUCCESS) {
            LTH.eLog("TAG_TAG", "items : " + items.size());
            movies = items;
            initRecyclerView();
        } else if (exception == 404) {

        } else if (exception == 500) {

        }
    }

    /**
     * Show SnackBar By Resource ID
     *
     * @param resId    The resource id of the string resource to use. Can be formatted text.
     * @param listener {@link View.OnClickListener} as Callback
     */
    protected void showSnackBar(@StringRes int resId, View.OnClickListener listener) {
        showSnackBar(getString(resId), listener);
    }

    /**
     * Show SnackBar By String Parameter
     *
     * @param message  The Message to show. Can be formatted text.
     * @param listener {@link View.OnClickListener} as Callback
     */
    protected void showSnackBar(String message, final View.OnClickListener listener) {
        final Snackbar snackbar = Snackbar.make(Objects.requireNonNull(getActivity()).getWindow().getDecorView(), message, Snackbar.LENGTH_INDEFINITE);
        View sbView = snackbar.getView();
        UIH.setMargin(sbView, 0, 0, 0, UIH.setPercentW(0.15));
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.YELLOW);

        if (listener != null) {
            snackbar.setAction(getString(R.string.again), v -> {
                snackbar.dismiss();
                listener.onClick(v);
            });
        }
        snackbar.show();
    }

}