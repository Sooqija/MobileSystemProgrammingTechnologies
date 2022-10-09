package com.example.a04_interface_elements

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

fun Int.toBoolean() = if (this == 1) true else false
fun Boolean.toInt() = if (this) 1 else 0


class ExerciseActivity: AppCompatActivity() {
    private lateinit var previousPageButton: Button


    fun Start() {
        val operation = arrayOf("+", "-", "*")
        val operations = Array(4) { i-> operation[(0..2).random()] }
        val isCorrect = Array(4) { i-> (0..1).random() }
        val firstNumbers = Array(4) {i -> (0..10).random()}
        val secondNumbers = Array(4) {i -> (0..10).random()}
        val equation1 = findViewById<TextView>(R.id.Equation1)
        val equation2 = findViewById<TextView>(R.id.Equation2)
        val equation3 = findViewById<TextView>(R.id.Equation3)
        val equation4 = findViewById<TextView>(R.id.Equation4)
        val switch1 = findViewById<Switch>(R.id.Switch1)
        val switch2 = findViewById<Switch>(R.id.Switch2)
        val switch3 = findViewById<Switch>(R.id.Switch3)
        val switch4 = findViewById<Switch>(R.id.Switch4)
        val equations = arrayOf<TextView>(equation1, equation2, equation3, equation4)
        val switches = arrayOf<Switch>(switch1, switch2, switch3, switch4)

        for (i in 0..3) {
            switches[i].isChecked = false
        }

        for (i in 0..3) {
            if (isCorrect[i] == 1) {
                when (operations[i]) {
                    "+" -> equations[i].text = firstNumbers[i].toString() + " + " + secondNumbers[i] + " = " + (firstNumbers[i] + secondNumbers[i]).toString()
                    "-" -> equations[i].text = firstNumbers[i].toString() + " - " + secondNumbers[i] + " = " + (firstNumbers[i] - secondNumbers[i]).toString()
                    "*" -> equations[i].text = firstNumbers[i].toString() + " * " + secondNumbers[i] + " = " + (firstNumbers[i] * secondNumbers[i]).toString()
                }
            }
            else {
                when (operations[i]) {
                    "+" -> {
                        var ans = 0
                        do { ans = (0..100).random() } while (ans == firstNumbers[i] + secondNumbers[i])
                        equations[i].text = firstNumbers[i].toString() + " + " + secondNumbers[i] + " = " + ans.toString()
                    }
                    "-" -> {
                        var ans = 0
                        do { ans = (0..100).random() } while (ans == firstNumbers[i] - secondNumbers[i])
                        equations[i].text = firstNumbers[i].toString() + " - " + secondNumbers[i] + " = " + ans.toString()
                    }
                    "*" -> {
                        var ans = 0
                        do { ans = (0..100).random() } while (ans == firstNumbers[i] * secondNumbers[i])
                        equations[i].text = firstNumbers[i].toString() + " * " + secondNumbers[i] + " = " + ans.toString()
                    }
                }
            }
        }

        val checkAnswers = findViewById<Button>(R.id.CheckAnswers)
        val solveAnswer = findViewById<TextView>(R.id.Answer)
        solveAnswer.text = ""
        var correct = 0

        for (i in 0..3) {
            correct += isCorrect[i]
        }

        checkAnswers.setOnClickListener {
            var correctSwitch = 0
            for (i in 0..3) {
                correctSwitch += switches[i].isChecked.toInt()
            }
            if (correct == correctSwitch &&
                correct == switches[0].isChecked.toInt() * isCorrect[0] +
                switches[1].isChecked.toInt() * isCorrect[1] +
                switches[2].isChecked.toInt() * isCorrect[2] +
                switches[3].isChecked.toInt() * isCorrect[3]) {

                solveAnswer.text = "Correct Answer ! :)"
                solveAnswer.setTextColor(Color.parseColor("#0ada52"))
            }
            else {
                solveAnswer.text = "Wrong Answer... :("
                solveAnswer.setTextColor(Color.parseColor("#f70707"))
            }
        }
        val refreshButton = findViewById<Button>(R.id.Refresh)
        refreshButton.setOnClickListener { Start() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        Start()

        previousPageButton = findViewById(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@ExerciseActivity, ListActivity::class.java)
            startActivity(intent)
        }
    }
}