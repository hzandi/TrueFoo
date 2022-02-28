package com.truecaller.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.truecaller.data.BuildConfig
import com.truecaller.data.LocalStorage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebserviceFactory @Inject constructor(private val localStorage: LocalStorage) {

    private companion object {
        const val API_BASE_URL = "https://blog.truecaller.com/"
        const val HEADER_NAME_AUTHORIZATION = "token"
        const val HEADER_NAME_APP = "app"
        const val HEADER_VALUE_ANDROID = "ANDROID"
    }

    fun <S> createService(serviceClass: Class<S>): S =
        retrofitApi.create(serviceClass)

    private val retrofitApi = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(HtmlPageAdapter.FACTORY)
        .client(getOkHttpClient())
        .build()

    private fun getOkHttpClient(): OkHttpClient {

        val okHttpBuilder = OkHttpClient.Builder()
            .addInterceptor(HeadersInterceptor())

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor()
                .apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
                .let {
                    okHttpBuilder.addInterceptor(it)
                }
        }

        return okHttpBuilder.build()

    }

    private inner class HeadersInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {

            val original = chain.request()

            val request = original.newBuilder()
                .header(
                    HEADER_NAME_AUTHORIZATION,
                    localStorage.getUserToken() ?: ""
                )
                .header(
                    HEADER_NAME_APP,
                    HEADER_VALUE_ANDROID
                )
                .method(original.method, original.body)
                .build()

            return chain.proceed(request)

        }

    }

    private fun getGSONBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

}
