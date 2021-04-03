package com.kjdevelopmentdotwest.astolfogaysounds2

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.*


class MainActivity : AppCompatActivity() {

    companion object{
        var clickCount : Long = 0
        var moneyCount : Long = 0

        val casualPostureSkirts = arrayListOf<CasualPostureSkirt>()

        val formalPostureBlazers = arrayListOf<FormalPostureBlazer>()
        val formalPosturePants = arrayListOf<FormalPosturePants>()

        val backgrounds = arrayListOf<Background>()
    }

    private lateinit var mainImage: ImageView
    private lateinit var mainImageBackground: ImageView
    private lateinit var shopButton: Button
    private lateinit var achievementsButton: Button
    private lateinit var clickCountTextView: TextView
    private lateinit var moneyCountTextView: TextView
    private lateinit var loadingGifImageView: ImageView
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews() //initialize views
        checkPermissions() //check for permissions
        setUpImageFactory() //initialize necessary variables for ImageFactory class
        setUpUserData() // retrieve user info from storage and draw saved image
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
        //Glide.with(this).load(R.drawable.loading_gif).into(loadingGifImageView)
        mainImage = findViewById(R.id.mainImage)
        mainImageBackground = findViewById(R.id.mainImageBackground)
        shopButton = findViewById(R.id.shopButton)
        achievementsButton = findViewById(R.id.achievementsButton)
        clickCountTextView = findViewById(R.id.clickCountTextView)
        moneyCountTextView = findViewById(R.id.moneyCountTextView)

        mediaPlayer = MediaPlayer.create(this, R.raw.gay_sound_1)

        mainImageBackground.setOnClickListener {
            onImageClicked()
        }

        shopButton.setOnClickListener {
            startActivity(Intent(this, ShopActivity::class.java))
        }
    }

    private fun setUpImageFactory(){

        if (ImageFactory.displayMetrics == null){
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
        }
    }

    private fun setUpUserData(){
        val retrieveAndDrawThread = RetrieveUserDataAndDrawImageThread()
        retrieveAndDrawThread.start()
    }

    private fun onImageClicked(){
        clickCount++
        clickCountTextView.text = clickCount.toString()
        moneyCount++
        moneyCountTextView.text = moneyCount.toString()

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
    }

    private fun googleAccountCheck(){
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null){
            //startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val drawThread = DrawImage()
        drawThread.start()
    }

    override fun onStop() {
        super.onStop()
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
            val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
            retrieveClickMoneyData(sharedPreferences)
            retrieveCasualPostureData(sharedPreferences)
            retrieveFormalPostureData(sharedPreferences)
            retrieveBackgroundData(sharedPreferences)
            runOnUiThread {
                clickCountTextView.text = clickCount.toString()
                moneyCountTextView.text = moneyCount.toString()
                mainImage.setImageBitmap(ImageFactory.resultImage)
                mainImageBackground.setImageBitmap(ImageFactory.resultBackground)
                loadingGifImageView.setImageResource(android.R.color.transparent)
                //Glide.with(this@MainActivity).load(android.R.color.transparent).into(loadingGifImageView)
            }
        }

        private fun retrieveClickMoneyData(sharedPreferences: SharedPreferences){
            clickCount = sharedPreferences.getLong("clicks", 0)
            moneyCount = sharedPreferences.getLong("money", 0)
        }

        private fun retrieveBackgroundData(sharedPreferences: SharedPreferences){
            backgrounds.add(Background(BitmapFactory.decodeResource(resources, R.drawable.background_black), sharedPreferences.getInt("blackBackground", 2)))
            backgrounds.add(Background(BitmapFactory.decodeResource(resources, R.drawable.background_green), sharedPreferences.getInt("greenBackground", 0)))
            backgrounds.forEach {
                if (it.status == 2){
                    it.draw()
                    return@forEach
                }
            }
        }

        private fun retrieveCasualPostureData(sharedPreferences: SharedPreferences){
            casualPostureSkirts.add(CasualPostureSkirt(BitmapFactory.decodeResource(resources, R.drawable.casual_skirt_black), sharedPreferences.getInt("blackSkirt", 2)))
            casualPostureSkirts.add(CasualPostureSkirt(BitmapFactory.decodeResource(resources, R.drawable.casual_skirt_green), sharedPreferences.getInt("greenSkirt", 0)))
            casualPostureSkirts.forEach {
                if (it.status == 2){
                    it.draw()
                    return@forEach
                }
            }
        }

        private fun retrieveFormalPostureData(sharedPreferences: SharedPreferences){
            FormalPosture.status = sharedPreferences.getInt("formalPosture", 0)
            formalPostureBlazers.add(FormalPostureBlazer(BitmapFactory.decodeResource(resources, R.drawable.formal_blazer_red), sharedPreferences.getInt("formalBlazerRed", 0)))
            formalPostureBlazers.forEach {
                if (it.status == 2){
                    it.draw()
                    return@forEach
                }
            }

            formalPosturePants.add(FormalPosturePants(BitmapFactory.decodeResource(resources, R.drawable.formal_pants_black), sharedPreferences.getInt("formalPantsBlack", 0)))
            formalPosturePants.add(FormalPosturePants(BitmapFactory.decodeResource(resources, R.drawable.formal_pants_green), sharedPreferences.getInt("formalPantsGreen", 0)))
            formalPosturePants.forEach {
                if (it.status == 2){
                    it.draw()
                    return@forEach
                }
            }
        }
    }

    inner class SaveUserDataThread: Thread(){
        override fun run(){
            if (backgrounds.isEmpty()){
                return
            }
            val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            saveClickMoneyData(editor)
            saveCasualPostureData(editor)
            saveFormalPostureData(editor)
            saveBackgroundData(editor)
            editor.apply()
        }

        private fun saveClickMoneyData(editor: SharedPreferences.Editor){
            editor.putLong("clicks", clickCount)
            editor.putLong("money", moneyCount)
        }

        private fun saveBackgroundData(editor: SharedPreferences.Editor){
            editor.putInt("blackBackground", backgrounds[0].status)
            editor.putInt("greenBackground", backgrounds[1].status)
        }

        private fun saveCasualPostureData(editor: SharedPreferences.Editor){
            editor.putInt("blackSkirt", casualPostureSkirts[0].status)
            editor.putInt("greenSkirt", casualPostureSkirts[1].status)
        }

        private fun saveFormalPostureData(editor: SharedPreferences.Editor){
            editor.putInt("formalBlazerRed", formalPostureBlazers[0].status)

            editor.putInt("formalPantsBlack", formalPosturePants[0].status)
            editor.putInt("formalPantsGreen", formalPosturePants[1].status)

            editor.putInt("formalPosture", FormalPosture.status)
        }
    }
}