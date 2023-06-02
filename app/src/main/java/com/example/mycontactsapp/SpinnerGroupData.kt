package com.example.mycontactsapp

import com.example.mycontactsapp.model.SpinnerGroup

class SpinnerGroupData {

    public fun getGroupItems() : MutableList<SpinnerGroup>{
        var list = mutableListOf<SpinnerGroup>()

        var spinnerItem1 = SpinnerGroup("Family",R.drawable.family)
        var spinnerItem2 = SpinnerGroup("School",R.drawable.school)
        var spinnerItem3 = SpinnerGroup("Business",R.drawable.job)
        var spinnerItem4 = SpinnerGroup("Friend",R.drawable.friends)
        var spinnerItem5 = SpinnerGroup("Game",R.drawable.game)
        var spinnerItem6 = SpinnerGroup("Football",R.drawable.footbaal)

        list.add(spinnerItem1)
        list.add(spinnerItem2)
        list.add(spinnerItem3)
        list.add(spinnerItem4)
        list.add(spinnerItem5)
        list.add(spinnerItem6)
        return list
    }
}