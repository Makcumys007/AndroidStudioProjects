package com.example.firstcoposeproject.lesson1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

suspend fun main() {
    val number = listOf (
        3,4,8,16,5,45,67,897,3432,332,45,67,89,9,12,34,56,87
        ).asFlow()
      //  .asSequence()
    number.filter { it.isPrime()}
        .filter { it > 20 }
        .map{"Number: $it"}
        .collect {println(it)}
        //.forEach { println(it) }

}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in  2.. this / 2 ) {
        delay(50)
        if(this % i == 0) return false
    }
    return true
}