package com.kjdevelopmentdotwest.astolfogaysounds2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


class MainActivity : AppCompatActivity() {

    private lateinit var mainImage: ImageView
    private lateinit var shopButton: Button
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
        clickCountTextView = findViewById(R.id.clickCountTextView)
        moneyCountTextView = findViewById(R.id.moneyCountTextView)

        mainImage.setOnClickListener {
            UserData.clickCount++
            clickCountTextView.text = UserData.clickCount.toString()
            UserData.moneyCount++
            moneyCountTextView.text = UserData.moneyCount.toString()
        }

        shopButton.setOnClickListener{
            ImageFactory.mergeScaleBitmaps(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.astolfo
                ), BitmapFactory.decodeResource(
                    resources,
                    R.drawable.astolfoface
                ), 100f, 50f
            )
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
    }

    private fun setUpImageFactory(){
        val displayMetrics = DisplayMetrics()
        val windowManager = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        ImageFactory.displayMetrics = displayMetrics
    }

    private fun setUpUserData(){
        UserData.context = this
        UserData.retrieveUserInfoFromFile()
        clickCountTextView.text = UserData.clickCount.toString()
        moneyCountTextView.text = UserData.moneyCount.toString()

    }

    private fun googleAccountCheck(){
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null){
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if (ImageFactory.resultImage != null){
            mainImage.setImageBitmap(ImageFactory.resultImage)
        } else {
            ImageFactory.scaleBitmap(BitmapFactory.decodeResource(resources, R.drawable.astolfo))
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
    }

    override fun onResume() {
        super.onResume()
        if (ImageFactory.resultImage != null){
            mainImage.setImageBitmap(ImageFactory.resultImage)
        } else {
            ImageFactory.scaleBitmap(BitmapFactory.decodeResource(resources, R.drawable.astolfo))
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
    }

    override fun onPause() {
        super.onPause()
        UserData.saveProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        UserData.saveProgress()
    }
}