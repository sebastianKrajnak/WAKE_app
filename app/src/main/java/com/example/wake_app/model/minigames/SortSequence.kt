package com.example.wake_app.model.minigames

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.example.wake_app.data.SequenceNumber
import kotlin.random.Random
import kotlin.random.nextInt

class SortSequence (val minVal : Int, val maxVal : Int, val range : Int) {
    val title = "Sort given sequence"

    var result = mutableStateOf("")
    var seq = mutableStateListOf<Int>()
    var numbers = mutableStateListOf<SequenceNumber>()

    init {
        newSequence()
    }

    /**
     * Adds letter to the result string and handles setting of
     * ConstructWordMiniGameLetter variables
     */
    fun handleUpdate(l: SequenceNumber) {
        if (l.selectIndex == -1) {  //not in solution => append
            //result.value += l.number.toString() + " "
            if (result.value.length == 0) l.selectIndex = 0
            else l.selectIndex = result.value.length-1
        } else { //letter already in solution => remove from solution and correct indices
            //result.value = StringBuilder(result.value).deleteCharAt(l.selectIndex).toString();
            correctIndices(l.selectIndex)
            l.selectIndex = -1
        }
        updateNumber(numbers.indexOf(l)) //negates select flag of updated letter

        result.value = ""

        var tmp = numbers.toMutableList()
        tmp.sortBy { it.selectIndex }
        for (n in tmp) {
            if (n.selected && n.selectIndex != -1) result.value += "${n.number} "
        }
    }

    /**
     * Corrects indices of numbers list,
     * indices of numbers before removed index remain unchanged,
     * letters after removed index are decremented
     * Removed index
     *     |
     * 0 1 2 3
     * A B C D
     *
     * =
     *
     * 0 1 2
     * A B D
     */
    fun correctIndices(i : Int) {
        for (n in numbers) {
            if (n.selectIndex > i) {
                n.selectIndex--
            }
        }
    }

    /**
     * Negates select flag of number at given index
     */
    fun updateNumber(index: Int){
        numbers[index] = numbers[index].copy(selected = !numbers[index].selected)
    }

    override fun toString(): String {
        return this.seq.toString()
    }

    /**
     * Handles generation of new sequence
     * Updates seq list to change ui
     */
    fun newSequence() {
        val randomInts = generateSequence { Random.nextInt(minVal..maxVal) }
            .distinct()
            .take(range)
            .sorted()
            .toSet()

        seq.removeAll(seq)
        for (i in randomInts) {
            seq.add(i)
        }

        numbers.removeAll(numbers)
        for (i in seq) {
            numbers.add(SequenceNumber(i,false,i))
        }
        numbers = numbers.shuffled().toMutableStateList()
        result.value = ""
    }

    fun resultToList() : List<Int> {
        var splt = result.value.split(" ")
        splt = splt.dropLast(1) //removes whitespace
        return splt.toList().map { it.toInt() }
    }

    /**
     * Returns true if constructed letter is correct
     */
    fun isCorrect() : Boolean {
        if (result.value.isEmpty()) return false
        var rList = resultToList()
        return rList == seq
    }
}