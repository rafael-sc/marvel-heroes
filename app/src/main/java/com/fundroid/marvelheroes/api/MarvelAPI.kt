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
import retrofit2.http.Query
import java.util.*

interface MarvelAPI {


//
//    @GET("/v1/public/characters")
//    fun charactersAsync(
//        @Query("limit") limit: Int = 20,
//        @Query("offset") offset: Int=0
//    ): Deferred<Response<MarvelCharacter>>
//
//        @GET("characters")
//        fun getMarvelHeroesAsync(
//            @Query(20) limit: Int,
//            @Query(0) offset: Int): Deferred<MarvelHeroResponse>
//
//        @GET("characters/{$PARAM_CHARACTER_ID}/comics")
//        fun getMarvelComicsAsync(@Path(PARAM_CHARACTER_ID) heroId: Int): Deferred<MarvelComicsResponse>
//
//        @GET("series")
//        fun getMarvelSeries(@Query(Definitions.PARAM_LIMIT) limit: Int, @Query(Definitions.PARAM_OFFSET) offset: Int): Deferred<MarvelSeriesResponse>


    @GET("/v1/public/characters")
    fun getMarvelCharactersAsync(
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


        const val URL = "https://gateway.marvel.com/"
        const val PUBLIC_API_KEY: String = "6357e1722cfbb51fc0b8b3a2b957a75a"
        const val PRIVATE_API_KEY: String = "588c70ac52c961a42f73e171f2e653434c3c49e9"
        val timestamp = Date().time
        val hash = Utils.md5(timestamp.toString() + PRIVATE_API_KEY + PUBLIC_API_KEY)


    }


}