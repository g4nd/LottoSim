package com.afit.lottosim

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import kotlinx.android.synthetic.main.activity_l_d_every_time.*
import kotlinx.android.synthetic.main.number_picker.*
import org.w3c.dom.Text

class ChooseOwnNumbers : AppCompatActivity() {

    var ownNumbers = ArrayList<Int>(6)

    var numberOfBalls = 49

    var biggestWin = 0
    var numberOfTimesPlayed = 0
    var freeGoes = 0
    var cashSpent = 0
    var showTwoWinAlert = 1

    var lottoprice = 2

    var threeballs = 30
    var fourballs = 140
    var fiveballs = 1750

    var matched0 = 0
    var matched1 = 0
    var matched2 = 0
    var matched3 = 0
    var matched4 = 0
    var matched5 = 0
    var matched6 = 0

    lateinit var PREFS : SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.number_picker)

        var numberBoxArray = arrayListOf<TextView>(
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
            ON49
        )


        PREFS = SharedPrefs(this)

        var times_opened = PREFS.getCount(PREFS.ONOpenCount)
        times_opened++
        PREFS.setCount(PREFS.ONOpenCount,times_opened)


        ownNumbers.clear()

        if (times_opened > 0){

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Welcome Back")
            builder.setMessage("Would you like to use the same numbers as last time?")

            builder.setPositiveButton("Yes Please") { dialog, which ->

                var numbersFromPrefs = arrayListOf(PREFS.getCount(PREFS.ON1),PREFS.getCount(PREFS.ON2),PREFS.getCount(PREFS.ON3),
                    PREFS.getCount(PREFS.ON4),PREFS.getCount(PREFS.ON5),PREFS.getCount(PREFS.ON6))

                numbersFromPrefs.forEach(::println)

                startGameOwnNumbers(numbersFromPrefs)

            }

            builder.setNegativeButton("No Thanks") { dialog, which ->

                pickNumbers(numberBoxArray)

            }

            builder.show()

        }

        numsLeftToChooseTxt.setText("Pick a number")


        pickNumbers(numberBoxArray)
    }

