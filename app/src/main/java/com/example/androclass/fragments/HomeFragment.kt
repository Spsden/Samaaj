package com.example.androclass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androclass.R
import com.example.androclass.adapters.RvAdapter
import com.example.androclass.databinding.FragmentHomeBinding
import com.example.androclass.mvvm.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() , RvAdapter.OnItemClickListener{

    private lateinit var binding: FragmentHomeBinding
    private lateinit var contactsList : ArrayList<Contacts>
   // private lateinit var recyclerView: RvAdapter()
  //  private lateinit var contactsList : ArrayList<Contacts>
    private lateinit var viewModel: MainViewModel
    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var repo: ContactsRepo


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
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
        //eturn inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createButton = binding.createFloat

        createButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCreateContacts())
        }

        viewModel.getContacts().observe(viewLifecycleOwner,{contacts ->
            contactsList = contacts as ArrayList<Contacts>

            homeRecyclerView = binding.mainRv
            homeRecyclerView.adapter = RvAdapter(contactsList,this)
            homeRecyclerView.layoutManager = LinearLayoutManager(context)
        })






//        binding.mainRv.layoutManager = LinearLayoutManager(context)
//        val adapter = RvAdapter(contactsList)














    }




    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onRvClick(position: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToViewContact(position))
    }
}