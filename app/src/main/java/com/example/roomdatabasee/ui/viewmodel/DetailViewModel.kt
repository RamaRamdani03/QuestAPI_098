package com.example.roomdatabasee.ui.viewmodel

import com.example.roomdatabasee.model.Mahasiswa

sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}