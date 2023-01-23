package com.example.SurviveProject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    var jumlahkoin = 0
    var ListData:ArrayList<shopList> = ArrayList()
    var listAchieve: ArrayList<Int> = ArrayList()
    var achieve = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()


        database.collection("Users").document(mAuth.uid.toString())
            .get().addOnSuccessListener {
                achieve = it.get("achievementList").toString()
                jumlahkoin = it.getLong("gold")!!.toInt()
                jumlahkoinshop.text = jumlahkoin.toString()
                val getList = it.get("avatarInvent")
                if (!getList.toString().contains("2")){
                    ListData.add(shopList("Listen Boy", 1000, R.drawable.avatar1, 2))
                }
                if (!getList.toString().contains("3")){
                    ListData.add(shopList("Punk", 1500, R.drawable.avatar2, 3))
                }
                if (!getList.toString().contains("4")){
                    ListData.add(shopList("Canvas Boy", 550, R.drawable.avatar3, 4))
                }
                if (!getList.toString().contains("5")){
                    ListData.add(shopList("Delivery Boy", 800, R.drawable.avatar4, 5))
                }
                if (!getList.toString().contains("6")){
                    ListData.add(shopList("Glasses Girl", 1200, R.drawable.avatar5, 6))
                }
                if (!getList.toString().contains("7")){
                    ListData.add(shopList("Little Boy", 500, R.drawable.avatar6, 7))
                }
                if (!getList.toString().contains("8")){
                    ListData.add(shopList("Hawaii Boy", 1000, R.drawable.avatar7, 8))
                }
                Log.e("data listed", ListData.toString())
                listshopbox.adapter = shopAdapter(ListData, requireActivity(), database, jumlahkoinshop)
                listshopbox.layoutManager = LinearLayoutManager(requireContext())
            }
    }
}