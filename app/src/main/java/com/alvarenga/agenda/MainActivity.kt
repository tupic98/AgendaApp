package com.alvarenga.agenda

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.alvarenga.agenda.Adapters.ViewPagerAdapter
import com.alvarenga.agenda.Contacts.Contact

class MainActivity : AppCompatActivity() {
    private var PERMISSION_REQUEST:Int = 0
    var contacts:ArrayList<Contact>? = null
    private val projection = arrayOf<String>(ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER)
    private val selectionClause = ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL"
    private val sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC"

    override fun onCreate(savedInstanceState: Bundle?) {
        prepareContacts()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)*/
        /*contacts = ArrayList<Contact>().apply {
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
        }*/

        val bundle = Bundle()
        bundle.putParcelableArrayList("KEY", contacts)

        val tab = findViewById<TabLayout>(R.id.mainTab)
        val viewer = findViewById<ViewPager>(R.id.Pager)
        val adapter = ViewPagerAdapter(this,supportFragmentManager,bundle)

        viewer.adapter = adapter
        tab.setupWithViewPager(viewer)
    }

    fun prepareContacts(){
        contacts = ArrayList()
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            var contact:Cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection, selectionClause,null,sortOrder)
            while(contact.moveToNext()){
                var name:String = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                var phoneNumber:String = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                var firstLetter:String = name[0].toString().toLowerCase()
                println(firstLetter)
                //contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, "","",false))
                when(firstLetter){
                    "a" -> contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, "","",false))
                    "b" -> contacts!!.add(Contact(R.drawable.b_letter,name, phoneNumber, "","",false))
                    "c" -> contacts!!.add(Contact(R.drawable.c_letter,name, phoneNumber, "","",false))
                    "d" -> contacts!!.add(Contact(R.drawable.d_letter,name, phoneNumber, "","",false))
                    "e" -> contacts!!.add(Contact(R.drawable.e_letter,name, phoneNumber, "","",false))
                    "f" -> contacts!!.add(Contact(R.drawable.f_letter,name, phoneNumber, "","",false))
                    "g" -> contacts!!.add(Contact(R.drawable.g_letter,name, phoneNumber, "","",false))
                    "h" -> contacts!!.add(Contact(R.drawable.h_letter,name, phoneNumber, "","",false))
                    "i" -> contacts!!.add(Contact(R.drawable.i_letter,name, phoneNumber, "","",false))
                    "j" -> contacts!!.add(Contact(R.drawable.j_letter,name, phoneNumber, "","",false))
                    "k" -> contacts!!.add(Contact(R.drawable.k_letter,name, phoneNumber, "","",false))
                    "l" -> contacts!!.add(Contact(R.drawable.l_letter,name, phoneNumber, "","",false))
                    "m" -> contacts!!.add(Contact(R.drawable.m_letter,name, phoneNumber, "","",false))
                    "n" -> contacts!!.add(Contact(R.drawable.n_letter,name, phoneNumber, "","",false))
                    "ñ" -> contacts!!.add(Contact(R.drawable.n_letter,name, phoneNumber, "","",false))
                    "o" -> contacts!!.add(Contact(R.drawable.o_letter,name, phoneNumber, "","",false))
                    "p" -> contacts!!.add(Contact(R.drawable.p_letter,name, phoneNumber, "","",false))
                    "q" -> contacts!!.add(Contact(R.drawable.q_letter,name, phoneNumber, "","",false))
                    "r" -> contacts!!.add(Contact(R.drawable.r_letter,name, phoneNumber, "","",false))
                    "s" -> contacts!!.add(Contact(R.drawable.s_letter,name, phoneNumber, "","",false))
                    "t" -> contacts!!.add(Contact(R.drawable.t_letter,name, phoneNumber, "","",false))
                    "u" -> contacts!!.add(Contact(R.drawable.u_letter,name, phoneNumber, "","",false))
                    "v" -> contacts!!.add(Contact(R.drawable.v_letter,name, phoneNumber, "","",false))
                    "w" -> contacts!!.add(Contact(R.drawable.w_letter,name, phoneNumber, "","",false))
                    "x" -> contacts!!.add(Contact(R.drawable.x_letter,name, phoneNumber, "","",false))
                    "y" -> contacts!!.add(Contact(R.drawable.y_letter,name, phoneNumber, "","",false))
                    "z" -> contacts!!.add(Contact(R.drawable.z_letter,name, phoneNumber, "","",false))
                    else -> contacts!!.add(Contact(R.drawable.z_letter,name, phoneNumber, "","",false))
                }
            }
            contact.close()
        }else{
            requestPermission()
        }
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.READ_CONTACTS), PERMISSION_REQUEST)
    }

    @SuppressLint("Recycle")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        /*super.onRequestPermissionsResult(requestCode, permissions, grantResults)*/
        if(requestCode == PERMISSION_REQUEST){
            if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                var contact:Cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection, selectionClause,null,sortOrder)
                while(contact.moveToNext()){
                    var name:String = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    var phoneNumber:String = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    var firstLetter:String = name[0].toString().toLowerCase()
                    println(firstLetter)
                    //contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, "","",false))
                    when(firstLetter){
                        "a" -> contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, "","",false))
                        "b" -> contacts!!.add(Contact(R.drawable.b_letter,name, phoneNumber, "","",false))
                        "c" -> contacts!!.add(Contact(R.drawable.c_letter,name, phoneNumber, "","",false))
                        "d" -> contacts!!.add(Contact(R.drawable.d_letter,name, phoneNumber, "","",false))
                        "e" -> contacts!!.add(Contact(R.drawable.e_letter,name, phoneNumber, "","",false))
                        "f" -> contacts!!.add(Contact(R.drawable.f_letter,name, phoneNumber, "","",false))
                        "g" -> contacts!!.add(Contact(R.drawable.g_letter,name, phoneNumber, "","",false))
                        "h" -> contacts!!.add(Contact(R.drawable.h_letter,name, phoneNumber, "","",false))
                        "i" -> contacts!!.add(Contact(R.drawable.i_letter,name, phoneNumber, "","",false))
                        "j" -> contacts!!.add(Contact(R.drawable.j_letter,name, phoneNumber, "","",false))
                        "k" -> contacts!!.add(Contact(R.drawable.k_letter,name, phoneNumber, "","",false))
                        "l" -> contacts!!.add(Contact(R.drawable.l_letter,name, phoneNumber, "","",false))
                        "m" -> contacts!!.add(Contact(R.drawable.m_letter,name, phoneNumber, "","",false))
                        "n" -> contacts!!.add(Contact(R.drawable.n_letter,name, phoneNumber, "","",false))
                        "ñ" -> contacts!!.add(Contact(R.drawable.n_letter,name, phoneNumber, "","",false))
                        "o" -> contacts!!.add(Contact(R.drawable.o_letter,name, phoneNumber, "","",false))
                        "p" -> contacts!!.add(Contact(R.drawable.p_letter,name, phoneNumber, "","",false))
                        "q" -> contacts!!.add(Contact(R.drawable.q_letter,name, phoneNumber, "","",false))
                        "r" -> contacts!!.add(Contact(R.drawable.r_letter,name, phoneNumber, "","",false))
                        "s" -> contacts!!.add(Contact(R.drawable.s_letter,name, phoneNumber, "","",false))
                        "t" -> contacts!!.add(Contact(R.drawable.t_letter,name, phoneNumber, "","",false))
                        "u" -> contacts!!.add(Contact(R.drawable.u_letter,name, phoneNumber, "","",false))
                        "v" -> contacts!!.add(Contact(R.drawable.v_letter,name, phoneNumber, "","",false))
                        "w" -> contacts!!.add(Contact(R.drawable.w_letter,name, phoneNumber, "","",false))
                        "x" -> contacts!!.add(Contact(R.drawable.x_letter,name, phoneNumber, "","",false))
                        "y" -> contacts!!.add(Contact(R.drawable.y_letter,name, phoneNumber, "","",false))
                        "z" -> contacts!!.add(Contact(R.drawable.z_letter,name, phoneNumber, "","",false))
                        else -> contacts!!.add(Contact(R.drawable.z_letter,name, phoneNumber, "","",false))
                    }
                }
                contact.close()
            }else{
                this.finish()
            }
        }
    }

}
//var firstLetter:String = name[0].toString().toLowerCase()
/*when(firstLetter){
                            "a" -> add(Contact(R.drawable.a_letter,name, phoneNumber, "","",false))
                            "b" -> add(Contact(R.drawable.b_letter,name, phoneNumber, "","",false))
                            "c" -> add(Contact(R.drawable.c_letter,name, phoneNumber, "","",false))
                            "d" -> add(Contact(R.drawable.d_letter,name, phoneNumber, "","",false))
                            "e" -> add(Contact(R.drawable.e_letter,name, phoneNumber, "","",false))
                            "f" -> add(Contact(R.drawable.f_letter,name, phoneNumber, "","",false))
                            "g" -> add(Contact(R.drawable.g_letter,name, phoneNumber, "","",false))
                            "h" -> add(Contact(R.drawable.h_letter,name, phoneNumber, "","",false))
                            "i" -> add(Contact(R.drawable.i_letter,name, phoneNumber, "","",false))
                            "j" -> add(Contact(R.drawable.j_letter,name, phoneNumber, "","",false))
                            "k" -> add(Contact(R.drawable.k_letter,name, phoneNumber, "","",false))
                            "l" -> add(Contact(R.drawable.l_letter,name, phoneNumber, "","",false))
                            "m" -> add(Contact(R.drawable.m_letter,name, phoneNumber, "","",false))
                            "n" -> add(Contact(R.drawable.n_letter,name, phoneNumber, "","",false))
                            "ñ" -> add(Contact(R.drawable.n_letter,name, phoneNumber, "","",false))
                            "o" -> add(Contact(R.drawable.o_letter,name, phoneNumber, "","",false))
                            "p" -> add(Contact(R.drawable.p_letter,name, phoneNumber, "","",false))
                            "q" -> add(Contact(R.drawable.q_letter,name, phoneNumber, "","",false))
                            "r" -> add(Contact(R.drawable.r_letter,name, phoneNumber, "","",false))
                            "s" -> add(Contact(R.drawable.s_letter,name, phoneNumber, "","",false))
                            "t" -> add(Contact(R.drawable.t_letter,name, phoneNumber, "","",false))
                            "u" -> add(Contact(R.drawable.u_letter,name, phoneNumber, "","",false))
                            "v" -> add(Contact(R.drawable.v_letter,name, phoneNumber, "","",false))
                            "w" -> add(Contact(R.drawable.w_letter,name, phoneNumber, "","",false))
                            "x" -> add(Contact(R.drawable.x_letter,name, phoneNumber, "","",false))
                            "y" -> add(Contact(R.drawable.y_letter,name, phoneNumber, "","",false))
                            "z" -> add(Contact(R.drawable.z_letter,name, phoneNumber, "","",false))
                        }*/
