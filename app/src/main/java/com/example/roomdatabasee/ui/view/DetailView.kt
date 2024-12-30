package com.example.roomdatabasee.ui.view

import com.example.roomdatabasee.ui.navigasi.DestinasiNavigasi

object DestinasiDetail: DestinasiNavigasi{
    override val route = "detail"
    override val titleRes = "Detail Mhs"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}