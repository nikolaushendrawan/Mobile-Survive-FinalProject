package com.example.SurviveProject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.SurviveProject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebasedatabase: FirebaseDatabase
    private lateinit var databasereference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        firebasedatabase = FirebaseDatabase.getInstance()
        databasereference = FirebaseDatabase.getInstance().reference

        binding.register.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.login.setOnClickListener{
            userlogin()
        }

    }

    private fun userlogin() {
    val editemail = binding.email.text.toString().trim()
    val editpassword =binding.password.text.toString().trim()

        if (editemail.isEmpty()) {
            binding.email.error = "Email is required!"
            binding.email.requestFocus()
            return
        }
        if (editpassword.isEmpty()) {
            binding.password.error = "Password is required!"
            binding.password.requestFocus()
            return
        }

        if (editpassword.length < 6) {
            binding.password.error = "Minimal password 6 characters!"
            binding.password.requestFocus()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(editemail, editpassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.e("Berhasil login","Selamat datang")
                    val uid = task.result.user?.uid
                    if (uid != null) {
                        databasereference.child("Users").child(uid).child("usertype")
                            .addListenerForSingleValueEvent(object :
                                ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    val usertype = snapshot.getValue(Int::class.java)
                                    if (usertype==0) {
                                        val `in` = Intent(this@MainActivity, HomeActivity::class.java)
                                        startActivity(`in`)
                                    }
                                    if (usertype==1) {
                                        val pindahadmin = Intent(this@MainActivity, HomeAdminActivity::class.java)
                                        startActivity(pindahadmin)
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {


                                }
                            })
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal Login!", Toast.LENGTH_LONG).show()
                }
            }
    }
}
