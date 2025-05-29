package com.example.yemeksiparis

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparis.adapter.YemekAdapter
import com.example.yemeksiparis.databinding.ActivityMainBinding
import com.example.yemeksiparis.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: YemekAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()
        observeViewModel()
        setupClickListeners()

        viewModel.tumYemekleriGetir()
    }

    private fun setupRecyclerView() {
        adapter = YemekAdapter(emptyList()) { yemek ->
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("yemek_id", yemek.yemek_id)
                putExtra("yemek_adi", yemek.yemek_adi)
                putExtra("yemek_resim_adi", yemek.yemek_resim_adi)
                putExtra("yemek_fiyat", yemek.yemek_fiyat)
            }
            startActivity(intent)
        }

        binding.rvYemekler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun observeViewModel() {
        viewModel.yemekler.observe(this) { yemekler ->
            adapter = YemekAdapter(yemekler) { yemek ->
                val intent = Intent(this, DetailsActivity::class.java).apply {
                    putExtra("yemek_id", yemek.yemek_id)
                    putExtra("yemek_adi", yemek.yemek_adi)
                    putExtra("yemek_resim_adi", yemek.yemek_resim_adi)
                    putExtra("yemek_fiyat", yemek.yemek_fiyat)
                }
                startActivity(intent)
            }
            binding.rvYemekler.adapter = adapter
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupClickListeners() {
        binding.fabSepet.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
} 