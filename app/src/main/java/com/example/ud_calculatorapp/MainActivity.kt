package com.example.ud_calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastNumeric = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){ //view is the button that called this method
        tvInput?.append((view as Button).text)
        lastNumeric = true
    }

    fun onOperator(view: View){
        tvInput?.text?.let {  //is the tvInput and text are not empty then this let will give us the CharSequence as 'it' variable
            if (!isOperatorAdded(it.toString()))
                tvInput?.append((view as Button).text)
                lastNumeric = false
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            var operation = ""

            try{

                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1) //get rid of the first value in the string ex Hello world -> ello world
                }

                if(tvValue.contains("-")){
                    operation = "-"
                }
                else if(tvValue.contains("+")){
                    operation = "+"
                }
                else if(tvValue.contains("*")){
                    operation = "*"
                }
                else{
                    operation = "/"
                }

                var splitValue: List<String>? = null

                when(operation){
                    "+" -> splitValue = tvValue.split("+")
                    "-" -> splitValue = tvValue.split("-")
                    "*" -> splitValue = tvValue.split("*")
                    "/" -> splitValue = tvValue.split("/")
                }


                var first = splitValue?.get(0)
                var second = splitValue?.get(1)

                if(prefix.isNotEmpty()){
                    first = prefix + first
                }

                when(operation){
                    "+" -> tvInput?.text = (first!!.toDouble() + second!!.toDouble()).toString()
                    "-" -> tvInput?.text = (first!!.toDouble() - second!!.toDouble()).toString()
                    "*" -> tvInput?.text = (first!!.toDouble() * second!!.toDouble()).toString()
                    "/" -> tvInput?.text = (first!!.toDouble() / second!!.toDouble()).toString()
                }

            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onClear(view: View){
        tvInput?.text = ""
        lastNumeric = false
    }

    fun onDecimalPoint(view: View){
        if(tvInput?.text?.contains(".") == false){
            tvInput?.append(".")
            lastNumeric = false
        }
    }

    private fun isOperatorAdded(value:String): Boolean{
        return if(value.startsWith("-")){
            false
        }else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }



}