package com.example.SurviveProject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.fragment_learn.view.*
import kotlinx.android.synthetic.main.level_list.view.*

class QuizAdapter (private var listlevel:List<Int>,
                   private val levelactivity : Activity,
                   private var userlevel: String): RecyclerView.Adapter<QuizAdapter.ViewHolder>(){

    class ViewHolder(item:View):RecyclerView.ViewHolder(item){
        val levelcard : CardView = item.cardquiz
        val quizlevel : TextView = item.levelname
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.level_list , parent , false)
        return QuizAdapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    if ((listlevel[position] + 1) == 1){
        holder.quizlevel.text = "Gempa Bumi"
    }
        if ((listlevel[position] + 1) == 2){
            holder.quizlevel.text = "Tsunami"
        }
        if ((listlevel[position] + 1) == 3){
            holder.quizlevel.text = "Gunung Api"
        }
        if ((listlevel[position] + 1) == 4){
            holder.quizlevel.text = "Bencana Banjir"
        }
        if ((listlevel[position] + 1) == 5){
            holder.quizlevel.text = "Tanah Longsor"
        }

        if(!userlevel.contains(listlevel[position].toString())){
        holder.levelcard.setCardBackgroundColor(Color.parseColor("#808080"))
            holder.quizlevel.setTextColor(Color.parseColor("#3A3B3C"))
            holder.levelcard.isClickable = false
        }
        else{
            holder.levelcard.setOnClickListener{
            val intent = Intent ( levelactivity, GameplayActivity::class.java )
            intent.putExtra("Status", (listlevel[position] + 1))
                levelactivity.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return listlevel.size
    }
}