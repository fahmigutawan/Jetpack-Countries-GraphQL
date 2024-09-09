package com.example.countriesgraphql.utils

import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Query
import kotlinx.coroutines.flow.flow

fun <T:Query.Data> getResponse(
    block: suspend () -> ApolloResponse<T>
) = flow {
    emit(Resource.Loading())
    try {
        val res = block()
        emit(Resource.Success(res.dataOrThrow()))
    } catch (e: Exception) {
        emit(Resource.Error(message = e.message.toString()))
    }
}