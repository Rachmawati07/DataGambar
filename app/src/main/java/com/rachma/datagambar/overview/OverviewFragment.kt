
package com.rachma.datagambar.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rachma.datagambar.databinding.FragmentOverviewBinding

// Untuk mendeklarasikan class yang bernama OverviewFragment
// Fragmen ini menunjukkan status transaksi layanan web foto Mars.
class OverviewFragment : Fragment() {

    // Untuk mendeklarasikan variabel yang bernama viewModel
    private val viewModel: OverviewViewModel by viewModels()

    // Untuk menginflate layout dengan Data Binding, menetapkan pemilik  lifecycle ke OverviewFragment
    // Untuk mengaktifkan data binding  untuk mengamati LiveData, dan menyiapkan RecyclerView dengan adaptor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Untuk mendeklarasikan variabel yang bernama binding
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Untuk mengijinkan data binding untuk Mengamati LiveData dengan lifecycle Fragmen ini
        binding.lifecycleOwner = this

        // Untuk memberikan akses binding ke OverviewViewModel
        binding.viewModel = viewModel

        // Untuk menyetel adaptor photosGrid RecyclerView
        binding.photosGrid.adapter = PhotoGridAdapter()

        // Untuk mengembalikan root binding
        return binding.root
    }
}
