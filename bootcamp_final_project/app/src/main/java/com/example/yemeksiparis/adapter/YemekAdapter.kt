package com.example.yemeksiparis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparis.databinding.ItemYemekBinding
import com.example.yemeksiparis.model.Yemek

class YemekAdapter(
    private val yemekler: List<Yemek>,
    private val onItemClick: (Yemek) -> Unit
) : RecyclerView.Adapter<YemekAdapter.YemekViewHolder>() {

    inner class YemekViewHolder(private val binding: ItemYemekBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(yemek: Yemek) {
            binding.apply {
                tvYemekAdi.text = yemek.yemek_adi
                tvYemekFiyat.text = "${yemek.yemek_fiyat} ₺"

                Glide.with(ivYemek)
                    .load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}")
                    .into(ivYemek)

                root.setOnClickListener {
                    onItemClick(yemek)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val binding = ItemYemekBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return YemekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        holder.bind(yemekler[position])
    }

    override fun getItemCount() = yemekler.size
} 