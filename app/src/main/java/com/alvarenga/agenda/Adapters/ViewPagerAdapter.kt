package com.alvarenga.agenda.Adapters


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alvarenga.agenda.Fragments.ContactFragment
import com.alvarenga.agenda.Fragments.FavoriteFragment
import com.alvarenga.agenda.R

class ViewPagerAdapter(private val context:Context, fm: FragmentManager, private val bundle:Bundle):FragmentPagerAdapter(fm){
    private val Contacts: ContactFragment = ContactFragment.newInstance(bundle.getParcelableArrayList("KEY"))
    private val FavContacts: FavoriteFragment = FavoriteFragment.newInstance(bundle.getParcelableArrayList("KEY"))

    override fun getItem(position: Int): Fragment = when(position){
        0 -> Contacts
        1 -> FavContacts
        else -> Contacts
    }

    override fun getCount(): Int {
       return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> context.getString(R.string.tab1)
            1 -> context.getString(R.string.tab2)
            else -> null
        }
    }

}