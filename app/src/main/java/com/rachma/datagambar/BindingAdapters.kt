
package com.rachma.datagambar

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rachma.datagambar.network.MarsPhoto
import com.rachma.datagambar.overview.MarsApiStatus
import com.rachma.datagambar.overview.PhotoGridAdapter

// Untuk mendeklarasikan fungsi yang bindRecyclerView
// Untuk memperbarui data yang ditampilkan di [RecyclerView]
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {

    // Untuk mendeklarasikan variabel yang bernama adapter
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

// Untuk mendeklarasikan fungsi yang bernama bindImage
// Untuk menggunakan perpustakaan Coil untuk memuat gambar dengan URL ke dalam [ImageView]
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

// Untuk mendeklarasikan fungsi yang bernama bindStatus
// Adaptor pengikat ini menampilkan [MarsApiStatus] permintaan jaringan dalam tampilan gambar
// Jika permintaan memiliki kesalahan, itu menampilkan gambar yang rusak untuk mencerminkan kesalahan koneksi. Ketika permintaan selesai, itu menyembunyikan tampilan gambar.
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
