package com.example.androclass.mvvm

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts")
data class Contacts(

    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val firstName : String,
    val secondName : String,
    val mobileNumber : String,
    val additionalInfo : String,
    val email: String,

)