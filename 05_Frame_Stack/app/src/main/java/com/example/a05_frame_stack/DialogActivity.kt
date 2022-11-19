package com.example.a05_frame_stack

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DialogActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        val dialogButton = findViewById<Button>(R.id.ShowDialog)
        dialogButton.setOnClickListener {
            showDialog("Message")
        }

        val previousPageButton = findViewById<Button>(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@DialogActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val nextPageButton = findViewById<Button>(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@DialogActivity, DateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog)
        dialog.getWindow()?.setLayout(1000, 500)
        dialog.show()
        val confirm = dialog.findViewById(R.id.ConfirmButton) as Button
        confirm.setOnClickListener {
            val message = dialog.findViewById(R.id.Message) as EditText

            val userMessage = this.findViewById(R.id.UserMessage) as TextView
            val str = "Your Message:\n" + message.text
            userMessage.text = str
            dialog.dismiss()
        }
    }
}