/////////////////////////////////////////
    //NUMBER PICKER SCREEN
    /////////////////////////////////////////

    fun pickNumbers(textViewArray: ArrayList<TextView>) {

        var ballsArray = arrayListOf<Int>()

        for (i in 1..49) {
            ballsArray.add(i)
            println("added " + i + " to balls array")
        }

        for (i in 0..ballsArray.count() - 1) {
            val textViewToUpdate = textViewArray.get(i)
            textViewToUpdate.setText(ballsArray.get(i).toString())
            textViewToUpdate.setTextColor(Color.parseColor("#F96E46"))
            textViewToUpdate.setTextSize(30.toFloat())

            textViewToUpdate.setOnClickListener {
                numberSelected((textViewToUpdate.text).toString(), textViewToUpdate, textViewArray)
            }
        }

    }

    fun numberSelected(
        whichNumber: String,
        theTextView: TextView,
        textViewArray: ArrayList<TextView>
    ) {

        val theNumber = whichNumber.toInt()

        println("The number selected was " + theNumber)

        theTextView.setTextColor(Color.parseColor("#FFFFFF"))
        theTextView.setOnClickListener{
            null
        }

        buildOwnNumbersArray(theNumber, textViewArray)

    }

    fun buildOwnNumbersArray(whichNumber: Int, textViewArray: ArrayList<TextView>) {

        ownNumbers.add(whichNumber)
        val numObjs = ownNumbers.count()
        println("own numbers array has " + numObjs.toString() + " values")
        numsLeftToChooseTxt.setText("Choose "  + (6 - numObjs) +  " more numbers")

        if (numObjs > 5) {

            for (i in 0..textViewArray.count() - 1) {
                val textViewToUpdate = textViewArray.get(i)
                textViewToUpdate.setTextColor(Color.parseColor("#000000"))

                textViewToUpdate.setOnClickListener {
                    null
                }


            }

            for (i in 1..6){
                when (i){
                    1 -> PREFS.setCount(PREFS.ON1,ownNumbers.get(0))
                    2 -> PREFS.setCount(PREFS.ON2,ownNumbers.get(1))
                    3 -> PREFS.setCount(PREFS.ON3,ownNumbers.get(2))
                    4 -> PREFS.setCount(PREFS.ON4,ownNumbers.get(3))
                    5 -> PREFS.setCount(PREFS.ON5,ownNumbers.get(4))
                    6 -> PREFS.setCount(PREFS.ON6,ownNumbers.get(5))
                }

            }
            startGameOwnNumbers(ownNumbers)

        }
    }

    fun startGameOwnNumbers(chosenNumbers:ArrayList<Int>) {
        setContentView(R.layout.activity_choose_own_numbers)

        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////
        //////////////////GAME START/////////////////////////
        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////

        lateinit var mAdView : AdView
        MobileAds.initialize(
            this
        ) { initializationStatus: InitializationStatus? -> }
        mAdView = findViewById(R.id.adViewCON)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        var yourNumbers = chosenNumbers

        val numberTxtView = findViewById<TextView>(R.id.yourNumberTxt)
        val numberTxtView2 = findViewById<TextView>(R.id.yourNumberTxt2)
        val numberTxtView3 = findViewById<TextView>(R.id.yourNumberTxt3)
        val numberTxtView4 = findViewById<TextView>(R.id.yourNumberTxt4)
        val numberTxtView5 = findViewById<TextView>(R.id.yourNumberTxt5)
        val numberTxtView6 = findViewById<TextView>(R.id.yourNumberTxt6)

        val txtViewsYour = arrayOf(numberTxtView, numberTxtView2, numberTxtView3, numberTxtView4, numberTxtView5,
            numberTxtView6)



        newNumberBtn.setOnClickListener {

            draw(yourNumbers, txtViewsYour)
        }

        backToMenuBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }


    }

    fun draw(yourNumbers : ArrayList<Int>, textViewsYour : Array<TextView>){

        /*firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, "Draw Pressed")
            param(FirebaseAnalytics.Param.ITEM_NAME, "New Draw")
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "Button")
        }*/

        numberOfTimesPlayed +=1
        cashSpent = lottoprice*(numberOfTimesPlayed-freeGoes)
        estCashSpent.setText("£" + cashSpent.toString() )

        val playedTxtView = findViewById<TextView>(R.id.timesPlayedTxtView)
        val biggestMatch = findViewById<TextView>(R.id.biggestMatchTxtView)

        playedTxtView.setText(numberOfTimesPlayed.toString())

        val lottoNumberTxtView = findViewById<TextView>(R.id.lottoNumberTxt)
        val lottoNumberTxtView2 = findViewById<TextView>(R.id.lottoNumberTxt2)
        val lottoNumberTxtView3 = findViewById<TextView>(R.id.lottoNumberTxt3)
        val lottoNumberTxtView4 = findViewById<TextView>(R.id.lottoNumberTxt4)
        val lottoNumberTxtView5 = findViewById<TextView>(R.id.lottoNumberTxt5)
        val lottoNumberTxtView6 = findViewById<TextView>(R.id.lottoNumberTxt6)

        val txtViewsLotto = arrayOf(lottoNumberTxtView, lottoNumberTxtView2, lottoNumberTxtView3, lottoNumberTxtView4, lottoNumberTxtView5,
            lottoNumberTxtView6)

        var drawNumbers = getNumber()

        yourNumbers.sort()
        drawNumbers.sort()

        println("your numbers are ")
        yourNumbers.forEach(::println)
        println("computer numbers are ")
        drawNumbers.forEach(::println)

        var matchesInDraw = 0


        for (i in 0..yourNumbers.count()-1){

            val txtViewToUpdate = textViewsYour.get(i)

            txtViewToUpdate.setText(yourNumbers.get(i).toString())

            val lottoNumberTxtViewToUpdate = txtViewsLotto.get(i)
            txtViewToUpdate.setTextColor(Color.parseColor("#F96E46"))
            lottoNumberTxtViewToUpdate.setTextColor(Color.parseColor("#F96E46"))
            txtViewToUpdate.setTextSize(30.toFloat())
            lottoNumberTxtViewToUpdate.setTextSize(30.toFloat())

            if (drawNumbers.contains(yourNumbers.get(i))){
                println("YOU HAVE A MATCH ON " + yourNumbers.get(i))
                txtViewToUpdate.setTextColor(Color.parseColor("#008000"))
                txtViewToUpdate.setTextSize(40.toFloat())
                matchesInDraw += 1
            }else{
                println("NO MATCH ON " + yourNumbers.get(i))
            }




            lottoNumberTxtViewToUpdate.setText(drawNumbers.get(i).toString())

        }
        println("You matched on " + matchesInDraw + " numbers")
        if (matchesInDraw > biggestWin){
            biggestWin = matchesInDraw
        }


        when (matchesInDraw){
            1 -> matched1 +=1
            2 -> matched2Handler()
            3 -> matched3Handler()
            4 -> matched4 +=1
            5 -> matched5 +=1
            6 -> matched6 +=1
            else -> {matched0 +=1
            }
        }

        var totalWinsCash = ((matched3 * threeballs) + (matched4 * fourballs) +
                (matched5 * fiveballs))


        estCashWon.setText("£" + totalWinsCash.toString())


        println("Your biggest win is " + biggestWin + " matched numbers")
        biggestMatch.setText(biggestWin.toString())

    }

    fun matched2Handler() {

        println("Entered Matched 2 Handler")
        println("Cash Spent is " + cashSpent)
        matched2 +=1
        freeGoes +=1
        println("You get a free go for matchign 2")
        println("Cash Spent is now " + cashSpent)
        estCashSpent.setText("£" + cashSpent.toString() )

        /*firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, "Start Game Button")
            param(FirebaseAnalytics.Param.ITEM_NAME, "Start Game")
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "Matches")
        }*/

        //showWinAlertView()
        if (showTwoWinAlert == 1) {
            winAlert(2, matched2)
        }else{}
    }

    fun winAlert(matchCount : Int, timesMatched : Int){

        var prizeWon = ""
        var positiveWord = ""

        when (matchCount) {
            2 -> prizeWon = "WON a Free Shot!"
            3 -> prizeWon = "WON £30"
            4 -> prizeWon = "WON £140"
            5 -> prizeWon = "WON £1750"
            6 -> prizeWon = "WON THE JACKPOT!"
        }

        when (matchCount) {
            2 -> positiveWord = "Great!"
            3 -> positiveWord = "Excellent"
            4 -> positiveWord = "AMAZING!"
            5 -> positiveWord = "HUGE WIN!"
            6 -> positiveWord = "YOU WON THE JACKPOT!"
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle(positiveWord + " You matched " + matchCount + " numbers!")
        builder.setMessage("You have matched "+ matchCount + " " + timesMatched.toString() + " times. " +"You " + prizeWon)

        builder.setPositiveButton("Keep Going!") { dialog, which ->
            /*Toast.makeText(applicationContext,
                positiveWords.get((0..positiveWords.count()-1).random()), Toast.LENGTH_SHORT).show()*/

        }

        builder.setNeutralButton("Tweet This") { dialog, which ->

            shareToTwitter(prizeWon)

            Toast.makeText(applicationContext,
                "Thank you for sharing!", Toast.LENGTH_SHORT).show()

        }

        if (matchCount == 2) {
            builder.setNegativeButton("Dont Show Again For 2 Numbers") { dialog, which ->

                showTwoWinAlert = 0

            }
        }

        builder.show()

    }

    fun shareToTwitter(thisWin : String){
        val url = "http://www.twitter.com/intent/tweet?text=I just " + thisWin +  " %23LottoSim.  Get it yourself on the Google Play Store! %40SimLotto"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)

        /*firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE) {
            param(FirebaseAnalytics.Param.ITEM_ID, "Share To Twitter")
            param(FirebaseAnalytics.Param.ITEM_NAME, "Twitter Share")
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "Win Share")
        }*/
    }

    fun matched3Handler() {

        println("Entered Matched 3 Handler")
        println("Cash Spent is " + cashSpent)
        matched3 +=1
        println("You win £30 for matchign 3")
        println("Cash Spent is now " + cashSpent)
        estCashSpent.setText("£" + cashSpent.toString() )

        /*firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, "Start Game Button")
            param(FirebaseAnalytics.Param.ITEM_NAME, "Start Game")
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "Matches")
        }*/

        //showWinAlertView()

        winAlert(3, matched3)

    }

    fun getNumber(): ArrayList<Int> {



        //println("added views to array")

        var ballsArray = arrayListOf<Int>()

        var outputArray = arrayListOf<Int>()

        //fill the machine
        for (i in 1..numberOfBalls){
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


