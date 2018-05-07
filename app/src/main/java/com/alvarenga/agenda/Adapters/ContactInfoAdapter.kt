package com.alvarenga.agenda.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.alvarenga.agenda.Contacts.Contact
import com.alvarenga.agenda.R
import android.Manifest
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.support.v4.app.ActivityCompat
import com.alvarenga.agenda.ContactinfoActivity
import de.hdodenhof.circleimageview.CircleImageView

class ContactInfoAdapter(private val contact: Contact, private val context:Context,private val activityCompat: ContactinfoActivity): RecyclerView.Adapter<ContactInfoAdapter.SingleViewHolder>() {

    var contactInfo : ArrayList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contact_info_view,parent,false)
        return SingleViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        contactInfo= crearArrayList(contact)

        /*var intent = Intent()
        holder.call.setOnClickListener { v ->
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activityCompat,arrayOf<String>(Manifest.permission.CALL_PHONE),225 )
            }else{
                val callIntent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel",phone,null))
                context.startActivity(callIntent)
            }
        }*/
        when(position){
            0 -> {//holder.imgSrc.setImageResource(R.drawable.star_full)
                holder.text1.text = contactInfo!![position]
                holder.text2.text = "Teléfono:" }
            1 -> {//holder.imgSrc.setImageResource(R.drawable.star_full)
                holder.text1.text = contactInfo!![position]
                holder.text2.text = "Correo Electrónico:" }
            2 -> {
                holder.text1.text = contactInfo!![position]
                holder.text2.text = "Descripción:"
            }
        }
    }

    fun crearArrayList(contact: Contact): ArrayList<String>{
        var auxi = ArrayList<String>().apply {
            add(contact.phone)
            add(contact.email)
            add(contact.other)
        }

        return auxi
    }

    class SingleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //internal var card : CardView = itemView.findViewById(R.id.card_view2)
        //internal var photo: CircleImageView = itemView.findViewById(R.id.img)
        internal var text1 : TextView = itemView.findViewById(R.id.texty1_single)
        internal var  text2 : TextView = itemView.findViewById(R.id.texty2_single)
        //internal var imgSrc : ImageView = itemView.findViewById(R.id.imagy_single)
        //internal var call:LinearLayout = itemView.findViewById(R.id.caller)
        //internal var share:LinearLayout = itemView.findViewById(R.id.sharable)

    }
}