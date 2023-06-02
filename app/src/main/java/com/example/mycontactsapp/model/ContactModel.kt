package com.example.mycontactsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("contact")
data class ContactModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="nid")
    val nid : Int?,

    val category : String?,
    val name : String?,
    val surname : String?,
    val phone : String?,
    val address : String
)
