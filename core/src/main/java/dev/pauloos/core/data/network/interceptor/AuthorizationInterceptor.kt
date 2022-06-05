package dev.pauloos.core.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.toHeaderList

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
