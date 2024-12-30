package com.example.roomdatabasee.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasee.model.Mahasiswa
import com.example.roomdatabasee.repository.mahasiswaRepository
import com.example.roomdatabasee.ui.view.DestinasiDetail
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val mhs: mahasiswaRepository
) : ViewModel() {

    var mahasiswaDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    init {
        getMahasiswaById()
    }

    fun getMahasiswaById() {
        viewModelScope.launch {
            mahasiswaDetailState = DetailUiState.Loading
            mahasiswaDetailState = try {
                val mahasiswa = mhs.getMahasiswaById(_nim)
                DetailUiState.Success(mahasiswa)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }
}