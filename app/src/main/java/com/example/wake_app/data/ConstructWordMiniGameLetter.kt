package com.example.wake_app.data

data class ConstructWordMiniGameLetter(
    var letter: String,
    var selected: Boolean = false,
    var wordIndex: Int = -1,
    var selectIndex: Int = -1
) {

}
