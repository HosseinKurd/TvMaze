package com.hosseinkurd.tvmaze.presenters;

import com.hosseinkurd.tvmaze.models.MovieMdl;

import java.util.ArrayList;

public interface MoviesListener {
    void onMoviesResponse(int exception, ArrayList<MovieMdl> items);
}