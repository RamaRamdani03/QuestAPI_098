package com.example.roomdatabasee.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.roomdatabasee.model.Mahasiswa
import com.example.roomdatabasee.ui.navigasi.DestinasiNavigasi
import com.example.roomdatabasee.ui.viewmodel.DetailUiState

object DestinasiDetail: DestinasiNavigasi{
    override val route = "detail"
    override val titleRes = "Detail Mhs"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}

@Composable
fun DetailStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState
) {
    when (detailUiState) {
        is DetailUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailUiState.Success -> {
            if (detailUiState.mahasiswa.nim.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Data tidak ditemukan.")
                }
            } else {
                ItemDetailMhs(
                    mahasiswa = detailUiState.mahasiswa,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }

        is DetailUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailMhs(
    modifier: Modifier = Modifier,
    mahasiswa: Mahasiswa
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMhs(judul = "NIM", isinya = mahasiswa.nim)
            ComponentDetailMhs(judul = "Nama", isinya = mahasiswa.nama)
            ComponentDetailMhs(judul = "Alamat", isinya = mahasiswa.alamat)
            ComponentDetailMhs(judul = "Jenis Kelamin", isinya = mahasiswa.jenisKelamin)
            ComponentDetailMhs(judul = "Kelas", isinya = mahasiswa.kelas)
            ComponentDetailMhs(judul = "Angkatan", isinya = mahasiswa.angkatan)
        }
    }
}

@Composable
fun ComponentDetailMhs(
    modifier: Modifier =Modifier,
    judul: String,
    isinya: String,
){
    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start)
    {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}