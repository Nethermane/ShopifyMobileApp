package com.nishimura.android.shopifyapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.nishimura.android.shopifyapp.data.network.response.CustomCollectionsResponse

const val access_token = "c32313df0d0ef512ca64d5b336a0d7c6"
const val page_num = "1"

//https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
interface ShopifyApiService {

    @GET("custom_collections.json")
    fun getCollections(
        @Query("page") pageNum: String = page_num,
        @Query("access_token") accessToken: String = access_token
    ): Deferred<CustomCollectionsResponse>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): ShopifyApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("page", page_num)
                    .addQueryParameter("access_token",
                        access_token
                    )
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor  chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://shopicruit.myshopify.com/admin/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopifyApiService::class.java)
        }
    }
}