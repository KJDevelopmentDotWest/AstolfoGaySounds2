package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.*
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture.SchoolPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.UserData

class MainActivity : AppCompatActivity() {

    private lateinit var mainImage: ImageView
    private lateinit var mainImageBackground: ImageView
    private lateinit var shopButton: Button
    private lateinit var achievementsButton: Button
    private lateinit var clickCountTextView: TextView
    private lateinit var moneyCountTextView: TextView
    private lateinit var loadingGifImageView: ImageView
    private lateinit var muteButton: ImageView
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews() //initialize views
        checkPermissions() //check for permissions
        setUpImageFactory() //initialize necessary variables for ImageFactory class
        setUpUserData() // retrieve user info from storage and draw saved image
        //changeMainImageMargin()
        googleAccountCheck()
    }

    private fun checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_MEDIA_LOCATION) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 1)
        }
    }

    private fun setUpViews(){
        loadingGifImageView = findViewById(R.id.loadingGif)
        loadingGifImageView.setImageResource(R.drawable.loading_gif)
        mainImage = findViewById(R.id.mainImage)
        mainImageBackground = findViewById(R.id.mainImageBackground)
        shopButton = findViewById(R.id.shopButton)
        achievementsButton = findViewById(R.id.achievementsButton)
        clickCountTextView = findViewById(R.id.clickCountTextView)
        moneyCountTextView = findViewById(R.id.moneyCountTextView)
        muteButton = findViewById(R.id.muteButton)

        mediaPlayer = MediaPlayer.create(this, R.raw.gay_sound_1)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        muteButton.setOnClickListener {
            if (!UserData.isMuted){
                UserData.isMuted = true
                muteButton.setImageResource(R.drawable.ic_button_unmute)
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
            } else {
                UserData.isMuted = false
                muteButton.setImageResource(R.drawable.ic_button_mute)
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0)
            }
        }

        mainImageBackground.setOnClickListener {
            onImageClicked()
        }

        shopButton.setOnClickListener {
            startActivity(Intent(this, ShopActivity::class.java))
        }
    }

    private fun setUpImageFactory(){
        val displayMetrics = DisplayMetrics()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = this.display
            display?.getRealMetrics(displayMetrics)
        } else {
            @Suppress("DEPRECATION")
            val display = this.windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(displayMetrics)
        }
        ImageFactory.displayMetrics = displayMetrics
        ImageFactory.resources = resources
        ImageFactory.getRes()
    }

    private fun setUpUserData(){
        val retrieveAndDrawThread = RetrieveUserDataAndDrawImageThread()
        retrieveAndDrawThread.start()
    }

    private fun googleAccountCheck(){
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null){
            //startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun onImageClicked(){
        UserData.clickCount++
        clickCountTextView.text = UserData.clickCount.toString()
        UserData.moneyCount++
        UserData.moneyCount = 1000000 //to be removed
        moneyCountTextView.text = UserData.moneyCount.toString()

        if (!mediaPlayer.isPlaying){
            mediaPlayer = when(System.nanoTime().rem(13).toInt()){
                0 -> MediaPlayer.create(this, R.raw.gay_sound_1)
                1 -> MediaPlayer.create(this, R.raw.gay_sound_2)
                2 -> MediaPlayer.create(this, R.raw.gay_sound_3)
                3 -> MediaPlayer.create(this, R.raw.gay_sound_4)
                4 -> MediaPlayer.create(this, R.raw.gay_sound_5)
                5 -> MediaPlayer.create(this, R.raw.gay_sound_6)
                6 -> MediaPlayer.create(this, R.raw.gay_sound_7)
                7 -> MediaPlayer.create(this, R.raw.gay_sound_8)
                8 -> MediaPlayer.create(this, R.raw.gay_sound_9)
                9 -> MediaPlayer.create(this, R.raw.gay_sound_10)
                10 -> MediaPlayer.create(this, R.raw.gay_sound_11)
                11 -> MediaPlayer.create(this, R.raw.gay_sound_12)
                12 -> MediaPlayer.create(this, R.raw.gay_sound_13)
                else -> MediaPlayer.create(this, R.raw.gay_sound_1)
            }

            mediaPlayer.start()
        }

        if (!UserData.clickAchievement){
            if (UserData.clicksList.size >= 100){
                UserData.clicksList.removeAt(0)
                UserData.clicksList.add(System.nanoTime())
                if (UserData.clicksList.last().minus(UserData.clicksList.first()) <= 129000000000000L){
                    UserData.clickAchievement = true
                    Toast.makeText(this, "${UserData.clicksList.last().minus(UserData.clicksList.first())}", Toast.LENGTH_SHORT).show()
                }
            } else {
                UserData.clicksList.add(System.currentTimeMillis())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val drawThread = DrawImage()
        drawThread.start()
        clickCountTextView.text = UserData.clickCount.toString()
        moneyCountTextView.text = UserData.moneyCount.toString()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
        val saveThread = SaveUserDataThread()
        saveThread.priority = Thread.MAX_PRIORITY
        saveThread.start()
    }

    inner class DrawImage: Thread(){
        override fun run() {
            runOnUiThread {
                mainImage.setImageBitmap(ImageFactory.resultImage)
                mainImageBackground.setImageBitmap(ImageFactory.resultBackground)
            }
        }
    }

    inner class RetrieveUserDataAndDrawImageThread: Thread(){
        override fun run() {
            UserData.sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
            UserData.sharedPreferencesStatus = getSharedPreferences("status", MODE_PRIVATE)
            UserData.retrieveUserData()
            runOnUiThread {
                clickCountTextView.text = UserData.clickCount.toString()
                moneyCountTextView.text = UserData.moneyCount.toString()
                loadingGifImageView.setImageResource(android.R.color.transparent)
                if (!UserData.isMuted){
                    muteButton.setImageResource(R.drawable.ic_button_mute)
                } else {
                    muteButton.setImageResource(R.drawable.ic_button_unmute)
                }
                if (UserData.defaultPostureStatus == 2) DefaultPosture.draw()
                if (UserData.formalPostureStatus == 2) FormalPosture.draw()
                if (UserData.schoolPostureStatus == 2) SchoolPosture.draw()
                mainImage.setImageBitmap(ImageFactory.resultImage)
                mainImageBackground.setImageBitmap(ImageFactory.resultBackground)
            }
        }
    }

    inner class SaveUserDataThread: Thread(){
        override fun run() {
            if (UserData.backgrounds.isEmpty()){
                return
            }
            UserData.saveUserData()
        }
    }
}