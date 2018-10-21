package com.hosseinkurd.tvmaze.presenters;

public interface Presenter<V> {
    void attachListener(V listener);
    void detachListener();
}