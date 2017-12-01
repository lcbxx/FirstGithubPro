package com.itogame.momoup.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 *  retrofit通用样式
 */

public interface RetrofitHttpService {

    @GET()
    Call<String> get(@Url String url, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST()
    Call<String> post(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @GET()
    Observable<ResponseBody> Obget(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> Obpost(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

}
