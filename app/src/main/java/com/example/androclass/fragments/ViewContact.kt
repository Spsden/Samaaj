package com.example.androclass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.androclass.R
import com.example.androclass.databinding.FragmentViewContactBinding
import com.example.androclass.mvvm.ContactsDatabase
import com.example.androclass.mvvm.ContactsRepo
import com.example.androclass.mvvm.MainViewModel
import com.example.androclass.mvvm.MainViewModelFactory


class ViewContact : Fragment() {

    private lateinit var binding: FragmentViewContactBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var repo: ContactsRepo
    private val args : ViewContactArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dao = ContactsDatabase.getDatabase(context!!).contactsDao()
        repo = ContactsRepo(dao)
        viewModel = ViewModelProvider(this, MainViewModelFactory(repo)).get(MainViewModel::class.java)




        binding = FragmentViewContactBinding.inflate(layoutInflater)

        return binding.root
       // return inflater.inflate(R.layout.fragment_view_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position : Int = args.position

        viewModel.getContacts().observe(this) {
            binding.contactName = it[position].firstName + it[position].secondName
            binding.mobileNum.text = it[position].mobileNumber
            binding.email.text = it[position].email
            binding.additionalInfo.text = it[position].additionalInfo
            binding.textViewCollapsing.text = it[position].firstName[0].toString()
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewContact().apply {
                arguments = Bundle().apply {

                }
            }
    }
}