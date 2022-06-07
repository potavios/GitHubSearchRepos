package dev.pauloos.githubsearchrepos.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pauloos.githubsearchrepos.framework.network.interceptor.AuthorizationInterceptor
import dev.pauloos.githubsearchrepos.BuildConfig
import dev.pauloos.githubsearchrepos.framework.network.GitHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor
    {
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG)
                {
                    HttpLoggingInterceptor.Level.BODY
                }
                else HttpLoggingInterceptor.Level.NONE
            )
        }
    }

    @Provides
    fun provideAuthorizationInterceptor(): AuthorizationInterceptor
    {
        return AuthorizationInterceptor(apitoken = BuildConfig.GITHUB_TOKEN)
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor)
        : OkHttpClient
    {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory
    {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory) : GitHubApi
    {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(GitHubApi::class.java)
    }
}