package com.alvarenga.agenda.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alvarenga.agenda.Adapters.ContactAdapter
import com.alvarenga.agenda.Contacts.Contact
import com.alvarenga.agenda.R

@SuppressLint("ValidFragment", "StaticFieldLeak")
class ContactFragment: Fragment() {
    private var recyclerV:RecyclerView? = null

    private var adapter: ContactAdapter? = null

    companion object {
        @JvmStatic val fragment = ContactFragment()
        @JvmStatic val args = Bundle()
        @JvmStatic var contact: ArrayList<Contact>? = null

        fun newInstance(contacts2: ArrayList<Contact>):ContactFragment{
            args.putParcelableArrayList("KEY1",contacts2)
            fragment.arguments = args
            return fragment
        }
        fun getInstance(): ContactFragment{
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contact = it.getParcelableArrayList("KEY1")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerV = getView()!!.findViewById(R.id.contactsFrag)
        val lmanager = LinearLayoutManager(this.context)
        recyclerV!!.layoutManager = lmanager
        adapter = ContactAdapter(contact!!,this.context!!,false)
        recyclerV!!.adapter = adapter
    }

    fun update(contacts:ArrayList<Contact>, position:Int, fav:Boolean){
        for(i in contact!!.indices){
            if(contact!![i].name == contacts[position].name){
                contact!![i].isFav = fav
            }
        }
        adapter = ContactAdapter(contact!!,this.context!!, false)
        recyclerV!!.adapter = adapter
    }


}