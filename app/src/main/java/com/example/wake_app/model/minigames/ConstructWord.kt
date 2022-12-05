package com.example.wake_app.model.minigames

import androidx.compose.runtime.mutableStateListOf
import com.example.wake_app.data.CommonWords
import com.example.wake_app.data.ConstructWordMiniGameLetter

class ConstructWord {
    val title = "Construct words using given letters"

    private var _word: String = "";
    val word: String?
        get() = _word

    var _selects: MutableList<Boolean> = mutableListOf()
    val selects: List<Boolean> = _selects

    var letters = mutableStateListOf<ConstructWordMiniGameLetter>()

    init {
        this._word = generateWord();

        for (c in (0 until longestWord().length)) {
            _selects.add(c,false)
        }

        for (i in (_word.indices)) {
            letters.add(ConstructWordMiniGameLetter(_word[i].toString()))
        }

    }

    fun updateLetter(index: Int){
        letters[index] = letters[index].copy(selected = !letters[index].selected)
    }

    fun setSelect(index: Int,value: Boolean) {
        System.out.println("Printing _selects:")

        for (s in _selects) {
            System.out.println(s)
        }
        _selects[index] = value
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
        return this._word
    }

    fun disableAll() {
        for (i in 0 until letters.size) {
            letters[i] = letters[i].copy(selected = false)
        }
    }

    fun generateWord(): String {
        disableAll()
        return CommonWords.words.random();
    }

    fun newWord() {
        disableAll()
        this._word = generateWord()
    }
}