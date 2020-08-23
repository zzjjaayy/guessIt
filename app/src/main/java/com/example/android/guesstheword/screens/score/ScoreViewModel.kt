package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore : Int) : ViewModel() {

    private val _scorePassed = MutableLiveData<Int>()
    val scorePassed : LiveData<Int>
        get() = _scorePassed

    private val _playAgain = MutableLiveData<Boolean>()
    val playAgain : LiveData<Boolean>
        get() = _playAgain

    init {
        _scorePassed.value = finalScore
        _playAgain.value = false
        // put in the value of the score to the LiveData
    }

    fun onPlayAgain() {
        _playAgain.value = true
    }

    fun onPlayAgainComplete() {
        _playAgain.value = false
    }
}