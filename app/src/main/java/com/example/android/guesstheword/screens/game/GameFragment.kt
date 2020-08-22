/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import kotlinx.android.synthetic.main.title_fragment.view.*

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    //viewModel
    private lateinit var viewModel: GameViewModel

    //Moved the variables to the viewModel class because those facilitate the logic of the fragment

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //calling view model provider
        // don't use ViewModelProviders {deprecated}
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        // Moved the nextWord() and resetList() to the viewModel so that it gets executed only once
        // i.e in the starting of the game, so that it isn't affected by configuration changes

        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
            // we don't need to call updateScoreText and updateWordText because the observe method will be called every time  the score changes
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })
        // passed in the lifecycle of the views in the activity{that is the fragment}
        // this method will be called every time the value of score changes

        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
        })

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { hasFinished ->
            if(hasFinished == true)
            {gameFinished()}
        })

        return binding.root

    }

    //Moved the resetList() and nextWord() to viewModel due to them being strictly logic based

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
//        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?:0 )
//        // this is an elvis operator which says that if the value is null put in 0
//        findNavController(this).navigate(action)
        Toast.makeText(this.activity,"game finished",Toast.LENGTH_SHORT).show()
    } // referenced the score variable from viewModel

}
