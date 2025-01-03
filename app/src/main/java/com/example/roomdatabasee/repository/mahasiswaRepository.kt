package com.example.roomdatabasee.repository

import com.example.roomdatabasee.model.Mahasiswa
import com.example.roomdatabasee.service_api.MahasiswaService
import okio.IOException

interface mahasiswaRepository {
    suspend fun getMahasiswa(): List<Mahasiswa>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim:String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswaById(nim: String): Mahasiswa
}

class NetworkMahasiswaRepository(
    private val  MahasiswaApiService: MahasiswaService
) : mahasiswaRepository {
    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        MahasiswaApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        MahasiswaApiService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = MahasiswaApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete mahasiswa. HTTP status code: " +
                "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMahasiswa(): List<Mahasiswa> = MahasiswaApiService.getMahasiswa()
    override suspend fun getMahasiswaById(nim: String): Mahasiswa {
        return MahasiswaApiService.getMahasiswaById(nim)
    }
}