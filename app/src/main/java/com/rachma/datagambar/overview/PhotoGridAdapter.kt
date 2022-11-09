
package com.rachma.datagambar.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rachma.datagambar.databinding.GridViewItemBinding
import com.rachma.datagambar.network.MarsPhoto

// Untuk mendeklarasikan class yang bernama PhotoGridAdapter
// Kelas ini mengimplementasikan [RecyclerView] [ListAdapter] yang menggunakan Data Binding untuk menampilkan [List] data, termasuk menghitung perbedaan antar daftar.
class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {

    // Untuk mendeklarasikan class yang bernama MarsPhotosViewHolder
    // Konstruktor MarsPhotosViewHolder mengambil variabel binding dari yang terkait GridViewItem, yang dengan baik memberikannya akses ke informasi [MarsPhoto] lengkap.
    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            // Untuk memaksa pengikatan data untuk segera dieksekusi, yang memungkinkan RecyclerView membuat pengukuran ukuran tampilan yang benar
            binding.executePendingBindings()
        }
    }

    // Memungkinkan RecyclerView untuk menentukan item mana yang telah berubah saat [Daftar] dari [MarsPhoto] telah diperbarui.
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    // Untuk membuat tampilan item [RecyclerView] baru (dipanggil oleh pengelola layout)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    // Untuk mengganti konten tampilan (dipanggil oleh pengelola layout)
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
