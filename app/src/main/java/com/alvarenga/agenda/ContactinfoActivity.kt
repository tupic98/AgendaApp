package com.alvarenga.agenda

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import com.alvarenga.agenda.Adapters.ContactInfoAdapter
import com.alvarenga.agenda.Contacts.Contact

class ContactinfoActivity:AppCompatActivity(){
    var contacts: Contact? = null
    var contexte: Context = this
    var activity:ContactinfoActivity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_view)

        val receiveBundle = this.intent.extras
        contacts = receiveBundle.getParcelable("KEEY")

        val tooly = findViewById<Toolbar>(R.id.Contacttoolbar)
        val colly = findViewById<CollapsingToolbarLayout>(R.id.collapsingContactoolbar)
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
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            android.R.id.home -> {onBackPressed();true }
            else -> super.onOptionsItemSelected(item)
        }

    }
}