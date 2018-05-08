package com.alvarenga.agenda

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.alvarenga.agenda.Adapters.FavoriteAdapter
import com.alvarenga.agenda.Adapters.ViewPagerAdapter
import com.alvarenga.agenda.Contacts.Contact

class MainActivity : AppCompatActivity() {
    private var PERMISSION_REQUEST:Int = 0
    private var instance:Boolean = false

    private val projection = arrayOf<String>(ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER)
    private val selectionClause = ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL"
    private val sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC"
    private val projectionEmail = arrayOf<String>(ContactsContract.CommonDataKinds.Email._ID,ContactsContract.CommonDataKinds.Email.ADDRESS,ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.LABEL)
    //private val selectionClauseEmail = ContactsContract.Data.LOOKUP_KEY + " = ?" + " AND " + ContactsContract.Data.MIMETYPE + " = " + "'" + ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE + "'"
    private val selectionClauseEmail = ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?"
    private val emailSelectionArgs = arrayOf<String>(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE)
    private val emailSortOrder = ContactsContract.CommonDataKinds.Email.DISPLAY_NAME + " ASC"
    private val MIME:String = ContactsContract.Data.MIMETYPE + "=?"
    private val params = arrayOf(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)

    companion object {
        @JvmStatic var contacts:ArrayList<Contact>? = null
        @JvmStatic var nameList:ArrayList<String>? = null
        @JvmStatic var emailList:ArrayList<String>? = null
        @JvmStatic var phoneList:ArrayList<String>? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        prepareContacts()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bundle = Bundle()
        bundle.putParcelableArrayList("KEY", contacts)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.add_contact)
        val tab = findViewById<TabLayout>(R.id.mainTab)
        val viewer = findViewById<ViewPager>(R.id.Pager)
        val adapter = ViewPagerAdapter(this,supportFragmentManager,bundle)



