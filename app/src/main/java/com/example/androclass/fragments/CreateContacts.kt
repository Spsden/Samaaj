package com.example.androclass.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androclass.databinding.FragmentCreateContactsBinding
import com.example.androclass.mvvm.*


class CreateContacts : Fragment() {

    private lateinit var binding: FragmentCreateContactsBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var repo: ContactsRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
//    init{
//        val dao = ContactsDatabase.getDatabase(context!!).contactsDao()
//        repo = ContactsRepo(dao)
//        viewModel = MainViewModel(repo)
//
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    val dao = ContactsDatabase.getDatabase(context!!).contactsDao()
    repo = ContactsRepo(dao)
    viewModel = ViewModelProvider(this,MainViewModelFactory(repo)).get(MainViewModel::class.java)


        binding = FragmentCreateContactsBinding.inflate(layoutInflater)




        return binding.root
        //return inflater.inflate(R.layout.fragment_create_contacts, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.saveContactFloat.setOnClickListener {
            createContact(it)
        }
    }

    private fun createContact(it:View?) {

        if(binding.firstName.text.isNullOrBlank() || binding.mobNum.text.isNullOrBlank()){
            Toast.makeText(context,"Please enter atleast number and name",Toast.LENGTH_LONG).show()
        } else {

            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val mobileNum = binding.mobNum.text.toString()
            val email = binding.email.text.toString()
            val additionalInfo = binding.additionalInfo.text.toString()

            val contact = Contacts(

                firstName = firstName,
                secondName = lastName,
                additionalInfo = additionalInfo,
                email = email,
                id = null,
                mobileNumber = mobileNum
            )

            viewModel.insertContacts(contact)

            Toast.makeText(activity?.applicationContext,"Contact created successfully",Toast.LENGTH_LONG).show()

            findNavController().popBackStack()


        }
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateContacts().apply {
                arguments = Bundle().apply {

                }
            }
    }
}