package com.example.roomdatabasee.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.roomdatabasee.ui.navigasi.DestinasiNavigasi

object DestinasiDetail: DestinasiNavigasi{
    override val route = "detail"
    override val titleRes = "Detail Mhs"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
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