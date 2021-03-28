package com.example.ab.transformers.di

import android.app.Application
import android.content.SharedPreferences
import com.example.ab.transformers.data.repo.token.TokenRepo
import com.example.ab.transformers.data.repo.transformers.TransformersRemoteDataSource
import com.example.ab.transformers.data.repo.transformers.TransformersRepo
import com.example.ab.transformers.data.rest.TransformersEndpoints
import com.example.ab.transformers.screens.main.MainViewModel
import com.example.ab.transformers.screens.splash.SplashViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sin

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel(get(), get()) }
}

val repoModule = module {
    single { TokenRepo(get(), get()) }
    single { TransformersRemoteDataSource(get()) }
    single { TransformersRepo(get<TransformersRemoteDataSource>()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideTransformersEndpoints(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideGsonConverterFactory(get()) }
    single { provideGson() }
    single{ getSharedPrefs(androidApplication()) }
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://transformers-api.firebaseapp.com/")
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
    return GsonConverterFactory.create(gson)
}

fun provideGson():Gson {
    return GsonBuilder().setLenient().create()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideTransformersEndpoints(retrofit: Retrofit): TransformersEndpoints = retrofit.create(TransformersEndpoints::class.java)

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return  androidApplication.getSharedPreferences("default",  android.content.Context.MODE_PRIVATE)
}