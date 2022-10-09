package com.example.a04_interface_elements

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ListActivity: AppCompatActivity() {
    private lateinit var spinnerHeroPicker: Spinner
    private lateinit var pickHero: TextView
    private lateinit var switchPower: Switch
    private lateinit var switchPowerIndicator: TextView
    private lateinit var seekBar:SeekBar
    private lateinit var seekBarIndicator:TextView
    private lateinit var previousPageButton: Button
    private lateinit var nextPageButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val heros = resources.getStringArray(R.array.heros)

        // Spinner
        spinnerHeroPicker = findViewById(R.id.HeroPicker)
        if (spinnerHeroPicker != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, heros)
            spinnerHeroPicker.adapter = adapter
        }
        pickHero = findViewById(R.id.PickHero)
        spinnerHeroPicker.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pickHero.text = heros[position]
//                Toast.makeText(this@ListActivity, "Selected Item: " + heros[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        // Switch
        switchPower = findViewById(R.id.Switch)
        switchPowerIndicator = findViewById(R.id.SwitchIndicator)
        switchPower.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switchPowerIndicator.text = "Power On"
//                Toast.makeText(this@ListActivity, "Switch: Power On", Toast.LENGTH_SHORT).show()
            } else {
                switchPowerIndicator.text = "Power Off"
//                Toast.makeText(this@ListActivity, "Switch: Power Off", Toast.LENGTH_SHORT).show()
            }
        }

        //SeekBar
        seekBar = findViewById(R.id.SeekBar)
        seekBarIndicator =  findViewById(R.id.Solve)
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                seekBarIndicator.text = "Current SeekBar Value " + progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // called when tracking the seekbar is started
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // called when tracking the seekbar is stopped
            }
        })

        previousPageButton = findViewById(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@ListActivity, DateActivity::class.java)
            startActivity(intent)
        }
        nextPageButton = findViewById(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@ListActivity, ExerciseActivity::class.java)
            startActivity(intent)
        }
    }
}