package com.example.basketballscore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.ArrayList

private const val SCORE_LIST = "SCORE_LIST"

class MainActivity : AppCompatActivity() {
    
    private var scores: ArrayList<Score> = arrayListOf(Score(0,0))
    private lateinit var teamATextView: TextView
    private lateinit var teamBTextView: TextView
    
    private fun updateScore(scoreA: Int, scoreB : Int){
        val last = scores.last()
        val score = Score(last.a + scoreA, last.b+ scoreB)
        scores.add(score)
        showLatestScore()
    }
    
    private fun showLatestScore(){
        val last = scores.last()
        teamATextView.text = last.a.toString()
        teamBTextView.text = last.b.toString()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.let{
            @Suppress("DEPRECATION")
            it.getParcelableArrayList<Score>(SCORE_LIST)?.let{savedScoresList->
                scores= savedScoresList
            }
        }
        teamATextView = findViewById(R.id.teamAScoreTextView)
        teamBTextView = findViewById(R.id.teamBScoreTextView)
        showLatestScore()

        //Team A add 3
        val incrementTeamA3Button = findViewById<Button>(R.id.teamA3Button)
        incrementTeamA3Button.setOnClickListener {
            updateScore(3,0)
        }

        //Team B add 3
        val incrementTeamB3Button = findViewById<Button>(R.id.teamB3Button)
        incrementTeamB3Button.setOnClickListener {
            updateScore(0,3)
        }

        //Team A add 2
        val incrementTeamA2Button = findViewById<Button>(R.id.teamA2Button)
        incrementTeamA2Button.setOnClickListener {
            updateScore(2,0)

        }

        //Team B add 2
        val incrementTeamB2Button = findViewById<Button>(R.id.teamB2Button)
        incrementTeamB2Button.setOnClickListener {
            updateScore(0,2)

        }

        //Team A add 1
        val incrementTeamA1Button = findViewById<Button>(R.id.teamA1Button)
        incrementTeamA1Button.setOnClickListener {
            updateScore(1,0)

        }

        //Team B add 1
        val incrementTeamB1Button = findViewById<Button>(R.id.teamB1Button)
        incrementTeamB1Button.setOnClickListener {
            updateScore(0,1)

        }

        //RESET Button Implementation
        val resetButton = findViewById<Button>(R.id.reset)
        resetButton.setOnClickListener {
            scores = arrayListOf(Score(0,0))
            showLatestScore()
        }

        //UNDO Button Implementation
        val undoButton = findViewById<Button>(R.id.undo)
        undoButton.setOnClickListener {
            if(scores.size>1) {
                scores.removeLast()
            }
            showLatestScore()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SCORE_LIST,scores)
    }
}