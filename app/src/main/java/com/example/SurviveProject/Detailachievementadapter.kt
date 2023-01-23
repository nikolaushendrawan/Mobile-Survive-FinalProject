package com.example.SurviveProject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.achievement_list.view.*
import kotlinx.android.synthetic.main.detailachievement_list.view.*

class Detailachievementadapter(private var detailachievementlist:List<userAchievement>,
                                private var  achievementuser: String): RecyclerView.Adapter<Detailachievementadapter.ViewHolder>(){

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val achievementimg : ImageView = item.achievementimage
        val achievementname : TextView = item.achievementname
        val descachievement : TextView = item.achievementdescription
        val achievementcard : CardView = item.achievementcard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.detailachievement_list, parent, false)
        return Detailachievementadapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: Detailachievementadapter.ViewHolder, position: Int) {
        holder.achievementname.text = detailachievementlist[position].achievementname
        holder.descachievement.text = detailachievementlist[position].achievementdesc
        holder.achievementimg.setImageResource(detailachievementlist[position].imagebit)
        if(!achievementuser.contains(detailachievementlist[position].imgid.toString())){
            if (detailachievementlist[position].imgid == 1){
                holder.achievementimg.setImageResource(R.drawable.gempabelumdapet)
            }
            if (detailachievementlist[position].imgid == 2){
                holder.achievementimg.setImageResource(R.drawable.tsunamibelumdapet)
            }
            if (detailachievementlist[position].imgid == 3){
                holder.achievementimg.setImageResource(R.drawable.gunungapibelumdapet)
            }
            if (detailachievementlist[position].imgid == 4){
                holder.achievementimg.setImageResource(R.drawable.banjirbelumdapet)
            }
            if (detailachievementlist[position].imgid == 5){
                holder.achievementimg.setImageResource(R.drawable.tanahlongsorbelumdapet)
            }
            if (detailachievementlist[position].imgid == 6){
                holder.achievementimg.setImageResource(R.drawable.achievementgoldbelumdapet)
            }
            if (detailachievementlist[position].imgid == 7){
                holder.achievementimg.setImageResource(R.drawable.achievementendbelumdapet)
            }
        }
    }

    override fun getItemCount(): Int {
        return detailachievementlist.size
    }
}