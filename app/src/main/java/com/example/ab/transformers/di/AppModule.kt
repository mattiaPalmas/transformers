package com.example.ab.transformers.di

import android.app.Application
import android.content.SharedPreferences
import com.example.ab.transformers.data.rest.TransformersEndpoints
import com.example.ab.transformers.screens.main.MainViewModel
import com.example.ab.transformers.screens.splash.SplashViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideTransformersEndpoints(get()) }
    single { provideRetrofit(get()) }
    single{ getSharedPrefs(androidApplication()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://transformers-api.firebaseapp.com/").client(okHttpClient).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideTransformersEndpoints(retrofit: Retrofit): TransformersEndpoints = retrofit.create(TransformersEndpoints::class.java)

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return  androidApplication.getSharedPreferences("default",  android.content.Context.MODE_PRIVATE)
}