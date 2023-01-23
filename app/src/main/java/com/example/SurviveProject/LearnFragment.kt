package com.example.SurviveProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_learn.*


class LearnFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var database: FirebaseFirestore
    var jumlahkoin = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        cardlearnhome.setOnClickListener { navController.navigate(R.id.action_learnfragment_to_courseFragment) }
        cardquizhome.setOnClickListener { navController.navigate(R.id.action_learnfragment_to_quizFragment) }
        database.collection("Users")
            .document(FirebaseAuth.getInstance().uid.toString())
            .get().addOnSuccessListener {
                jumlahkoin = it.getLong("gold")!!.toInt()
                jumlahkoinlearn.text = jumlahkoin.toString()
            }

    }
}