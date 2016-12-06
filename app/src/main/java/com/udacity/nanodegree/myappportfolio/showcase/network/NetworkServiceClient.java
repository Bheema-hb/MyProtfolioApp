package com.udacity.nanodegree.myappportfolio.showcase.network;

import android.util.Base64;

import com.udacity.nanodegree.myappportfolio.BuildConfig;
import com.udacity.nanodegree.myappportfolio.showcase.utilities.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bheema on 12/6/16.
 */

public class NetworkServiceClient {
    private static NetworkServiceClient sInstance;
    private final OkHttpClient mClient;


    public static NetworkServiceClient getInstance() {
        if(sInstance == null) {
            sInstance = new NetworkServiceClient();
        }
        return sInstance;
    }

    private NetworkServiceClient(){

        OkHttpClient.Builder httpBuilder= new OkHttpClient.Builder();
        httpBuilder.connectTimeout(Constants.TIMEOUT_MS, TimeUnit.MILLISECONDS);
        httpBuilder.readTimeout(Constants.TIMEOUT_MS, TimeUnit.MILLISECONDS);
        httpBuilder.retryOnConnectionFailure(false);
        httpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.header("Accept", "application/json");
                requestBuilder.header("Content-Type", "application/json");
                requestBuilder.method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.IS_PRODUCTION) {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        httpBuilder.addInterceptor(logging);
        mClient = httpBuilder.build();
    }


    public <S> S getService(Class<S> serviceClass, String baseUrl){
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(mClient).build();
        return retrofit.create(serviceClass);

    }
}
