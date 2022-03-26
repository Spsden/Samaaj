package com.example.androclass.mvvm

import androidx.lifecycle.LiveData

class ContactsRepo(private val contactsDao: ContactsDao) {

    fun getContacts() : LiveData<List<Contacts>>{
        return contactsDao.getContacts()
    }

    suspend fun insertContacts(contacts: Contacts){
        contactsDao.insertContacts(contacts)
    }

    fun deleteContacts(id : Int){
        contactsDao.deleteContacts(id)
    }

    fun updateContacts(contacts: Contacts){
        contactsDao.updateContacts(contacts)

    }
}

///repo talks with the local database and external api too