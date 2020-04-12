package com.fundroid.marvelheroes.api

import com.fundroid.marvelheroes.api.model.MarvelAPIResponse
import com.fundroid.marvelheroes.commom.Utils
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MarvelAPI {


    @GET("characters")
    fun getMarvelCharactersAsync(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Deferred<Response<MarvelAPIResponse>>

    @GET("characters/{character_id}")
    fun getMarvelCharacterDetailAsync(
        @Path("character_id") characterId: Int,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Deferred<Response<MarvelAPIResponse>>


    companion object {
        fun getApi(): MarvelAPI {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url()
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", PUBLIC_API_KEY)
                        .addQueryParameter("ts", timestamp.toString())
                        .addQueryParameter("hash", hash)
                        .build()
                    val requestBuilder = original.newBuilder()
                        .url(url)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }.build()

            return Retrofit.Builder()
                .baseUrl(URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(httpClient)
                .build()
                .create(MarvelAPI::class.java)
        }


        private val URL = "https://gateway.marvel.com//v1/public/"
        private const val PUBLIC_API_KEY: String = "6357e1722cfbb51fc0b8b3a2b957a75a"
        private val PRIVATE_API_KEY: String = "588c70ac52c961a42f73e171f2e653434c3c49e9"
        val timestamp = Date().time
        val hash = Utils.md5(timestamp.toString() + PRIVATE_API_KEY + PUBLIC_API_KEY)


    }


}