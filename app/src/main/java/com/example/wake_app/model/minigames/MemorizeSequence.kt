package com.example.wake_app.model.minigames

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.example.wake_app.data.MemorizeSymbol
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.random.nextInt

class MemorizeSequence(val length: Int, val showDelay: Long) {
    private val _SYMBOL_LEN = 5
    private val _SUSPEND_TIME = 10 //seconds

    val title = "Memorize and repeat given sequence"

    var firstRepeat = mutableStateOf(true)

    var seq = mutableStateListOf<Int>()
    var symbols = mutableStateListOf<MemorizeSymbol>()
    var symbolsDisabled = mutableStateOf(true)
    var seqSolution = mutableListOf<Int>()

    var showAgain = mutableStateOf(true)
    var showAgainTimerStr = mutableStateOf("")

    init {
        newSequence()
    }

    fun generateSequence(): List<Int> {
        var tmp = mutableListOf<Int>()
        for (i in 0..length) {
            var randInt = Random.nextInt(0 until _SYMBOL_LEN)
            tmp.add(randInt)
        } //adds length number of random indices of symbol list

        return tmp
    }

    fun listToStr(list: MutableList<Int>): String {
        var seqStr = ""
        for (i in list) {
            seqStr += "$i "
        }
        return seqStr
    }

    fun newSequence() {
        this.seq = generateSequence().toMutableStateList()

        symbols.removeAll(symbols)
        for (i in 0 until _SYMBOL_LEN) {
            symbols.add(
                MemorizeSymbol(
                    selected = false,
                    highlighted = false,
                    disabled = false
                )
            )
        }
    }

    fun addToSolution(index: Int) {
        seqSolution.add(index)
        if (seqSolution.size != seq.size) return

        if (seqSolution == seq) {
            System.out.println("CORRECT")
        } else {
            System.out.println("inCORRECT")
            seqSolution.clear()
        }
    }


    suspend fun showSequence() {
        symbolsDisabled.value = false
        for (i in seq) {
            updateSymbol(i)
            delay(showDelay)
            updateSymbol(i)
            delay(showDelay)
        }
        symbolsDisabled.value = true
    }

    suspend fun showBtnTimer() {
        showAgainTimerStr.value = _SUSPEND_TIME.toString()
        for (i in _SUSPEND_TIME downTo 0) {
            showAgainTimerStr.value = i.toString()
            delay(1000) //delay 1s
        }
        showAgainTimerStr.value = ""
    }

    fun updateSymbol(index: Int){
        symbols[index] = symbols[index].copy(selected = !symbols[index].selected)
    }


}