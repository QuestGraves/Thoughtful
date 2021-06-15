package com.keltica.thoughtful.view.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keltica.thoughtful.R
import com.keltica.thoughtful.model.ContactModel


class ChoseContactRecyclerAdapter(private val contactModelList: List<ContactModel>) : RecyclerView.Adapter<ChoseContactRecyclerAdapter.ChoseContactViewHolder>(){


    //Returns an instance of this class's inner class ViewHolder with the proper objects hooked up...
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoseContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_recycler_item_contact,
        parent, false)
        return ChoseContactViewHolder(itemView)
    }

    //This is the one called many times potentially, used cached references here
    override fun onBindViewHolder(holder: ChoseContactViewHolder, position: Int) {
        val currentItem = contactModelList[position]
        //Set fields in the holder

        holder.contactPhoto.setImageResource(R.drawable.ic_baseline_android_80) //ToDo This need to be mapped from contact photo uri somehow quickly...may cache them.
        holder.contactName.text = currentItem.displayName
        holder.contactPhone.text = currentItem.phoneNumber
    }


    override fun getItemCount() = contactModelList.size

    class ChoseContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val contactPhoto: ImageView = itemView.findViewById(R.id.contact_image_view)
        val contactName: TextView = itemView.findViewById(R.id.contact_name)
        val contactPhone: TextView = itemView.findViewById(R.id.contact_phone_number)

    }

}