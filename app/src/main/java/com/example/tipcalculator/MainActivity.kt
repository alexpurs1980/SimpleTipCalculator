package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    //this code to setup the MainActivity to use view binding

    lateinit var binding: ActivityMainBinding
    //lateinit var promise that your code will initialize the variable before using it

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This line initializes the binding object which you'll use to access Views in
        // the activity_main.xml layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {calculateTip()}

    }

    fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDouble()
        val selectedID = binding.tipOptions.checkedRadioButtonId
        // set the tip percentage
        val tipPercentage = when(selectedID) {
            R.id.option_twenty_percent -> 0.2
            R.id.option_fifteen_percent -> 0.15
            else -> 0.1
        }

        var tip = cost * tipPercentage
        //Assign the isChecked attribute of the round up switch to a variable called roundUp.
        val roundUp = binding.roundUpSwitch.isChecked
        // if true - round up tip
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        // set currency format for tips
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)


    }


}