package com.afit.lottosim

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import kotlinx.android.synthetic.main.number_picker.*
import org.w3c.dom.Text

class ChooseOwnNumbers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.number_picker)

        pickNumbers()
    }

/////////////////////////////////////////
    //NUMBER PICKER SCREEN
    /////////////////////////////////////////

    fun pickNumbers(){

        var ballsArray = arrayListOf<Int>()

        val numberBoxArray = arrayOf(
            ON1,
            ON2,
            ON3,
            ON4,
            ON5,
            ON6,
            ON7,
            ON8,
            ON9,
            ON10,
            ON11,
            ON12,
            ON13,
            ON14,
            ON15,
            ON16,
            ON17,
            ON18,
            ON19,
            ON20,
            ON21,
            ON22,
            ON23,
            ON24,
            ON25,
            ON26,
            ON27,
            ON28,
            ON29,
            ON30,
            ON31,
            ON32,
            ON33,
            ON34,
            ON35,
            ON36,
            ON37,
            ON38,
            ON39,
            ON40,
            ON41,
            ON42,
            ON43,
            ON44,
            ON45,
            ON46,
            ON47,
            ON48,
            ON49)

        for (i in 1..49){
            ballsArray.add(i)
            println("added " + i +" to balls array")
        }

        for (i in 0..ballsArray.count()-1){
            val textViewToUpdate = numberBoxArray.get(i)
            textViewToUpdate.setText(ballsArray.get(i).toString())
            textViewToUpdate.setTextColor(Color.parseColor("#F96E46"))
            textViewToUpdate.setTextSize(30.toFloat())

            textViewToUpdate.setOnClickListener{
                numberSelected((textViewToUpdate.text).toString(), textViewToUpdate)
            }
        }

    }

    fun numberSelected(whichNumber : String, theTextView : TextView){

        val theNumber = whichNumber.toInt()

        println("The number selected was "+ theNumber)

        theTextView.setTextColor(Color.parseColor("#FFFFFF"))

    }

}