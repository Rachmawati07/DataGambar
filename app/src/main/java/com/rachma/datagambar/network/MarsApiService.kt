
package com.rachma.datagambar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Untuk mendeklarasikan variabel yang bernama BASE_URL
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

// Untuk mendeklarasikan variabel yang bernama moshi
// Untuk membangun objek Moshi yang akan digunakan Retrofit, dan menambahkan adaptor Kotlin untuk kompatibilitas penuh Kotlin.
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Untuk mendeklarasikan variabel yang bernama retrofit
// Retrofit untuk membuat objek retrofit menggunakan konverter Moshi dengan objek Moshi
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Untuk mendeklarasikan interface yang bernama MarsApiService
// Merupakan interface publik yang memperlihatkan metode [getPhotos]
interface MarsApiService {

     // Untuk mengembalikan [Daftar] dari [MarsPhoto] dan metode ini dapat dipanggil dari Coroutine.
     // Anotasi @GET menunjukkan bahwa titik akhir "foto" akan diminta dengan GET Metode HTTP
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

// Untuk mendeklarasikan objek yang bernama MarsApi
// Sebuah Objek Api publik yang mengekspos layanan Retrofit yang diinisialisasi
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
