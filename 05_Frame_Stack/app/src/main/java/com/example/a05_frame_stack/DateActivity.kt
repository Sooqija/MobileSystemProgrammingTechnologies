package com.example.a05_frame_stack

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class DateActivity: AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    private lateinit var labelDate: TextView
    private lateinit var labelTime: TextView
    private lateinit var selectDateButton: Button
    private lateinit var selectTimeButton: Button
    private lateinit var nextPageButton: Button
    private lateinit var previousPageButton: Button
    private var savedHour = 0
    private var savedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        labelDate = findViewById(R.id.SelectedDate)
        labelTime = findViewById(R.id.selectedTime)
        selectDateButton = findViewById(R.id.SelectDateButton)
        selectTimeButton = findViewById(R.id.SelectTimeButton)

        // Initialization
        val c = Calendar.getInstance()
        var myFormat = SimpleDateFormat("dd MMMM yyyy")
        c.get(Calendar.YEAR)
        c.get(Calendar.MONTH)
        c.get(Calendar.DAY_OF_MONTH)
        labelDate.text = myFormat.format(c.time)
        savedHour = c.get(Calendar.HOUR)
        savedMinute = c.get(Calendar.MINUTE)
        labelTime.text = "$savedHour:$savedMinute"



        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            var myFormat = SimpleDateFormat("dd MMMM yyyy")
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, monthOfYear)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            labelDate.text = myFormat.format(c.time)
        }

        selectDateButton.setOnClickListener {
            DatePickerDialog(this@DateActivity, dateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }
        selectTimeButton.setOnClickListener {
            TimePickerDialog(this@DateActivity, this@DateActivity, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true).show()
        }

        nextPageButton = findViewById(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@DateActivity, ListActivity::class.java)
            startActivity(intent)
        }
        previousPageButton = findViewById(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@DateActivity, DialogActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
        labelTime.text = "$savedHour:$savedMinute"
    }
}