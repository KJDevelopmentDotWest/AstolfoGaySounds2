package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.kjdevelopmentdotwest.astolfogaysounds2.R


class SignInActivity : AppCompatActivity() {

    private lateinit var signInButton: SignInButton
    private lateinit var startImage: ImageView
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var account: GoogleSignInAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton = findViewById(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_WIDE)
        startImage = findViewById(R.id.startImage)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        account = GoogleSignIn.getLastSignedInAccount(this)

        if (account != null){
            //startActivity(Intent(this, MainActivity::class.java))
        }

        signInButton.setOnClickListener{
            signIn()
        }
    }

    private fun signIn(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        account = GoogleSignIn.getLastSignedInAccount(this)
        if (requestCode == 1 && account != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}