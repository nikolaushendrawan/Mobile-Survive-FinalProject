package com.example.SurviveProject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var database: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth
    private lateinit var navController: NavController

    var listData:ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)

        titleachievement.setOnClickListener { navController.navigate(R.id.action_profileFragment_to_detailachievementFragment) }
        backbtnprofile.setOnClickListener { navController.navigate(R.id.action_profileFragment_to_homefragment) }
        logoProfile.setOnClickListener { navController.navigate(R.id.action_profileFragment_to_editAvatarFragment) }
        database.collection("Users")
            .document(FirebaseAuth.getInstance().uid.toString())
            .get().addOnSuccessListener {
                namaprofile.text = it.getString(  "username")
                var avatarid = it.getLong("activeAvatar")!!.toInt()
                var totalcoin = it.getLong("gold")!!.toInt()
                var listachieve = it.get("achievementList").toString()
                var totalskor = it.getLong("score").toString()
                skorprofile.text = totalskor
                jumlahkoinprofile.text = totalcoin.toString()
                if (listachieve.contains("6")){
                    listData.add(6)
                }
                if (listachieve.contains("1")){
                    listData.add(1)
                }
                if (listachieve.contains("2")){
                    listData.add(2)
                }
                if (listachieve.contains("3")){
                    listData.add(3)
                }
                if (listachieve.contains("4")){
                    listData.add(4)
                }
                if (listachieve.contains("5")){
                    listData.add(5)
                }
                if (listachieve.contains("7")){
                    listData.add(7)
                }
                achievementrecyler.adapter = AchievementAdapter(listData)

                if (avatarid == 1) {
                    logoProfile.setImageResource(R.drawable.defaultuser)
                }
                if (avatarid == 2) {
                    logoProfile.setImageResource(R.drawable.avatar1)
                }
                if (avatarid == 3) {
                    logoProfile.setImageResource(R.drawable.avatar2)
                }
                if (avatarid == 4) {
                    logoProfile.setImageResource(R.drawable.avatar3)
                }
                if (avatarid == 5) {
                    logoProfile.setImageResource(R.drawable.avatar4)
                }
                if (avatarid == 6) {
                    logoProfile.setImageResource(R.drawable.avatar5)
                }
                if (avatarid == 7) {
                    logoProfile.setImageResource(R.drawable.avatar6)
                }
                if (avatarid == 8) {
                    logoProfile.setImageResource(R.drawable.avatar7)
                }
                }

        logoutbtn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent (activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity?.startActivity(intent)
        }
    }

}