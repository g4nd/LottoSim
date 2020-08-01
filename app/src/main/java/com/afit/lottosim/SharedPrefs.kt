package com.afit.lottosim

import android.content.Context

class SharedPrefs(context: Context) {
    val PREFS_FILENAME = "com.afit.lottosim.prefs"
    val OPEN_COUNT = "OpenCount"
    val NUMBER_OF_TIMES_PLAYED = "TimesPlayed"

    //Own NUmbers
    val ONOpenCount = "ONOpenCount"
    val ON1 = "ON1"
    val ON2 = "ON2"
    val ON3 = "ON3"
    val ON4 = "ON4"
    val ON5 = "ON5"
    val ON6 = "ON6"





    //TO STORE DATA TODO
    val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)

    fun getCount(thePref:String) : Int{
        return prefs.getInt(thePref,0)
    }

    fun setCount(thePref:String,count:Int){
        val editor = prefs.edit()
        editor.putInt(thePref,count)
        editor.apply()

        println("setting "+ thePref +" to " + count)
    }
}