package com.example.mycontactsapp.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mycontactsapp.R
import com.example.mycontactsapp.model.ContactModel

class ContactAdapter(private val context : Activity, private val list : MutableList<ContactModel>) : ArrayAdapter<ContactModel>(context,
R.layout.listview_item,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rootView = context.layoutInflater.inflate(R.layout.listview_item,null,true)

        val userInfoTextView = rootView.findViewById<TextView>(R.id.userInfoTextView)
        val userPhone = rootView.findViewById<TextView>(R.id.userPhone)
        val contact = list.get(position)

        userInfoTextView.text = contact.name + " " + contact.surname + "-" + contact.category
        userPhone.text = contact.phone
        return  rootView
    }
}