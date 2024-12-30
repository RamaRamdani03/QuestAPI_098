package com.example.roomdatabasee.dependenciesinjection

import com.example.roomdatabasee.repository.mahasiswaRepository

interface AppContainer{
    val kontakRepository: mahasiswaRepository
}