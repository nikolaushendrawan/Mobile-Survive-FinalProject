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
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_rank.*

class RankFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    var datauser = ""

    var listData:ArrayList<userLeaderboard> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        backbtnrank.setOnClickListener{ navController.navigate(R.id.action_rankFragment_to_homefragment) }
        database.collection("Users").document(mAuth.uid.toString())
            .get().addOnSuccessListener {
                datauser = it.getString("username").toString()
                database.collection("Users").orderBy("score", Query.Direction.DESCENDING).get().addOnSuccessListener {
                for (i in it){
                listData.add(userLeaderboard(i.getString("username").toString(),
                    i.getLong("score")!!.toInt(),i.getLong("activeAvatar")!!.toInt() ))
                }

                    ranklist.adapter = RankAdapter(listData, datauser)
                    ranklist.layoutManager = LinearLayoutManager(requireContext())
                }
            }
    }
}