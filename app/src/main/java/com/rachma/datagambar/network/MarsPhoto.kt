
package com.rachma.datagambar.network

import com.squareup.moshi.Json

// Untuk mendeklarasikan class yang bernama MarsPhoto
// Kelas data ini mendefinisikan foto Mars yang menyertakan ID, dan URL gambar.
// Nama properti dari kelas data ini digunakan oleh Moshi untuk mencocokkan nama nilai di JSON.
data class MarsPhoto(
        val id: String,
        // Untuk memetakan img_src dari JSON ke imgSrcUrl di kelas kita
        @Json(name = "img_src") val imgSrcUrl: String
)