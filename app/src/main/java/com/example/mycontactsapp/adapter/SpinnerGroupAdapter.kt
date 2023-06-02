package com.example.mycontactsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mycontactsapp.R
import com.example.mycontactsapp.model.SpinnerGroup

class SpinnerGroupAdapter(var context : Context, var list: MutableList<SpinnerGroup>): BaseAdapter(){

    override fun getCount(): Int {
        if(list != null){
            return list.size
        }
        else{
            return 0
        }
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var rootView : View = LayoutInflater.from(context).inflate(R.layout.item_group,p2,false)
        var groupText : TextView = rootView.findViewById(R.id.itemGroupTitleTextView)
        var groupIcon : ImageView = rootView.findViewById(R.id.itemGroupIconImage)

        groupText.text = list.get(p0).group
        groupIcon.setImageResource(list.get(p0).groupImg)
        return rootView
    }
}