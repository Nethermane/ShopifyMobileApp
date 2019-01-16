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
import com.nishimura.android.shopifyapp.data.network.response.ProductIDsResponse
import com.nishimura.android.shopifyapp.data.network.response.ProductsResponse

const val access_token = "c32313df0d0ef512ca64d5b336a0d7c6"


interface ShopifyApiService {
    //https://shopicruit.myshopify.com/admin/products.json?access_token=c32313df0d0ef512ca64d5b336a0d7c6
    @GET("products.json")
    fun getProductsById(
        @Query("ids") ids: Array<Long>? = null
    ): Deferred<ProductsResponse>

    //https://shopicruit.myshopify.com/admin/collects.json?collection_id=68424466488&access_token=c32313df0d0ef512ca64d5b336a0d7c6
    @GET("collects.json")
    fun getProductIDsFromCollection(
        @Query("collection_id") collectionId: String = ""
    ): Deferred<ProductIDsResponse>

    //https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
    @GET("custom_collections.json")
    fun getCollections(): Deferred<CustomCollectionsResponse>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): ShopifyApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(
                        "access_token",
                        access_token
                    )
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
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