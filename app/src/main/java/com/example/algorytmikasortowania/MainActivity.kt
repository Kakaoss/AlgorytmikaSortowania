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
// quick sort
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
//insert sort
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
//heap sort
fun MutableList<Int>.heapSort() {
    for (i in size / 2 - 1 downTo 0) {
        heapify(size, i)
    }
    for (i in size - 1 downTo 0) {
        val temp = this[0]
        this[0] = this[i]
        this[i] = temp
        heapify(i, 0)
    }
}

fun MutableList<Int>.heapify(size: Int, rootIndex: Int) {
    var largest = rootIndex
    val leftChild = 2 * rootIndex + 1
    val rightChild = 2 * rootIndex + 2

    if (leftChild < size && this[leftChild] > this[largest]) {
        largest = leftChild
    }
    if (rightChild < size && this[rightChild] > this[largest]) {
        largest = rightChild
    }
    if (largest != rootIndex) {
        val temp = this[rootIndex]
        this[rootIndex] = this[largest]
        this[largest] = temp

        heapify(size, largest)
    }
}

//merge sort
fun MutableList<Int>.mergeSort() {
    mergeSort(this, 0, size - 1)
}
fun mergeSort(list: MutableList<Int>, low: Int, high: Int) {
    if (low < high) {
        val mid = (low + high) / 2
        mergeSort(list, low, mid)
        mergeSort(list, mid + 1, high)
        merge(list, low, mid, high)
    }
}
fun merge(list: MutableList<Int>, low: Int, mid: Int, high: Int) {

    val n1 = mid - low + 1
    val n2 = high - mid
    val left = MutableList(n1) { 0 }
    val right = MutableList(n2) { 0 }
    for (i in 0 until n1) {
        left[i] = list[low + i]
    }
    for (j in 0 until n2) {
        right[j] = list[mid + 1 + j]
    }
    var i = 0
    var j = 0
    var k = low
    while (i < n1 && j < n2) {
        if (left[i] <= right[j]) {
            list[k] = left[i]
            i++
        } else {
            list[k] = right[j]
            j++
        }
        k++
    }
    while (i < n1) {
        list[k] = left[i]
        i++
        k++
    }
    while (j < n2) {
        list[k] = right[j]
        j++
        k++
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
            if (iloscElementow.isNotEmpty() && iloscRazy.isNotEmpty()) {
                if (iloscElementow.toString().toInt() >= 1)
                    for (i in 1..iloscElementow.toString().toInt()) {
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

                //HeapSort Time
                val heapSortTime = measureTimeMillis {
                    for (n in 1..iloscRazy.toString().toInt())
                        lista.heapSort()
                }
                czasHeap.text = "${heapSortTime}ms"

                //MergeSort Time
                val mergeSortTime = measureTimeMillis {
                    for (n in 1..iloscRazy.toString().toInt())
                        lista.mergeSort()
                }
                czasMerge.text = "${mergeSortTime}ms"
            }
        }
    }
}