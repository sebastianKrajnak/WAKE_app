package com.example.wake_app.data

data class SequenceNumber(
    var number: Int,
    var selected: Boolean = false,
    var seqIndex: Int = -1,
    var selectIndex: Int = -1
) {

}
