package com.example.androclass.mvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao() : ContactsDao

    companion object{
        private var INSTANCE : ContactsDatabase? = null
        fun getDatabase(context: Context) : ContactsDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,ContactsDatabase::class.java,"contacts_database").build()
                }
            }
            return INSTANCE!!
        }
    }
}