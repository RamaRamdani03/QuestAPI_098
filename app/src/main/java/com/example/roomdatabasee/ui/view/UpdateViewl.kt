package com.example.roomdatabasee.ui.view

import com.example.roomdatabasee.ui.navigasi.DestinasiNavigasi

object DestinasiUpdate: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Mhs"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}