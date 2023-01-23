package com.example.SurviveProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.SurviveProject.R.layout.fragment_home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*



class HomeFragment : Fragment() {

    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController
    var jumlahkoin = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        database.collection("Users")
            .document(FirebaseAuth.getInstance().uid.toString())
            .get().addOnSuccessListener {
                namauser.text = it.getString("username")
                jumlahkoin = it.getLong("gold")!!.toInt()
                jumlahkoinhome.text = jumlahkoin.toString()
                val avatarid = it.getLong("activeAvatar")!!.toInt()
                if (avatarid == 1) {
                    logoProfilehome.setImageResource(R.drawable.defaultuser)
                }
                if (avatarid == 2) {
                    logoProfilehome.setImageResource(R.drawable.avatar1)
                }
                if (avatarid == 3) {
                    logoProfilehome.setImageResource(R.drawable.avatar2)
                }
                if (avatarid == 4) {
                    logoProfilehome.setImageResource(R.drawable.avatar3)
                }
                if (avatarid == 5) {
                    logoProfilehome.setImageResource(R.drawable.avatar4)
                }
                if (avatarid == 6) {
                    logoProfilehome.setImageResource(R.drawable.avatar5)
                }
                if (avatarid == 7) {
                    logoProfilehome.setImageResource(R.drawable.avatar6)
                }
                if (avatarid == 8) {
                    logoProfilehome.setImageResource(R.drawable.avatar7)
                }
            }

                learnhome.setOnClickListener { navController.navigate(R.id.action_homefragment_to_learngragment) }

                rankhome.setOnClickListener { navController.navigate(R.id.action_homefragment_to_rankFragment) }

                profilehome.setOnClickListener { navController.navigate(R.id.action_homefragment_to_profileFragment) }

                shophome.setOnClickListener { navController.navigate(R.id.action_homefragment_to_shopFragment) }
            }
    }


