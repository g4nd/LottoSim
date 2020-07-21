package com.afit.lottosim

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    var biggestWin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //////////////////////////////////
        //STARTING GAME ON MAIN MENU//////
        //////////////////////////////////

        setContentView(R.layout.main_menu)

        val startGameButton = findViewById<Button>(R.id.startGameBtn)

        startGameButton.setOnClickListener{
            showGameView()
        }


    }

    fun showGameView(){

        setContentView(R.layout.activity_main)

        val newNumberBtn = findViewById<Button>(R.id.newNumberBtn)

        newNumberBtn.setOnClickListener{

            draw()

        }
    }

    fun draw(){
        

        val numberTxtView = findViewById<TextView>(R.id.aNumberTxt)
        val numberTxtView2 = findViewById<TextView>(R.id.aNumberTxt2)
        val numberTxtView3 = findViewById<TextView>(R.id.aNumberTxt3)
        val numberTxtView4 = findViewById<TextView>(R.id.aNumberTxt4)
        val numberTxtView5 = findViewById<TextView>(R.id.aNumberTxt5)
        val numberTxtView6 = findViewById<TextView>(R.id.aNumberTxt6)

        //println("created textViews")

        val txtViewsResults = arrayOf(numberTxtView, numberTxtView2, numberTxtView3, numberTxtView4, numberTxtView5,
            numberTxtView6)

        var yourNumbers = getNumber()
        var drawNumbers = getNumber()

        yourNumbers.sort()
        drawNumbers.sort()

        println("your numbers are ")
        yourNumbers.forEach(::println)
        println("computer numbers are ")
        drawNumbers.forEach(::println)

        var matchesInDraw = 0


        for (i in 0..yourNumbers.count()-1){



            val txtViewToUpdate = txtViewsResults.get(i)
            txtViewToUpdate.setTextColor(Color.parseColor("#000000"))
            txtViewToUpdate.setTextSize(30.toFloat())

            if (drawNumbers.contains(yourNumbers.get(i))){
                println("YOU HAVE A MATCH ON " + yourNumbers.get(i))
                txtViewToUpdate.setTextColor(Color.parseColor("#008000"))
                txtViewToUpdate.setTextSize(40.toFloat())
                matchesInDraw += 1
            }else{
                println("NO MATCH ON " + yourNumbers.get(i))
            }



            txtViewToUpdate.setText(yourNumbers.get(i).toString())

        }
        println("You matched on " + matchesInDraw + " numbers")
        if (matchesInDraw > biggestWin){
            biggestWin = matchesInDraw
        }
        println("Your biggest win is " + biggestWin + " matched numbers")

    }

    fun getNumber(): ArrayList<Int> {



        //println("added views to array")

        var ballsArray = arrayListOf<Int>()

        var outputArray = arrayListOf<Int>()

        //fill the machine
        for (i in 1..49){
            ballsArray.add(i)
            //println("added " + i +" to balls array")
        }

        //Draw the numbers
        var resultsArray = arrayListOf<Int>()

        //draw 6 numbers
        for (i in 0..5){
            /*println("preshuffle check")
            ballsArray.sort()
            ballsArray.forEach(::println)*/


            ballsArray.shuffle()

            /*println("Shuffled balls")
            println("")
            println("")
            println("")
            println("")
            println("This is how the machine looks now ")
            ballsArray.forEach(::println)
            println("")
            println("")
            println("")
            println("")
            println("")*/

            val numberOfBallsLeft = ballsArray.count()

            //println("there are " + numberOfBallsLeft + " balls left in the machine")

            //Did a minus one here as the randomball was always one ahead. This is because if there are 44 objects
            //in the array, the randomizer here could choose object 44 and because we are doing from 0 to 44,
            //obj 44 would be position 45 in the array, which wouldnt exist and rthe app crashes. also
            //it caused the app to always choose the next position up which isnt good for logging
            //so taking the balls array count down by one evens it out.  for some reason starting the
            //randomizer at 1 to balls count didnt work either

            val selectARandomBall = (0..numberOfBallsLeft-1).random()

            //println("I've selected the ball at position " + selectARandomBall)

            val thisNumber = ballsArray.get(selectARandomBall)

            ballsArray.remove(thisNumber)

            //println("removing number " + thisNumber + " from the machine")

            //println("there are now " + ballsArray.count() + " left")

            outputArray.add(thisNumber)



        }

        outputArray.sort()



        return outputArray
    }
}