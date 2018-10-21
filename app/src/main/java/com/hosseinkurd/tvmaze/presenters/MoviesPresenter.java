package com.hosseinkurd.tvmaze.presenters;

import com.hosseinkurd.kurdiautils.toolbox.helpers.LTH;
import com.hosseinkurd.tvmaze.models.MovieMdl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MoviesPresenter implements Presenter<MoviesListener> {

    private MoviesListener listener;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attachListener(MoviesListener listener) {
        this.listener = listener;
    }

    @Override
    public void detachListener() {
        listener = null;
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void checkForUpdate() {
        compositeDisposable.add(APIClient.getDefaultClient().create(APIClient.APIInterface.class).movies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<MovieMdl>>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LTH.eLog("TAG_TAG", "Throwable : " + e.getMessage(), e);
                        listener.onMoviesResponse(500, null);
                    }

                    @Override
                    public void onNext(Response<List<MovieMdl>> response) {
                        int code = response.code();
                        ArrayList<MovieMdl> items = new ArrayList<>();
                        if (response.body() != null) {
                            items.addAll(response.body());
                        } else {
                            code = 404;
                        }
                        listener.onMoviesResponse(code, items);
                    }
                }));
    }
}