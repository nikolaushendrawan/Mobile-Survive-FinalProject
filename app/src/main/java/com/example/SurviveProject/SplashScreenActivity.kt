package com.example.SurviveProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    lateinit var mauth : FirebaseAuth
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mauth = FirebaseAuth.getInstance()
        handler = Handler()
        handler.postDelayed({
                if(mauth.currentUser != null){
                    /* val svc = Intent(this, BackgroundMusic::class.java)
                     svc.setAction("PLAY")
                     startService(svc)*/
                    val intent = Intent (this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }else{
                    /*val svc = Intent(this, BackgroundMusic::class.java)
                    svc.setAction("PLAY")
                    startService(svc)*/
                    /*val intent = Intent(requireActivity(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)*/
                    //finish()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
        },3000)// 3 detik delay
    }
}