package com.example.mycontactsapp

import androidx.room.*
import com.example.mycontactsapp.model.ContactModel

@Dao
interface ContactDao {

    @Insert
    fun insert(contact : ContactModel)

    @Query("select * from contact order by nid desc limit 10")
    fun getLastTen() : MutableList<ContactModel>

    @Query("select * from contact where category =:title")
    fun getCategory(title :String) : MutableList<ContactModel>

    @Query("select * from contact where name like:name || '%'")
    fun search(name :String) : MutableList<ContactModel>

    @Delete()
    fun delete(contact: ContactModel)

    @Update
    fun update(contact : ContactModel)
}