package com.example.algorytmikasortowania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lista: MutableList<Int> = mutableListOf()
        val iloscRazy = findViewById<EditText>(R.id.wprIloscPowt).text
        val iloscElementow = findViewById<EditText>(R.id.wprIloscElem).text
        val btnSortuj = findViewById<Button>(R.id.btnSortuj)
        val czasQuick = findViewById<TextView>(R.id.czasQuick)
        val czasBubble = findViewById<TextView>(R.id.czasBubble)
        val czasInsert = findViewById<TextView>(R.id.czasInsert)
        val czasHeap = findViewById<TextView>(R.id.czasHeap)
        val czasMerge = findViewById<TextView>(R.id.czasMerge)
        btnSortuj.setOnClickListener {

            lista.clear()

            for (i in 1..iloscElementow.toString().toInt()){
                lista.add(Random.nextInt(1,50))
            }
            czasQuick.text = lista.toString()
        }
    }
}