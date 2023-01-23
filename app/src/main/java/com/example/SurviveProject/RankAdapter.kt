package com.example.SurviveProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rank_list.view.*

class RankAdapter (private var listLead:List<userLeaderboard> ,
                   private var activeAcc:String): RecyclerView.Adapter<RankAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val numberrank: TextView = item.ranknumber
        val avatarrank: ImageView = item.rankImg
        val usernamerank: TextView = item.usernamerank
        val totalscore: TextView = item.scorerank
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rank_list, parent, false)
        return RankAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.numberrank.text = (position + 1).toString()
        holder.usernamerank.text = listLead[position].username
        holder.totalscore.text = listLead[position].score.toString()
        if (listLead[position].activeAvatar == 1) {
            holder.avatarrank.setImageResource(R.drawable.defaultuser)
        } else if (listLead[position].activeAvatar == 2) {
            holder.avatarrank.setImageResource(R.drawable.avatar1)
        } else if (listLead[position].activeAvatar == 3) {
            holder.avatarrank.setImageResource(R.drawable.avatar2)
        } else if (listLead[position].activeAvatar == 4) {
            holder.avatarrank.setImageResource(R.drawable.avatar3)
        } else if (listLead[position].activeAvatar == 5) {
            holder.avatarrank.setImageResource(R.drawable.avatar4)
        } else if (listLead[position].activeAvatar == 6) {
            holder.avatarrank.setImageResource(R.drawable.avatar5)
        } else if (listLead[position].activeAvatar == 7) {
            holder.avatarrank.setImageResource(R.drawable.avatar6)
        } else if (listLead[position].activeAvatar == 8) {
            holder.avatarrank.setImageResource(R.drawable.avatar7)
        }
    }

    override fun getItemCount(): Int {
        return listLead.size
    }
}

