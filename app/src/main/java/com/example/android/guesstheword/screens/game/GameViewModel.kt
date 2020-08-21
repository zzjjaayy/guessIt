package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * ViewModel is used to maintain a layer of separation between UI and data display/update and other minor calculations.
 */
class GameViewModel() : ViewModel(){
    init {
        Log.i("GameViewModel","viewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel","viewModel destroyed")
    }
    // onCleared is called when the activity/fragment is completely destroyed
    // this means that viewModel is retained if the activity/fragment is recreated due to configuration changes like rotation.
}