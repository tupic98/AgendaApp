package com.alvarenga.agenda.Fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alvarenga.agenda.Adapters.ContactAdapter
import com.alvarenga.agenda.Adapters.FavoriteAdapter
import com.alvarenga.agenda.Contacts.Contact
import com.alvarenga.agenda.R

@SuppressLint("ValidFragment", "StaticFieldLeak")
class FavoriteFragment:Fragment(){
    private var contact:ArrayList<Contact>? = null
    private var favContact:ArrayList<Contact>? = null
    private var recyclerV:RecyclerView? = null
    private var adapter:FavoriteAdapter? = null
    private lateinit var lmanager:GridLayoutManager

    companion object {
        @JvmStatic val fragment = FavoriteFragment()
        @JvmStatic val arg = Bundle()

        fun newInstance(contacts2:ArrayList<Contact>): FavoriteFragment{
            arg.putParcelableArrayList("KEY2", contacts2)
            fragment.arguments = arg
            return fragment
        }
        fun getInstance(): FavoriteFragment{
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contact = it.getParcelableArrayList("KEY2")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favContact = favContactPreparation(contact)

        recyclerV = getView()!!.findViewById(R.id.favFrag)
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            lmanager = GridLayoutManager(this.context,3)
        }else{
            lmanager = GridLayoutManager(this.context,5)

        }

        recyclerV!!.layoutManager = lmanager
        adapter = FavoriteAdapter(favContact!!,this.context!!,true)
        recyclerV!!.adapter = adapter
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if(newConfig != null){
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                lmanager = GridLayoutManager(this.context,3)
            }else{
                lmanager = GridLayoutManager(this.context,5)

            }
            recyclerV!!.layoutManager = lmanager
            adapter = FavoriteAdapter(favContact!!,this.context!!,true)
            recyclerV!!.adapter = adapter
        }
    }

    fun favContactPreparation(contacts:ArrayList<Contact>?):ArrayList<Contact>{
        val favContacts = ArrayList<Contact>()
        for(i in contacts!!.indices) if(contacts[i].isFav){
            favContacts.add(Contact(contacts[i].photo,contacts[i].name,contacts[i].phone, contacts[i].email,contacts[i].other,contacts[i].isFav))
        }
        return favContacts
    }

    fun update(contacts:ArrayList<Contact>){
        favContact = favContactPreparation(contacts)
        adapter = FavoriteAdapter(favContact!!,this.context!!,true)
        recyclerV!!.adapter = adapter
    }


}