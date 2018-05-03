package com.alvarenga.agenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.alvarenga.agenda.Adapters.ViewPagerAdapter
import com.alvarenga.agenda.Contacts.Contact

class MainActivity : AppCompatActivity() {

    var contacts:ArrayList<Contact>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        contacts = ArrayList<Contact>().apply {
            add(Contact(R.drawable.n_letter,"Nelson Castro", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.m_letter,"Mancha ", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.l_letter,"Lito", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.n_letter,"Nelson Castro", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.m_letter,"Mancha ", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.l_letter,"Lito", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.n_letter,"Nelson Castro", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.m_letter,"Mancha ", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.l_letter,"Lito", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.n_letter,"Nelson Castro", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.m_letter,"Mancha ", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.l_letter,"Lito", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.n_letter,"Nelson Castro", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.m_letter,"Mancha ", 72936840, "nelsonmandela@gmail.com","DESC",false))
            add(Contact(R.drawable.l_letter,"Lito", 72936840, "nelsonmandela@gmail.com","DESC",false))
        }

        val bundle = Bundle()
        bundle.putParcelableArrayList("KEY", contacts)

        val tab = findViewById<TabLayout>(R.id.mainTab)
        val viewer = findViewById<ViewPager>(R.id.Pager)
        val adapter = ViewPagerAdapter(this,supportFragmentManager,bundle)

        viewer.adapter = adapter
        tab.setupWithViewPager(viewer)
    }
}
