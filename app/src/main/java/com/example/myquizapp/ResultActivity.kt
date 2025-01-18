package com.example.myquizapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvScore: TextView = findViewById(R.id.tv_score)
        val tvMessage: TextView = findViewById(R.id.tv_message)
        val ivTrophy: ImageView = findViewById(R.id.iv_trophy)
        val btnFinish: Button = findViewById(R.id.btn_finish)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val correctAns = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val isWinner = intent.getBooleanExtra(Constants.IS_WINNER, false)

        tvName.text = userName
        tvScore.text = "Ваш результат $correctAns очков!"
        tvMessage.text = if (isWinner) "Поздравляем! Вы выиграли игру!" else "Ты проиграл! Повезет в следующий раз."

        if (isWinner) {
            ivTrophy.setImageResource(R.drawable.ic_trophy) // Победный трофей
            playSound(R.raw.victory_sound)
        } else {
            ivTrophy.setImageResource(R.drawable.ic_game_over) // Грустная иконка
            playSound(R.raw.defeat_sound)
        }

        btnFinish.setOnClickListener {
            mediaPlayer?.stop() // Останавливаем музыку при завершении
            mediaPlayer?.release()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun playSound(soundResId: Int) {
        mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release() // Освобождаем ресурсы MediaPlayer
        mediaPlayer = null
    }
}