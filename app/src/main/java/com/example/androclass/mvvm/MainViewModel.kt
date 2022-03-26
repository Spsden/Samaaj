package com.example.androclass.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val contactsRepo: ContactsRepo) : ViewModel() {

    fun getContacts() : LiveData<List<Contacts>>{
        return contactsRepo.getContacts()


    }

    fun insertContacts(contacts: Contacts){
        viewModelScope.launch ( Dispatchers.IO ){
            contactsRepo.insertContacts(contacts)
        }

    }

    fun deleteContacts(id : Int){
        contactsRepo.deleteContacts(id)
    }

    fun updateContacts(contacts: Contacts){
        contactsRepo.updateContacts(contacts)

    }
}