package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel is used to maintain a layer of separation between UI and data display/update and other minor calculations.
 */
class GameViewModel() : ViewModel(){

    // The current word
    private val _word = MutableLiveData<String>()
    val word : LiveData<String>
    get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score
    // LiveData cannot be edited and just be viewed
    // earlier the value of score and word could be changed by any class
    // so we used a concept called encapsulation which means that we can control who can view and who can edit the values
    // we made the score and word private and changed their names for use in this class only
    // and ade a new public variable which cannot edit the variable but pass in its value to other classes
    // this is done by a kotlin concept called backing field which lets you make changes to the default getters and setters
    // now in this case the getter is getting the value of score and word and updating to other classes

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
        _score.value = 0
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //gameFinished()
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
        // we changed the syntax because score is now a liveData object and not an Integer
        // so we need to get its value and make null check, because its null in the starting
        // then we call the minus function because operators are giving compile errors
        // SAME FOR onCorrect()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }
}