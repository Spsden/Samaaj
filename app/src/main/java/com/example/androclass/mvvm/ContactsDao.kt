package com.example.androclass.mvvm

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ContactsDao {


    @Query("SELECT * FROM contacts")
    fun getContacts() : LiveData<List<Contacts>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: Contacts)

    @Query("Delete FROM contacts Where id =:id")
    fun deleteContacts(id:Int)

    @Update
    fun updateContacts(contacts: Contacts)
}