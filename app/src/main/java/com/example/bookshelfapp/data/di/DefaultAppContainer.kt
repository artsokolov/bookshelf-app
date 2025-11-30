package com.example.bookshelfapp.data.di

import com.example.bookshelfapp.data.network.BooksApiService
import com.example.bookshelfapp.data.repository.NetworkBooksRepository
import com.example.bookshelfapp.domain.repository.BooksRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class DefaultAppContainer: AppContainer {
    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/"
    }
}
