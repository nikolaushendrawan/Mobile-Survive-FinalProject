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
import kotlinx.android.synthetic.main.fragment_course.*
import kotlinx.android.synthetic.main.fragment_home.*


class CourseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardgempa.setOnClickListener{
        val intent = Intent (getActivity(), TheoryActivity::class.java)
            intent.putExtra("Type", 1)
            getActivity()?.startActivity(intent)
        }
        cardtsunami.setOnClickListener{
            val intent = Intent (getActivity(), TheoryActivity::class.java)
            intent.putExtra("Type", 2)
            getActivity()?.startActivity(intent)
        }
        cardgunungapi.setOnClickListener{
            val intent = Intent (getActivity(), TheoryActivity::class.java)
            intent.putExtra("Type", 3)
            getActivity()?.startActivity(intent)
        }
        cardbanjir.setOnClickListener{
            val intent = Intent (getActivity(), TheoryActivity::class.java)
            intent.putExtra("Type", 4)
            getActivity()?.startActivity(intent)
        }
        cardtanahlongsor.setOnClickListener{
            val intent = Intent (getActivity(), TheoryActivity::class.java)
            intent.putExtra("Type", 5)
            getActivity()?.startActivity(intent)
        }
    }

}