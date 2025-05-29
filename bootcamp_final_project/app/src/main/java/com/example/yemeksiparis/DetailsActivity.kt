package com.example.yemeksiparis

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.yemeksiparis.databinding.ActivityDetailsBinding
import com.example.yemeksiparis.repository.YemekRepository

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var adet = 1
    private lateinit var yemekAdi: String
    private lateinit var yemekResimAdi: String
    private var yemekFiyat = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val yemekId = intent.getIntExtra("yemek_id", 0)
        yemekAdi = intent.getStringExtra("yemek_adi") ?: ""
        yemekResimAdi = intent.getStringExtra("yemek_resim_adi") ?: ""
        yemekFiyat = intent.getIntExtra("yemek_fiyat", 0)

        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        binding.apply {
            tvYemekAdiDetay.text = yemekAdi
            tvYemekFiyatDetay.text = "$yemekFiyat ₺"
            tvAdet.text = adet.toString()

            Glide.with(this@DetailsActivity)
                .load("http://kasimadalan.pe.hu/yemekler/resimler/$yemekResimAdi")
                .into(ivYemekDetay)
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            btnArttir.setOnClickListener {
                adet++
                tvAdet.text = adet.toString()
            }

            btnAzalt.setOnClickListener {
                if (adet > 1) {
                    adet--
                    tvAdet.text = adet.toString()
                }
            }

            btnSepeteEkle.setOnClickListener {
                val repository = YemekRepository()
                Thread {
                    val success = repository.sepeteYemekEkle(
                        yemekAdi,
                        yemekResimAdi,
                        yemekFiyat,
                        adet,
                        "kadir_efe"
                    )

                    runOnUiThread {
                        if (success) {
                            Toast.makeText(
                                this@DetailsActivity,
                                "Yemek sepete eklendi",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(
                                this@DetailsActivity,
                                "Yemek sepete eklenirken bir hata oluştu",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }.start()
            }
        }
    }
} 