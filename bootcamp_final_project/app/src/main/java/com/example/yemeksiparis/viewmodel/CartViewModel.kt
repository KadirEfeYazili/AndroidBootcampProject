package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparis.model.SepetYemek
import com.example.yemeksiparis.repository.YemekRepository
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val repository = YemekRepository()
    private val _sepetYemekler = MutableLiveData<List<SepetYemek>>()
    val sepetYemekler: LiveData<List<SepetYemek>> = _sepetYemekler

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    fun sepettekiYemekleriGetir() {
        viewModelScope.launch {
            try {
                val yemekler = repository.sepettekiYemekleriGetir()
                if (yemekler != null) {
                    _sepetYemekler.value = yemekler
                } else {
                    _error.value = "Sepetteki yemekler yüklenirken bir hata oluştu"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                val success = repository.sepettenYemekSil(sepetYemekId, kullaniciAdi)
                _success.value = success
                if (success) {
                    sepettekiYemekleriGetir()
                } else {
                    _error.value = "Yemek silinirken bir hata oluştu"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
} 