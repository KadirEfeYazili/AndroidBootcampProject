package com.example.yemeksiparis.repository

import com.example.yemeksiparis.api.RetrofitClient
import com.example.yemeksiparis.model.Yemek
import com.example.yemeksiparis.model.SepetYemek

class YemekRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun tumYemekleriGetir(): List<Yemek>? {
        val response = apiService.tumYemekleriGetir()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun sepettekiYemekleriGetir(): List<SepetYemek>? {
        val response = apiService.sepettekiYemekleriGetir()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun sepeteYemekEkle(
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: Int,
        yemekSiparisAdet: Int,
        kullaniciAdi: String
    ): Boolean {
        val response = apiService.sepeteYemekEkle(
            yemekAdi,
            yemekResimAdi,
            yemekFiyat,
            yemekSiparisAdet,
            kullaniciAdi
        )
        return response.isSuccessful
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String): Boolean {
        val response = apiService.sepettenYemekSil(sepetYemekId, kullaniciAdi)
        return response.isSuccessful
    }
} 