package com.example.sportexample.di

import com.example.sportexample.data.model.AllTeams
import com.example.sportexample.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePopularMovies(): AllTeams {
        return AllTeams()
    }

    @Singleton
    @Provides
    fun provideApiCLient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.thesportsdb.com/")
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }

//    @Singleton
//    @Provides
//    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .build()
//    }
//
//
//    @Singleton
//    @Provides
//    fun provideAuthInterceptor(): Interceptor {
//        return Interceptor { chain->
//            val url: HttpUrl = chain.request().url().newBuilder()
//                .addQueryParameter("api_key", BuildConfig.MOVIE_API_KEY)
//                .build()
//            val request=chain.request().newBuilder()
//                .url(url)
//                .build()
//            chain.proceed(request)
//        }
//    }




//    @Singleton
//    @Provides
//    fun provideRetrofit(addClient: RetrofitHelper): Retrofit {
//        return addClient.getRetrofit()
//    }



//    @Singleton
//    @Provides
//    fun provideOkHttpClient(
//        authInterceptor: MovieAuthInterceptor
//    ): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .build()
//    }



//    @Singleton
//    @Provides
//    fun provideAuthInterceptor(
//        authInterceptor: MovieAuthInterceptor
//    ): Interceptor {
//        return authInterceptor
//    }

}