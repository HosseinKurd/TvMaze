package com.hosseinkurd.tvmaze.presenters;

import com.hosseinkurd.tvmaze.models.MovieMdl;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

class APIClient {

    static Retrofit getDefaultClient() {
        Interceptor interceptor = chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            builder.header("X-DEVELOPER", "KURDIA");
            Request request = builder.method(original.method(), original.body()).build();
            return chain.proceed(request);
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                // .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public interface APIInterface {
        @GET("shows")
        Observable<Response<List<MovieMdl>>> movies();

        @GET("shows/:{id}")
        Observable<Response<List<MovieMdl>>> movieDetail(@FieldMap Map<String, String> fields);
    }

}