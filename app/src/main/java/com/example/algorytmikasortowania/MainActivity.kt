package com.example.algorytmikasortowania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun MutableList<Int>.bubbleSort() {
    for (i in 0 until size - 1) {
        for (j in 0 until size - i - 1) {
            if (this[j] > this[j + 1]) {
                val temp = this[j]
                this[j] = this[j + 1]
                this[j + 1] = temp
            }
        }
    }
}

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
                lista.add(Random.nextInt())
            }

            // Bubble sort Time
            val bubbleSortTime = measureTimeMillis {
                for (n in 1..iloscRazy.toString().toInt())
                lista.bubbleSort()
            }
            czasBubble.text = "${bubbleSortTime}ms"
        }
    }
}