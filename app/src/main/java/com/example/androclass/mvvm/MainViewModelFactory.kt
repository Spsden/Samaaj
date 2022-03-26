package com.example.androclass.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val contactsRepo: ContactsRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(contactsRepo) as T
    }
}

//WE Can't create a viewmodel directly, we need viewmodel factory which is this.