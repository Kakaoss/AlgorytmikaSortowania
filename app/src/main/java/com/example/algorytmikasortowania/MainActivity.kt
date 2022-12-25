package com.example.algorytmikasortowania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random
import kotlin.system.measureTimeMillis

// Bubble sort
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

fun MutableList<Int>.quickSort() {
    quickSort(0, size - 1)
}
fun MutableList<Int>.quickSort(low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = partition(low, high)
        quickSort(low, pivotIndex - 1)
        quickSort(pivotIndex + 1, high)
    }
}
fun MutableList<Int>.partition(low: Int, high: Int): Int {
    val pivot = this[high]
    var i = low - 1
    for (j in low until high) {
        if (this[j] <= pivot) {
            i++
            val temp = this[i]
            this[i] = this[j]
            this[j] = temp
        }
    }
    val temp = this[i + 1]
    this[i + 1] = this[high]
    this[high] = temp

    return i + 1
}

fun MutableList<Int>.insertSort() {
    for (i in 1 until size) {
        val current = this[i]
        var j = i
        while (j > 0 && this[j - 1] > current) {
            this[j] = this[j - 1]
            j--
        }
        this[j] = current
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

            //QuickSort Time
            val quickSortTime = measureTimeMillis {
                for (n in 1..iloscRazy.toString().toInt())
                    lista.quickSort()
            }
            czasQuick.text = "${quickSortTime}ms"
            //InsertSort Time
            val insertSortTime = measureTimeMillis {
                for (n in 1..iloscRazy.toString().toInt())
                    lista.insertSort()
            }
            czasInsert.text = "${insertSortTime}ms"
        }
    }
}