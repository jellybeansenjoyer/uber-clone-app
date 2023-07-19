package com.example.ubercloneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Arrays

const val LOGIN_REQUEST_CODE = 7171;
class SplashScreenActivity : AppCompatActivity() {
    lateinit private var providers:List<AuthUI.IdpConfig>
    lateinit private var firebaseAuth:FirebaseAuth
    lateinit private var listener:FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initial()
    }

    private fun initial() {
        providers = Arrays.asList(AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build())
        firebaseAuth = FirebaseAuth.getInstance()
        listener = firebaseAuth.let{
            val user = firebaseAuth.currentUser
            if(user!=null){
                delaySplashScreen()
            }else{
                showLoginLayout()
            }
        }
    }

    private fun showLoginLayout() {
        val authMethodPickerLayout = AuthMethodPickerLayout.Builder(R.layout.signin).setPhoneButtonId(R.id.phone_btn).setGoogleButtonId(R.id.google_btn).build()
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAuthMethodPickerLayout(authMethodPickerLayout).setIsSmartLockEnabled(false).setAvailableProviders(providers).build(),
            LOGIN_REQUEST_CODE)
    }

    fun delaySplashScreen(){
        lifecycleScope.launch {
            delay(5000)
            Toast.makeText(this@SplashScreenActivity, "Splash Screen Done!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val idpResponse = IdpResponse.fromResultIntent(data)
        if(requestCode==RESULT_OK){
            val user = FirebaseAuth.getInstance().currentUser
        }else
            Toast.makeText(this@SplashScreenActivity, "Failed to sign in:${idpResponse?.error?.message}", Toast.LENGTH_SHORT).show()
    }
}