package com.hosseinkurd.tvmaze.presenters;

public class Configs {

    private static Configs instance;

    public static Configs getInstance() {
        if (instance == null) {
            instance = new Configs();
        }
        return instance;
    }


}