
package com.rachma.datagambar.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rachma.datagambar.network.MarsApi
import com.rachma.datagambar.network.MarsPhoto
import kotlinx.coroutines.launch

// Untuk mendeklarasikan class MarsApiStatus
enum class MarsApiStatus { LOADING, ERROR, DONE }

// Untuk mendeklarasikan class yang bernama OverviewViewModel
// Merupakan viewModel] yang dilampirkan ke [OverviewFragment].
class OverviewViewModel : ViewModel() {

    // Untuk mendeklarasikan variabel _status
    // MutableLiveData internal yang menyimpan status permintaan terbaru
    private val _status = MutableLiveData<MarsApiStatus>()

    // Untuk mendeklarasikan variabel yang bernama status
    // Merupakan LiveData eksternal yang tidak dapat diubah untuk status permintaan
    val status: LiveData<MarsApiStatus> = _status

    // Untuk mendeklarasikan variabel _photos
    // Secara internal, menggunakan MutableLiveData, karena  akan memperbarui Daftar MarsPhoto dengan nilai baru
    private val _photos = MutableLiveData<List<MarsPhoto>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val photos: LiveData<List<MarsPhoto>> = _photos

    // Untuk memanggil getMarsPhotos() pada init agar kami dapat segera menampilkan status.
    init {
        getMarsPhotos()
    }

    // untuk mendeklarasikan fungsi yang bernama getMarsPhotos
    // Untuk mendapatkan informasi foto Mars dari layanan Mars API Retrofit dan memperbarui [MarsPhoto] [Daftar] [LiveData].
    private fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}
