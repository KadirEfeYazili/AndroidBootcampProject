package com.example.yemeksiparis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparis.databinding.ItemSepetBinding
import com.example.yemeksiparis.model.SepetYemek

class SepetAdapter(
    private val sepetYemekler: List<SepetYemek>,
    private val onDeleteClick: (SepetYemek) -> Unit
) : RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {

    inner class SepetViewHolder(private val binding: ItemSepetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sepetYemek: SepetYemek) {
            binding.apply {
                tvSepetYemekAdi.text = sepetYemek.yemek_adi
                tvSepetYemekAdet.text = "Adet: ${sepetYemek.yemek_siparis_adet}"
                tvSepetYemekFiyat.text = "Toplam: ${sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet} ₺"

                Glide.with(ivSepetYemek)
                    .load("http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}")
                    .into(ivSepetYemek)

                btnSil.setOnClickListener {
                    onDeleteClick(sepetYemek)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val binding = ItemSepetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SepetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        holder.bind(sepetYemekler[position])
    }

    override fun getItemCount() = sepetYemekler.size
} 