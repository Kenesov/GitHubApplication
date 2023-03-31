package com.example.githubapplication.di

import com.example.githubapplication.domain.repository.MainRepository
import com.example.githubapplication.domain.repository.MainRepositoryImpl
import com.example.githubapplication.domain.usecase.MainUseCase
import com.example.githubapplication.domain.usecase.MainUseCaseImpl
import com.example.githubapplication.presentation.MainViewModel
import com.example.githubapplication.retrafit.GitHubApi
import com.example.githubapplication.retrafit.GitHubInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val domainModule = module {


    single<MainRepository> {
        MainRepositoryImpl(api = get())
    }

    factory<MainUseCase> {
        MainUseCaseImpl(repo = get())
    }

    fun provideRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

        val interceptor = GitHubInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com")
            .client(client)
            .build()
        return retrofit
    }

    fun provideApi(retrofit: Retrofit) : GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

    single {
        provideRetrofit()
    }

    single {
        provideApi(retrofit = get())
    }

}

val appModule = module {
    factory<MainViewModel> {
        MainViewModel(useCase = get())
    }
}
