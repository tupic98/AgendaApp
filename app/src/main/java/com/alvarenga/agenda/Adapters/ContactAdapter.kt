package com.alvarenga.agenda.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alvarenga.agenda.ContactinfoActivity
import com.alvarenga.agenda.Contacts.Contact
import com.alvarenga.agenda.Fragments.ContactFragment
import com.alvarenga.agenda.Fragments.FavoriteFragment
import com.alvarenga.agenda.R
import de.hdodenhof.circleimageview.CircleImageView

class ContactAdapter(private val contact: ArrayList<Contact>, private val context:Context, private val fav:Boolean): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    companion object {
        @SuppressLint("StaticFieldLeak")
        private val contFrag = ContactFragment.getInstance()
        @SuppressLint("StaticFieldLeak")
        private val favFrag = FavoriteFragment.getInstance()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.tab_contactview,parent,false)
        return ContactViewHolder(v, this.fav)
    }

    override fun getItemCount(): Int {
        return contact.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val sendBundle = Bundle()
        holder.name.text = contact[position].name
        holder.img.setImageResource(contact[position].photo)
        holder.img.setOnClickListener { v ->
            sendBundle.putParcelable("KEEY",contact[position])
            val i  = Intent(context, ContactinfoActivity::class.java)
            i.putExtras(sendBundle)
            context.startActivity(i)
            //(contexte as Activity).finish()
        }
        holder.name.setOnClickListener { v ->
            sendBundle.putParcelable("KEEY",contact[position])
            val i = Intent(context, ContactinfoActivity::class.java)
            i.putExtras(sendBundle)
            context.startActivity(i)
        }

        holder.favButton.setImageResource(if(contact[position].isFav)
                                            R.drawable.star_full else R.drawable.star_empty)
        holder.favButton.setOnClickListener { v ->
            //Toast.makeText(v.context, contact[position].name, Toast.LENGTH_SHORT).show()
            if(!fav){
                if(!holder.fav){
                    contact[position].isFav = true
                    holder.favButton.setImageResource(R.drawable.star_full)
                    holder.fav = true
                } else{
                    contact[position].isFav = false
                    holder.favButton.setImageResource(R.drawable.star_empty)
                    holder.fav = false
                }
            }else{
                if (!holder.fav) {
                    contact[position].isFav = true
                    holder.favButton.setImageResource(R.drawable.star_full)
                    holder.fav = true
                } else {
                    contact[position].isFav = false
                    holder.favButton.setImageResource(R.drawable.star_empty)
                    holder.fav = false
                }
                contFrag.update(contact, position, holder.fav)
            }
            favFrag.update(contact)
        }
    }

    class ContactViewHolder(itemView: View, internal var fav:Boolean):RecyclerView.ViewHolder(itemView) {
        /*internal var cardView:CardView = itemView.findViewById(R.id.card_view)*/
        internal var name: TextView = itemView.findViewById(R.id.name)
        internal var img:CircleImageView = itemView.findViewById(R.id.img)
        internal var favButton: ImageView = itemView.findViewById(R.id.butt)
    }

}