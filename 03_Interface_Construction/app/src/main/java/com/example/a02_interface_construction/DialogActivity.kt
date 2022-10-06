package com.example.a02_interface_construction

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DialogActivity: Activity() {

    private lateinit var dialogButton: Button
    private lateinit var previousPageButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        dialogButton = findViewById(R.id.DialogButton)
        dialogButton = findViewById(R.id.DialogButton)
        dialogButton.setOnClickListener {
            showDialog("Sum")
        }
        previousPageButton = findViewById(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@DialogActivity, AnimationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog)
        dialog.show()
        val answer = dialog.findViewById(R.id.Answer) as TextView
        val cancel = dialog.findViewById(R.id.CancelButton) as Button
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        val confirm = dialog.findViewById(R.id.ConfirmButton) as Button
        confirm.setOnClickListener {
            val firstNumberEditText = dialog.findViewById(R.id.FirstNumber) as EditText
            val secondNumberEditText = dialog.findViewById(R.id.SecondNumber) as EditText
            val firstNumberText = firstNumberEditText.text
            val secondNumberText = secondNumberEditText.text
            if (firstNumberText.isNotEmpty() && secondNumberText.isNotEmpty()) {
                answer.setText((firstNumberText.toString().toDouble() + secondNumberText.toString().toDouble()).toString())
            }
        }
    }
}