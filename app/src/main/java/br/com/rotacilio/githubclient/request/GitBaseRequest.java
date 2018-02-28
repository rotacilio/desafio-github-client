package br.com.rotacilio.githubclient.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import br.com.rotacilio.githubclient.common.CallbackRequest;
import br.com.rotacilio.githubclient.common.InternetConnection;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rodrigo on 28/02/18.
 */
public abstract class GitBaseRequest {

    private static final String BASE_URL = "https://api.github.com/";
    public GitServices gitServices;
    public CallbackRequest callbackRequest;

    public void call(CallbackRequest callbackRequest, final Context context){
        this.callbackRequest = callbackRequest;

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {


                        if(InternetConnection.internetAvailable(context)){

                            Response originalResponse = chain.proceed(chain.request());

                            CacheControl cacheControl = new CacheControl.Builder()
                                    .maxAge(5, TimeUnit.MINUTES)
                                    .build();

                            return originalResponse.newBuilder()
                                    .header("Cache-Control", cacheControl.toString())
                                    .build();

                        } else {

                            Request request = chain.request();

                            CacheControl cacheControl = new CacheControl.Builder()
                                    .maxStale(1, TimeUnit.DAYS)
                                    .build();

                            request.newBuilder()
                                    .cacheControl(cacheControl)
                                    .build();

                            return chain.proceed(request);
                        }
                    }
                })
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gitServices = retrofit.create(GitServices.class);
    }


}
