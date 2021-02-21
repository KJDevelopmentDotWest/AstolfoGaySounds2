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
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.CasualPostureSkirt
import java.io.*


class MainActivity : AppCompatActivity() {

    companion object{
        var clickCount : Long = 0
        var moneyCount : Long = 0
        val casualPostureSkirts = arrayListOf<CasualPostureSkirt>()
    }

    private lateinit var mainImage: ImageView
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
        setUpUserData() // initialize necessary variables for UserData class and retrieve user info from storage
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
        shopButton = findViewById(R.id.shopButton)
        achievementsButton = findViewById(R.id.achievementsButton)
        clickCountTextView = findViewById(R.id.clickCountTextView)
        moneyCountTextView = findViewById(R.id.moneyCountTextView)

        mainImage.setOnClickListener {
            clickCount++
            clickCountTextView.text = clickCount.toString()
            moneyCount++
            moneyCountTextView.text = moneyCount.toString()
        }

        shopButton.setOnClickListener {
            startActivity(Intent(this, ShopActivity::class.java))
        }

        achievementsButton.setOnClickListener{
            ImageFactory.mergeScaleBitmaps(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.casual_astolfo
                ), BitmapFactory.decodeResource(
                    resources,
                    R.drawable.astolfoface
                )
            )
            mainImage.setImageBitmap(ImageFactory.resultImage)
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
        if (ImageFactory.resultImage != null){
            mainImage.setImageBitmap(ImageFactory.resultImage)
        } else {
            ImageFactory.scaleBitmap(BitmapFactory.decodeResource(resources, R.drawable.casual_astolfo))
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
    }

    override fun onResume() {
        super.onResume()
        if (ImageFactory.resultImage != null){
            mainImage.setImageBitmap(ImageFactory.resultImage)
        } else {
            ImageFactory.scaleBitmap(BitmapFactory.decodeResource(resources, R.drawable.casual_astolfo))
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
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
    }

    private fun retrieveAll(){
        retrieveClickMoneyData()
        retrieveCasualPostureData()
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