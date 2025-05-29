package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparis.model.Yemek
import com.example.yemeksiparis.repository.YemekRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = YemekRepository()
    private val _yemekler = MutableLiveData<List<Yemek>>()
    val yemekler: LiveData<List<Yemek>> = _yemekler

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun tumYemekleriGetir() {
        viewModelScope.launch {
            try {
                val yemekler = repository.tumYemekleriGetir()
                if (yemekler != null) {
                    _yemekler.value = yemekler
                } else {
                    _error.value = "Yemekler yüklenirken bir hata oluştu"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
} 