package com.example.wake_app.model.minigames

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.example.wake_app.data.CommonWords
import com.example.wake_app.data.ConstructWordMiniGameLetter

class ConstructWord {
    val title = "Construct a word using given letters"

    var result = mutableStateOf("")
    var word = mutableStateOf("")
    var letters = mutableStateListOf<ConstructWordMiniGameLetter>()

    init {
        newWord()
    }

    /**
     * Adds letter to the result string and handles setting of
     * ConstructWordMiniGameLetter variables
     */
    fun handleUpdate(l: ConstructWordMiniGameLetter) {
        if (l.selectIndex == -1) {  //not in solution => append
            result.value += l.letter
            l.selectIndex = result.value.length-1
        } else { //letter already in solution => remove from solution and correct indices
            result.value = StringBuilder(result.value).deleteCharAt(l.selectIndex).toString();
            correctIndices(l.selectIndex)
            l.selectIndex = -1
        }

        updateLetter(letters.indexOf(l)) //negates select flag of updated letter
    }

    /**
     * Corrects indices of ConstructWordMiniGameLetter list,
     * indices of letter before removed index remain unchanged,
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
        for (l in letters) {
            if (l.selectIndex > i) {
                l.selectIndex--
            }
        }
    }

    /**
     * Negates selec flag of letter at given index
     */
    fun updateLetter(index: Int){
        letters[index] = letters[index].copy(selected = !letters[index].selected)
    }

    fun longestWord() : String {
        var maxLen = -1;
        var lWord = "";
        for (w in CommonWords.words) {
            if (w.length > maxLen) {
                maxLen = w.length
                lWord = w
            };
        }
        return lWord;
    }

    override fun toString(): String {
        return this.word.value
    }

    /**
     * Generates random word from data list
     */
    fun generateWord(): String {
        return CommonWords.words.random()
    }

    /**
     * Handles generation of new word
     * Updates letter list to change ui
     */
    fun newWord() {
        this.word.value = generateWord()

        letters.removeAll(letters)

        for (i in (word.value.indices)) {
            letters.add(ConstructWordMiniGameLetter(word.value[i].toString(),false,i))
        }
        letters = letters.shuffled().toMutableStateList()
        result.value = ""
    }

    /**
     * Returns true if constructed letter is correct
     */
    fun isCorrect() : Boolean {
        return word.value == result.value
    }
}