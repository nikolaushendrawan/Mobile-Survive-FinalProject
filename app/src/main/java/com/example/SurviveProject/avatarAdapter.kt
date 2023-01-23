package com.example.SurviveProject

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.avatar_list.view.*
import kotlinx.android.synthetic.main.avatardialogbox.view.*
import kotlinx.android.synthetic.main.fragment_editavatar.view.*

class avatarAdapter (var avatarlist : List<avatarList>,
                     private val shopact : Activity,
                     private val firebase : FirebaseFirestore,
                     private val navController : NavController,
): RecyclerView.Adapter<avatarAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val namaavatar: TextView = item.avatarnameprofile
        val avatar: ImageView = item.avatarimgprofile
        val avatarcard: CardView = item.avatarcardprofile
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.avatar_list, parent, false)
        return avatarAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaavatar.text = avatarlist[position].itemname
        holder.avatar.setImageResource(avatarlist[position].imagebit)
        holder.avatarcard.setOnClickListener {
            shopDialog(avatarlist[position].imgid, position)
        }
    }

    override fun getItemCount(): Int {
        return avatarlist.size
    }

    private fun shopDialog(id: Int, position: Int) {
        val mDialogView = LayoutInflater.from(shopact).inflate(R.layout.avatardialogbox, null)
        val mBuilder = AlertDialog.Builder(shopact)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setBackgroundDrawable(
            AppCompatResources.getDrawable(
                shopact,
                R.drawable.dialogboxyes
            )
        )


        mDialogView.dialog_yes_avatar.setOnClickListener {
            firebase.collection("Users")
                .document(FirebaseAuth.getInstance().uid.toString())
                .update("activeAvatar", id)
            mAlertDialog.dismiss()
            navController.navigate(R.id.action_editAvatarFragment_to_profileFragment)
        }

        mDialogView.dialog_no_avatar.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }
}

