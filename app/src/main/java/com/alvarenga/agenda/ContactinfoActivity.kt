package com.alvarenga.agenda

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.alvarenga.agenda.Adapters.ContactInfoAdapter
import com.alvarenga.agenda.Contacts.Contact

class ContactinfoActivity:AppCompatActivity(){
    var contacts: Contact? = null
    var contexte: Context = this
    var activity:ContactinfoActivity = this
    var contactInfo:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_view)

        val receiveBundle = this.intent.extras
        contacts = receiveBundle.getParcelable("KEEY")

        createShareableContent()

        val tooly = findViewById<Toolbar>(R.id.Contacttoolbar)
        val colly = findViewById<CollapsingToolbarLayout>(R.id.collapsingContactoolbar)
        val call = findViewById<LinearLayout>(R.id.caller)
        val share = findViewById<LinearLayout>(R.id.sharable)
        val imgToolbar = findViewById<ImageView>(R.id.imagy_single)
        setSupportActionBar(tooly)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        colly.setCollapsedTitleTextColor(Color.WHITE)
        colly.title = contacts!!.name
        colly.setExpandedTitleTextAppearance(R.style.AppBarExpanded)
        colly.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed)

        val rv: RecyclerView = findViewById(R.id.contactviewRecylcer)
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = ContactInfoAdapter(contacts!!,contexte,activity)
        rv.adapter = adapter
        call.setOnClickListener({
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 225)
            } else {
                val callIntent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", contacts!!.phone, null))
                startActivity(callIntent)
            }
        })
        share.setOnClickListener({
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, contactInfo)
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            android.R.id.home -> {onBackPressed();true }
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun createShareableContent() {
        /* if (firstName != null || lastName != null) {

             contactInfo = "Name: "

             if (firstName != null && lastName != null) {
                 contactInfo += firstName + " " + lastName + "."
             } else if (firstName != null) {
                 contactInfo += firstName + "."
             } else {
                 contactInfo += lastName + "."
             }

         } else {
             contactInfo = "No name."
         }*/

        if (contacts!!.phone != null) {
            contactInfo = "\nPhone:"
            contactInfo += contacts!!.phone
        }
/*
        if (idContact != null) {
            contactInfo += "\nContact ID: $idContact."
        }*/

        /*if (emails != null) {
            contactInfo!!.plus("\nE-mail: $emails.")*/
/*        }

        if (address != null) {
            contactInfo += "\nAddress: $address."
        }

        if (birthday != null) {
            contactInfo += "\nBirthday: $birthday."
        }*/
    }
}