package com.example.SurviveProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_editavatar.*

class EditAvatarFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController
    var jumlahkoin = 0
    var listData:ArrayList<avatarList> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_editavatar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    mAuth = FirebaseAuth.getInstance()
    navController = Navigation.findNavController(view)
    database = FirebaseFirestore.getInstance()

        database.collection("Users").document(mAuth.uid.toString())
            .get().addOnSuccessListener {
                val getList = it.get("avatarInvent")
                if (getList.toString().contains("1")){
                listData.add(avatarList("Normal Boy", R.drawable.defaultuser, 1))
                }
                if (getList.toString().contains("2")){
                    listData.add(avatarList("Listen Boy", R.drawable.avatar1, 2))
                }
                if (getList.toString().contains("3")){
                    listData.add(avatarList("Punk", R.drawable.avatar2, 3))
                }
                if (getList.toString().contains("4")){
                    listData.add(avatarList("Canvas Boy", R.drawable.avatar3, 4))
                }
                if (getList.toString().contains("5")){
                    listData.add(avatarList("Delivery Boy", R.drawable.avatar4, 5))
                }
                if (getList.toString().contains("6")){
                    listData.add(avatarList("Glasses Girl", R.drawable.avatar5, 6))
                }
                if (getList.toString().contains("7")){
                    listData.add(avatarList("Little Boy", R.drawable.avatar6, 7))
                }
                if (getList.toString().contains("8")){
                    listData.add(avatarList("Hawaii Boy", R.drawable.avatar7, 8))
                }
                listavatar.adapter = avatarAdapter(listData, requireActivity(), database, navController)
                listavatar.layoutManager = LinearLayoutManager(requireContext())
            }
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigate(R.id.action_editAvatarFragment_to_profileFragment)
                }
            })
    }
}