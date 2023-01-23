package com.example.SurviveProject

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.buy_dialog_box.view.*
import kotlinx.android.synthetic.main.shop_list.view.*

class shopAdapter (private var listshop: List<shopList>,
                   private val shopact : Activity,
                   private val firebase: FirebaseFirestore,
                   private var moneyacc:TextView): RecyclerView.Adapter<shopAdapter.ViewHolder>(){

                        class ViewHolder(item: View):RecyclerView.ViewHolder(item){
                            val avatarshop : ImageView = item.avatarshop
                            val price :TextView = item.avatarprice
                            val avatarname : TextView = item.avatarname
                            val viewcard : CardView= item.mainCardShop
                        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shopAdapter.ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.shop_list, parent, false)
        return shopAdapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.avatarshop.setImageResource(listshop[position].imagebit)
    holder.price.text = listshop[position].price.toString()
    holder.avatarname.text = listshop[position].itemname
        holder.viewcard.setOnClickListener {
            shopDialog(listshop[position].imgid, listshop[position].price, position)
        }
    }

    override fun getItemCount(): Int {
    return listshop.size
    }

    private fun shopDialog(id: Int, price: Int, position: Int) {
        var newPrice: Int = 0
        var ownedgold: Int = 0
        var list1: ArrayList<Int> = ArrayList()
        var ListData: ArrayList<shopList> = ArrayList()
        val mDialogView = LayoutInflater.from(shopact).inflate(R.layout.buy_dialog_box, null)
        val mBuilder = AlertDialog.Builder(shopact)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setBackgroundDrawable(AppCompatResources.getDrawable(shopact, R.drawable.dialogboxyes))

    firebase.collection("Users")
        .document(FirebaseAuth.getInstance().uid.toString())
        .get().addOnSuccessListener { val
        profilepicture = it.get("avatarInvent")
        newPrice = it.getLong("gold")?.minus(price)!!.toInt()
        ownedgold = it.getLong("gold")!!.toInt()
            Log.e("Data listed",profilepicture.toString())
            list1.add(1)
            if(profilepicture.toString().contains("2")){
                list1.add(2)
            }
            if(profilepicture.toString().contains("3")){
                list1.add(3)
            }
            if(profilepicture.toString().contains("4")){
                list1.add(4)
            }
            if(profilepicture.toString().contains("5")){
                list1.add(5)
            }
            if(profilepicture.toString().contains("6")){
                list1.add(6)
            }
            if(profilepicture.toString().contains("7")){
                list1.add(7)
            }
            if(profilepicture.toString().contains("8")){
                list1.add(8)
            }
            if(profilepicture.toString().contains("9")){
                list1.add(9)
            }
            list1.add(id)
        }
        mDialogView.dialog_yes.setOnClickListener{
        if(price > ownedgold){
        Toast.makeText(shopact, "Not Enough Gold", Toast.LENGTH_SHORT).show()
        }else{
        firebase.collection("Users")
            .document(FirebaseAuth.getInstance().uid.toString())
            .update("avatarInvent", list1, "gold", newPrice)

            firebase.collection("Users")
                .document(FirebaseAuth.getInstance().uid.toString())
                .get().addOnSuccessListener {
                    val getList = it.get("avatarInvent")
                    Log.e("Data listed", getList.toString())
                    if (!getList.toString().contains("2")){
                        ListData.add(shopList("Listen Boy", 200, R.drawable.avatar1, 2))
                    }
                    if (!getList.toString().contains("3")){
                        ListData.add(shopList("Punk", 100, R.drawable.avatar2, 3))
                    }
                    if (!getList.toString().contains("4")){
                        ListData.add(shopList("Canvas Boy", 500, R.drawable.avatar3, 4))
                    }
                    if (!getList.toString().contains("5")){
                        ListData.add(shopList("Delivery Boy", 800, R.drawable.avatar4, 5))
                    }
                    if (!getList.toString().contains("6")){
                        ListData.add(shopList("Glasses Girl", 100, R.drawable.avatar5, 6))
                    }
                    if (!getList.toString().contains("7")){
                        ListData.add(shopList("Little Boy", 200, R.drawable.avatar6, 7))
                    }
                    if (!getList.toString().contains("8")){
                        ListData.add(shopList("Hawaii Boy", 500, R.drawable.avatar7, 8))
                }
            listshop = ListData
            moneyacc.text = it.getLong("gold").toString()
                    notifyDataSetChanged()
        }
        }
            mAlertDialog.dismiss()
    }
        mDialogView.dialog_no.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }
}