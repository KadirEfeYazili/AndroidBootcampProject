package com.example.yemeksiparis

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparis.adapter.SepetAdapter
import com.example.yemeksiparis.databinding.ActivityCartBinding
import com.example.yemeksiparis.viewmodel.CartViewModel

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: SepetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        setupRecyclerView()
        observeViewModel()

        viewModel.sepettekiYemekleriGetir()
    }

    private fun setupRecyclerView() {
        adapter = SepetAdapter(emptyList()) { sepetYemek ->
            viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id, "kadir_efe")
        }

        binding.rvSepet.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = this@CartActivity.adapter
        }
    }

    private fun observeViewModel() {
        viewModel.sepetYemekler.observe(this) { sepetYemekler ->
            if (sepetYemekler.isNullOrEmpty()) {
                binding.tvSepetBos.visibility = View.VISIBLE
                binding.rvSepet.visibility = View.GONE
            } else {
                binding.tvSepetBos.visibility = View.GONE
                binding.rvSepet.visibility = View.VISIBLE
                adapter = SepetAdapter(sepetYemekler) { sepetYemek ->
                    viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id, "kadir_efe")
                }
                binding.rvSepet.adapter = adapter
            }
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.success.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Yemek sepetten silindi", Toast.LENGTH_SHORT).show()
            }
        }
    }
} 