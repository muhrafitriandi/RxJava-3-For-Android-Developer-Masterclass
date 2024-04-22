package com.yandey.rxjava3_android.data.remote.network

import com.google.gson.Gson
import com.yandey.rxjava3_android.BuildConfig
import com.yandey.rxjava3_android.data.remote.mock.MockNetworkInterceptor
import com.yandey.rxjava3_android.data.remote.mock.mockTaskResponse
import com.yandey.rxjava3_android.data.remote.EndPoint
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ApiConfig {
    private fun provideOkHttpClient(interceptor: MockNetworkInterceptor, cacheDir: File, cacheSize: Long): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(cacheDir, cacheSize))
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun provideApiService(interceptor: MockNetworkInterceptor, cacheDir: File, cacheSize: Long): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(provideOkHttpClient(interceptor, cacheDir, cacheSize))
            .build()
            .create(ApiService::class.java)
    }

    fun mockApi(cacheDir: File, cacheSize: Long): ApiService {
        return provideApiService(
            MockNetworkInterceptor()
                .mock(
                    "${BuildConfig.BASE_URL}${EndPoint.GET_ALL_TASK}",
                    { Gson().toJson(mockTaskResponse) },
                    200,
                    1500
                )
                .mock(
                    "${BuildConfig.BASE_URL}${EndPoint.ADD_TASK}",
                    { "{}" },
                    200,
                    1500
                )
                .mock(
                    "${BuildConfig.BASE_URL}${EndPoint.EDIT_TASK}",
                    { "{}" },
                    200,
                    1500
                )
                .mock(
                    "${BuildConfig.BASE_URL}${EndPoint.DELETE_TASK}",
                    { "{}" },
                    200,
                    1500
                ),
            cacheDir,
            cacheSize
        )
    }
}