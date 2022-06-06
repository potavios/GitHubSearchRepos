package dev.pauloos.githubsearchrepos.factory.response

import dev.pauloos.githubsearchrepos.framework.network.response.DataWrapperResponse
import dev.pauloos.githubsearchrepos.framework.network.response.ItemsResponse
import dev.pauloos.githubsearchrepos.framework.network.response.OwnerResponse

class DataWrapperResponseFactory
{
    fun create() = DataWrapperResponse(
        totalCount = 1000,
        items = listOf(
            ItemsResponse(
                "0",
                "okHttp",
                OwnerResponse(
                    "https://avatars.githubusercontent.com/u/82592?v=4",
                    "square"
                ),
                1900,
                345),

            ItemsResponse(
                "1",
                "RxKotlin",
                OwnerResponse(
                    "https://avatars.githubusercontent.com/u/82592?v=4",
                    "ReactiveX"
                ),
                6861,
                461),
        )
    )
}