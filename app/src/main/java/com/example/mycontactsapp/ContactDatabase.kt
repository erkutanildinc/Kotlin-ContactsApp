package com.example.mycontactsapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycontactsapp.model.ContactModel

@Database(entities = [ContactModel::class], version = 2)
abstract class ContactDatabase : RoomDatabase(){
    abstract fun contactDao() : ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase? {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contactDatabase"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                INSTANCE=instance
                instance
            }
        }
    }
}

