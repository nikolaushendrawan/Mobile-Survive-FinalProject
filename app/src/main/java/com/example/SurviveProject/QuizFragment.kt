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
import kotlinx.android.synthetic.main.fragment_quiz.*

class QuizFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore

    var leveldata:ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        leveldata.add(0)
        leveldata.add(1)
        leveldata.add(2)
        leveldata.add(3)
        leveldata.add(4)

        database.collection("Users").document(mAuth.uid.toString()).get().addOnSuccessListener {
            var levellist = it.get("level").toString()
            listlevel.adapter = QuizAdapter(leveldata, requireActivity(), levellist)
            listlevel.layoutManager = LinearLayoutManager(requireContext())

        }
    }
}