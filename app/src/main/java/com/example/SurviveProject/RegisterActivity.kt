package com.example.SurviveProject

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.SurviveProject.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registeruser.setOnClickListener{
            register()
        }

    }

    private fun register() {
        val editusername = binding.username.text.toString().trim()
        val editemail = binding.email.text.toString().trim()
        val editpassword = binding.password.text.toString().trim()
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        if (editusername.isEmpty()) {
            binding.username.error = "Username is required!"
            binding.username.requestFocus()
            return
        }

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
            binding.password.error = "Minimal Password 6 characters!"
            binding.password.requestFocus()
            return
        }



        firebaseAuth.createUserWithEmailAndPassword(editemail, editpassword)
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    var avatarinvent: ArrayList<Int> = ArrayList()
                    var achievementinvent: ArrayList<Int> = ArrayList()
                    var leveluser: ArrayList<String> = ArrayList()
                    var usermodule: ArrayList<String> = ArrayList()
                    leveluser.add("0")
                    avatarinvent.add(1)
                    usermodule.add("1")
                    achievementinvent.add(6)
                    val user = User(editusername, editemail, 0, 0, 0 , 1, avatarinvent, achievementinvent, leveluser, usermodule, 0, 0,0,0,0,0 )
                    database.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                        .set(user).addOnSuccessListener { if (task.isSuccessful) {
//                            Toast.makeText(this, "User has been registered sucessfully!", Toast.LENGTH_LONG).show()
//                            val kelogin = Intent(this, MainActivity::class.java)
//                            startActivity(kelogin)
//                            //redirect ke login
//                        } else Toast.makeText(this, "Failed to Register :(", Toast.LENGTH_LONG).show() }
                    FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user).addOnSuccessListener {
                            if (task.isSuccessful) {
                                Toast.makeText(this, "User has been registered sucessfully!", Toast.LENGTH_LONG).show()
                                val kelogin = Intent(this, MainActivity::class.java)
                                startActivity(kelogin)
                                //redirect ke login
                            } else Toast.makeText(this, "Failed to Register :(", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(this, "Failed Register", Toast.LENGTH_LONG).show()
                }
            }
    }
})
    }
}