package com.afit.lottosim

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.game_type_choice.*
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    lateinit var mAdView : AdView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)


        firebaseAnalytics = Firebase.analytics

        MobileAds.initialize(
            this
        ) { initializationStatus: InitializationStatus? -> }
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)




        //////////////////////////////////
        //STARTING GAME ON MAIN MENU//////
        //////////////////////////////////



        val startGameButton = findViewById<Button>(R.id.startGameBtn)

        startGameButton.setOnClickListener{
            showGameChoiceView()


        }
    }

    fun showGameChoiceView() {

        setContentView(R.layout.game_type_choice)

        newLDEachBtn.setOnClickListener {
            val intent = Intent(this, LDEveryTime::class.java)

            startActivity(intent)
        }

        oneLDBtn.setOnClickListener{
                val intent = Intent(this, LDOneTime::class.java)

                startActivity(intent)

        }

        letMeChooseBtn.setOnClickListener{

            val intent = Intent(this, ChooseOwnNumbers::class.java)

            startActivity(intent)
        }

        }

    }


