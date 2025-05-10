package com.example.bootcamptasarimodevi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip


class MainActivity : AppCompatActivity() {

    private lateinit var ekran: TextView
    private var ifade = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ekran = findViewById(R.id.textViewsonuc)

        val tuslar = listOf(
            R.id.tus1 to "1",
            R.id.tus2 to "2",
            R.id.tus3 to "3",
            R.id.tus4 to "4",
            R.id.tus5 to "5",
            R.id.tus6 to "6",
            R.id.tus7 to "7",
            R.id.tus8 to "8",
            R.id.tus9 to "9",
            R.id.tustopla to "+" ,
            R.id.tuscikar to "-",
            R.id.tuscarp to "x",
            R.id.tusböl to "÷"
        )

        for ((id, deger) in tuslar) {
            findViewById<Button>(id).setOnClickListener {
                ifade += deger
                ekran.text = ifade
            }
        }

        val tusSil = findViewById<Button>(R.id.tussil)
        tusSil.setOnClickListener {
            if (ifade.isNotEmpty()) {
                ifade = ifade.dropLast(1)
                ekran.text = ifade
            }
        }

        val tusC = findViewById<Button>(R.id.tusc)
        tusC.setOnClickListener {
            ifade = ""
            ekran.text = ""
        }

        val tusSonuc = findViewById<Chip>(R.id.tusesit)
        tusSonuc.setOnClickListener {
            try {
                val sayilar = ifade.split("+").map { it.toInt() }
                val toplam = sayilar.sum()
                ekran.text = toplam.toString()
                ifade = toplam.toString()
            } catch (e: Exception) {
                ekran.text = "Hata"
            }
        }
    }
}
