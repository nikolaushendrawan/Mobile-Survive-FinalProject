package com.example.SurviveProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_detailachievement.*
import kotlinx.android.synthetic.main.fragment_rank.*

class DetailachievementFragment : Fragment() {

    private lateinit var database: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth
    private lateinit var navController: NavController

    var listData:ArrayList<userAchievement> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailachievement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)

        backbtndetailachievement.setOnClickListener{ navController.navigate(R.id.action_detailachievementFragment_to_profileFragment) }
        database.collection("Users")
            .document(FirebaseAuth.getInstance().uid.toString())
            .get().addOnSuccessListener {
                var listachieve = it.get("achievementList").toString()
                listData.add(userAchievement("Newborn","Create An Account",R.drawable.achievementgold,6))
                listData.add(userAchievement("Earthshaker","Get perfect score from Gempa Bumi stage",R.drawable.gempa,1))
                listData.add(userAchievement("King Of The Sea","Get perfect score from Tsunami stage",R.drawable.tsunami,2))
                listData.add(userAchievement("Krakatoa!","Get perfect score from Gunungapi stage",R.drawable.gunungapi,3))
                listData.add(userAchievement("The Floor is Water","Get perfect score from Banjir stage",R.drawable.banjir,4))
                listData.add(userAchievement("Avalanche","Get perfect score from Tanah Longsor stage",R.drawable.tanah_longsor,5))
                listData.add(userAchievement("Natural Disaster Expert","Finish all the stages with perfect score",R.drawable.achievementend,7))
                achievementlist.adapter = Detailachievementadapter(listData,listachieve)
                achievementlist.layoutManager = LinearLayoutManager(requireContext())
            }
    }
}