package com.example.roomdatabasee

import android.app.Application
import com.example.roomdatabasee.dependenciesinjection.AppContainer
import com.example.roomdatabasee.dependenciesinjection.MahasiswaContainer

class MahasiswaApplications : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}