package dev.pauloos.githubsearchrepos.framework.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val apitoken: String)
    : Interceptor
{
    override fun intercept(chain: Interceptor.Chain): Response
    {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $apitoken")
            .build()

        return chain.proceed(newRequest)
    }
}
