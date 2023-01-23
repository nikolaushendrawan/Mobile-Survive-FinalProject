package com.example.SurviveProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.achievement_list.view.*

class AchievementAdapter (private var listachievement:List<Int>): RecyclerView.Adapter<AchievementAdapter.ViewHolder>(){

    class ViewHolder(item:View):RecyclerView.ViewHolder(item){
        val achievementlogo : ImageView = item.achievement
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.achievement_list, parent, false)
        return AchievementAdapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: AchievementAdapter.ViewHolder, position: Int) {
    if (listachievement[position] == 1){
        holder.achievementlogo.setImageResource(R.drawable.gempa)
    }
        if (listachievement[position] == 2){
            holder.achievementlogo.setImageResource(R.drawable.tsunami)
        }
        if (listachievement[position] == 3){
            holder.achievementlogo.setImageResource(R.drawable.gunungapi)
        }
        if (listachievement[position] == 4){
            holder.achievementlogo.setImageResource(R.drawable.banjir)
        }
        if (listachievement[position] == 5){
            holder.achievementlogo.setImageResource(R.drawable.tanah_longsor)
        }
        if (listachievement[position] == 6){
            holder.achievementlogo.setImageResource(R.drawable.achievementgold)
        }
        if (listachievement[position] == 7){
            holder.achievementlogo.setImageResource(R.drawable.achievementend)
        }
    }

    override fun getItemCount(): Int {
        return listachievement.size
    }
}