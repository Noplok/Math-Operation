package com.example.mobile_math_operation

import android.os.Bundle
import android.widget.*
import android.graphics.Color
import kotlin.random.Random
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvProblem: TextView
    private lateinit var etAnswer: EditText
    private lateinit var btnCheck: Button
    private lateinit var btnStart: Button
    private lateinit var tvStats: TextView

    private var correctAnswers = 0
    private var wrongAnswers = 0
    private var num1 = 0
    private var num2 = 0
    private var operator = ""
    private var result = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvProblem = findViewById(R.id.tvProblem)
        etAnswer = findViewById(R.id.etAnswer)
        btnCheck = findViewById(R.id.btnCheck)
        btnStart = findViewById(R.id.btnStart)
        tvStats = findViewById(R.id.tvStats)

        btnStart.setOnClickListener { startGame() }
        btnCheck.setOnClickListener { checkAnswer() }
    }

    private fun startGame() {
        num1 = Random.nextInt(10, 100)
        num2 = Random.nextInt(10, 100)
        operator = listOf("+", "-", "*", "/").random()

        result = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                while (num1 % num2 != 0) {
                    num1 = Random.nextInt(10, 100)
                    num2 = Random.nextInt(10, 100)
                }
                num1 / num2
            }
            else -> 0
        }

        tvProblem.text = "$num1 $operator $num2 ="
        tvProblem.setBackgroundColor(Color.WHITE)
        etAnswer.text.clear()
        etAnswer.isEnabled = true
        btnCheck.isEnabled = true
        btnStart.isEnabled = false
    }

    private fun checkAnswer() {
        val userAnswer = etAnswer.text.toString().toIntOrNull()

        if (userAnswer == result) {
            tvProblem.setBackgroundColor(Color.GREEN)
            correctAnswers++
        } else {
            tvProblem.setBackgroundColor(Color.RED)
            wrongAnswers++
        }

        val total = correctAnswers + wrongAnswers
        val percentage = if (total > 0) (correctAnswers * 100.0 / total) else 0.0
        tvStats.text = "Итог: Правильно: $correctAnswers, Неправильно: $wrongAnswers (%.2f%%)".format(percentage)

        etAnswer.isEnabled = false
        btnCheck.isEnabled = false
        btnStart.isEnabled = true
    }
}