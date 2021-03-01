package com.kjdevelopmentdotwest.astolfogaysounds2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.Background
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.CasualPostureSkirt
import java.io.*


class MainActivity : AppCompatActivity() {

    companion object{
        var clickCount : Long = 0
        var moneyCount : Long = 0
        val casualPostureSkirts = arrayListOf<CasualPostureSkirt>()
        val backgrounds = arrayListOf<Background>()
    }

    private lateinit var mainImage: ImageView
    private lateinit var mainImageBackground: ImageView
    private lateinit var shopButton: Button
    private lateinit var achievementsButton: Button
    private lateinit var clickCountTextView: TextView
    private lateinit var moneyCountTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions() //check for permissions
        setUpViews() //initialize views
        setUpImageFactory() //initialize necessary variables for ImageFactory class
        setUpUserData() // retrieve user info from storage
        googleAccountCheck() //check is user signed in google account
    }

    private fun checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_MEDIA_LOCATION) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }

    private fun setUpViews(){
        mainImage = findViewById(R.id.mainImage)
        mainImageBackground = findViewById(R.id.mainImageBackground)
        shopButton = findViewById(R.id.shopButton)
        achievementsButton = findViewById(R.id.achievementsButton)
        clickCountTextView = findViewById(R.id.clickCountTextView)
        moneyCountTextView = findViewById(R.id.moneyCountTextView)

        mainImageBackground.setOnClickListener {
            clickCount++
            clickCountTextView.text = clickCount.toString()
            moneyCount++
            moneyCountTextView.text = moneyCount.toString()
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
        retrieveAll()
        clickCountTextView.text = clickCount.toString()
        moneyCountTextView.text = moneyCount.toString()
    }

    private fun googleAccountCheck(){
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null){
            //startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        mainImage.setImageBitmap(ImageFactory.resultImage)
        mainImageBackground.setImageBitmap(ImageFactory.resultBackground)
    }

    override fun onResume() {
        super.onResume()
        mainImage.setImageBitmap(ImageFactory.resultImage)
        mainImageBackground.setImageBitmap(ImageFactory.resultBackground)
    }

    override fun onPause() {
        super.onPause()
        saveAll()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveAll()
    }

    private fun saveAll(){
        saveClickMoneyData()
        saveCasualPostureData()
        saveBackgroundData()
    }

    private fun retrieveAll(){
        retrieveClickMoneyData()
        retrieveCasualPostureData()
        retrieveBackgroundData()
    }

    private fun saveClickMoneyData(){
        val sharedPreferences = getSharedPreferences("clickMoneyData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong("clicks", clickCount)
        editor.putLong("money", moneyCount)
        editor.apply()
    }

    private fun retrieveClickMoneyData(){
        val sharedPreferences = getSharedPreferences("clickMoneyData", MODE_PRIVATE)
        clickCount = sharedPreferences.getLong("clicks", 0)
        moneyCount = sharedPreferences.getLong("money", 0)
    }

    private fun saveBackgroundData(){
        val sharedPreferences = getSharedPreferences("backgroundData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("blackBackground", backgrounds[0].status)
        editor.putInt("greenBackground", backgrounds[1].status)
        editor.apply()
    }

    private fun retrieveBackgroundData(){
        val sharedPreferences = getSharedPreferences("backgroundData", MODE_PRIVATE)
        backgrounds.add(Background(BitmapFactory.decodeResource(resources, R.drawable.background_black), sharedPreferences.getInt("blackBackground", 2)))
        backgrounds.add(Background(BitmapFactory.decodeResource(resources, R.drawable.background_green), sharedPreferences.getInt("greenBackground", 0)))
        backgrounds.forEach {
            if (it.status == 2){
                it.draw()
                return@forEach
            }
        }
    }

    private fun saveCasualPostureData(){
        val sharedPreferences = getSharedPreferences("casualPostureData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("blackSkirt", casualPostureSkirts[0].status)
        editor.putInt("greenSkirt", casualPostureSkirts[1].status)
        editor.apply()

    }

    private fun retrieveCasualPostureData(){
        val sharedPreferences = getSharedPreferences("casualPostureData", MODE_PRIVATE)
        casualPostureSkirts.add(CasualPostureSkirt(BitmapFactory.decodeResource(resources, R.drawable.casual_skirt_black), sharedPreferences.getInt("blackSkirt", 0)))
        casualPostureSkirts.add(CasualPostureSkirt(BitmapFactory.decodeResource(resources, R.drawable.casual_skirt_green), sharedPreferences.getInt("greenSkirt", 0)))
        casualPostureSkirts.forEach {
            if (it.status == 2){
                it.draw()
                return@forEach
            }
        }
    }
}