        tab.setOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab:TabLayout.Tab) {
                viewer.setCurrentItem(tab.position, true)
            }
            override fun onTabUnselected(tab:TabLayout.Tab) {}
            override fun onTabReselected(tab:TabLayout.Tab) {//scroll to top
                try
                {
                    val f = adapter.getItem(tab.position)
                    val fragmentView = f.view
                    val mRecyclerView = fragmentView!!.findViewById(R.id.contactsFrag) as RecyclerView//mine one is RecyclerView
                    mRecyclerView.scrollToPosition(0)
                }
                catch (npe:NullPointerException) {}
            }
        })

        viewer.adapter = adapter
        tab.setupWithViewPager(viewer)

        floatingActionButton.setOnClickListener { v ->
            val intent = Intent(applicationContext, AddContactActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        }
    }

    @SuppressLint("Recycle")
    fun prepareContacts(){
        contacts = ArrayList()
        nameList = ArrayList()
        phoneList = ArrayList()
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            var contact:Cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection, selectionClause,null,sortOrder)

            while(contact.moveToNext()){
                var name:String = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                var phoneNumber:String = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                var email:String = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                var firstLetter:String = name[0].toString().toLowerCase()
                println(firstLetter)
                //contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, "","",false))
                when(firstLetter){
                    "a" -> contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, email,"",false))
                    "b" -> contacts!!.add(Contact(R.drawable.b_letter,name, phoneNumber, email,"",false))
                    "c" -> contacts!!.add(Contact(R.drawable.c_letter,name, phoneNumber, email,"",false))
                    "d" -> contacts!!.add(Contact(R.drawable.d_letter,name, phoneNumber, email,"",false))
                    "e" -> contacts!!.add(Contact(R.drawable.e_letter,name, phoneNumber, email,"",false))
                    "f" -> contacts!!.add(Contact(R.drawable.f_letter,name, phoneNumber, email,"",false))
                    "g" -> contacts!!.add(Contact(R.drawable.g_letter,name, phoneNumber, email,"",false))
                    "h" -> contacts!!.add(Contact(R.drawable.h_letter,name, phoneNumber, email,"",false))
                    "i" -> contacts!!.add(Contact(R.drawable.i_letter,name, phoneNumber, email,"",false))
                    "j" -> contacts!!.add(Contact(R.drawable.j_letter,name, phoneNumber, email,"",false))
                    "k" -> contacts!!.add(Contact(R.drawable.k_letter,name, phoneNumber, email,"",false))
                    "l" -> contacts!!.add(Contact(R.drawable.l_letter,name, phoneNumber, email,"",false))
                    "m" -> contacts!!.add(Contact(R.drawable.m_letter,name, phoneNumber, email,"",false))
                    "n" -> contacts!!.add(Contact(R.drawable.n_letter,name, phoneNumber, email,"",false))
                    "ñ" -> contacts!!.add(Contact(R.drawable.n_letter,name, phoneNumber, email,"",false))
                    "o" -> contacts!!.add(Contact(R.drawable.o_letter,name, phoneNumber, email,"",false))
                    "p" -> contacts!!.add(Contact(R.drawable.p_letter,name, phoneNumber, email,"",false))
                    "q" -> contacts!!.add(Contact(R.drawable.q_letter,name, phoneNumber, email,"",false))
                    "r" -> contacts!!.add(Contact(R.drawable.r_letter,name, phoneNumber, email,"",false))
                    "s" -> contacts!!.add(Contact(R.drawable.s_letter,name, phoneNumber, email,"",false))
                    "t" -> contacts!!.add(Contact(R.drawable.t_letter,name, phoneNumber, email,"",false))
                    "u" -> contacts!!.add(Contact(R.drawable.u_letter,name, phoneNumber, email,"",false))
                    "v" -> contacts!!.add(Contact(R.drawable.v_letter,name, phoneNumber, email,"",false))
                    "w" -> contacts!!.add(Contact(R.drawable.w_letter,name, phoneNumber, email,"",false))
                    "x" -> contacts!!.add(Contact(R.drawable.x_letter,name, phoneNumber, email,"",false))
                    "y" -> contacts!!.add(Contact(R.drawable.y_letter,name, phoneNumber, email,"",false))
                    "z" -> contacts!!.add(Contact(R.drawable.z_letter,name, phoneNumber, email,"",false))
                    else -> contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, email,"",false))
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
                    var email:String = contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                    var firstLetter:String = name[0].toString().toLowerCase()
                    println(firstLetter)
                    //contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, "","",false))
                    when(firstLetter){
                        "a" -> contacts!!.add(Contact(R.drawable.a_letter,name, phoneNumber, email,"",false))
                        "b" -> contacts!!.add(Contact(R.drawable.b_letter,name, phoneNumber, email,"",false))
                        "c" -> contacts!!.add(Contact(R.drawable.c_letter,name, phoneNumber, email,"",false))
                        "d" -> contacts!!.add(Contact(R.drawable.d_letter,name, phoneNumber, email,"",false))
                        "e" -> contacts!!.add(Contact(R.drawable.e_letter,name, phoneNumber, email,"",false))
                        "f" -> contacts!!.add(Contact(R.drawable.f_letter,name, phoneNumber, email,"",false))
                        "g" -> contacts!!.add(Contact(R.drawable.g_letter,name, phoneNumber, email,"",false))
                        "h" -> contacts!!.add(Contact(R.drawable.h_letter,name, phoneNumber, email,"",false))
                        "i" -> contacts!!.add(Contact(R.drawable.i_letter,name, phoneNumber, email,"",false))
                        "j" -> contacts!!.add(Contact(R.drawable.j_letter,name, phoneNumber, email,"",false))
                        "k" -> contacts!!.add(Contact(R.drawable.k_letter,name, phoneNumber, email,"",false))
                        "l" -> contacts!!.add(Contact(R.drawable.l_letter,name, phoneNumber, email,"",false))
                        "m" -> contacts!!.add(Contact(R.drawable.m_letter,name, phoneNumber, email,"",false))
                        "n" -> contacts!!.add(Contact(R.drawable.n_letter,name, phoneNumber, email,"",false))
                        "ñ" -> contacts!!.add(Contact(R.drawable.n_letter,name, phoneNumber, email,"",false))
                        "o" -> contacts!!.add(Contact(R.drawable.o_letter,name, phoneNumber, email,"",false))
                        "p" -> contacts!!.add(Contact(R.drawable.p_letter,name, phoneNumber, email,"",false))
                        "q" -> contacts!!.add(Contact(R.drawable.q_letter,name, phoneNumber, email,"",false))
                        "r" -> contacts!!.add(Contact(R.drawable.r_letter,name, phoneNumber, email,"",false))
                        "s" -> contacts!!.add(Contact(R.drawable.s_letter,name, phoneNumber, email,"",false))
                        "t" -> contacts!!.add(Contact(R.drawable.t_letter,name, phoneNumber, email,"",false))
                        "u" -> contacts!!.add(Contact(R.drawable.u_letter,name, phoneNumber, email,"",false))
                        "v" -> contacts!!.add(Contact(R.drawable.v_letter,name, phoneNumber, email,"",false))
                        "w" -> contacts!!.add(Contact(R.drawable.w_letter,name, phoneNumber, email,"",false))
                        "x" -> contacts!!.add(Contact(R.drawable.x_letter,name, phoneNumber, email,"",false))
                        "y" -> contacts!!.add(Contact(R.drawable.y_letter,name, phoneNumber, email,"",false))
                        "z" -> contacts!!.add(Contact(R.drawable.z_letter,name, phoneNumber, email,"",false))
                        else -> contacts!!.add(Contact(R.drawable.z_letter,name, phoneNumber, email,"",false))
                    }
                }
                contact.close()
            }else{
                this.finish()
            }
        }
    }

}
