package com.example.yemeksiparis.api

import com.example.yemeksiparis.model.Yemek
import com.example.yemeksiparis.model.SepetYemek
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemekApiService {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun tumYemekleriGetir(): Response<List<Yemek>>

    @GET("yemekler/sepettekiYemekleriGetir.php")
    suspend fun sepettekiYemekleriGetir(): Response<List<SepetYemek>>

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") yemekResimAdi: String,
        @Field("yemek_fiyat") yemekFiyat: Int,
        @Field("yemek_siparis_adet") yemekSiparisAdet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<SepetYemek>

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepetYemekId: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<SepetYemek>
} 