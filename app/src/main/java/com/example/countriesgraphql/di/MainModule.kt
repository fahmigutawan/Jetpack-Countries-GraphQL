package com.example.countriesgraphql.di

import com.apollographql.apollo.ApolloClient
import com.example.countriesgraphql.utils.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideApolloClient() = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .apply {
            addHttpInterceptor(AuthorizationInterceptor("token"))
            //TODO Add more interceptor HERE
        }
        .build()
}