package com.example.mycontactsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mycontactsapp.ContactDao
import com.example.mycontactsapp.ContactDatabase
import com.example.mycontactsapp.MainActivity
import com.example.mycontactsapp.UserDetail
import com.example.mycontactsapp.adapter.ContactAdapter
import com.example.mycontactsapp.databinding.FragmentHomeBinding
import com.example.mycontactsapp.model.ContactModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeListView : ListView
    private lateinit var db : ContactDao
    lateinit var searchEditText : EditText
    lateinit var searchBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        homeListView = binding.homeListView
        searchBtn = binding.searchButton
        searchEditText = binding.searchEditText
        db = ContactDatabase.getInstance(this.requireContext())?.contactDao()!!
        return root
    }

    override fun onStart() {

        super.onStart()
        searchEditText.text.clear()
        var list : MutableList<ContactModel> = db.getLastTen()
        var adapter = ContactAdapter(this.requireActivity(),list)
        homeListView.adapter = adapter

        homeListView.setOnItemClickListener { adapterView, view, position, l ->
            var intent = Intent(this.requireContext(),UserDetail::class.java)
            intent.putExtra("name",list.get(position).name)
            intent.putExtra("surname",list.get(position).surname)
            intent.putExtra("category",list.get(position).category)
            intent.putExtra("phone",list.get(position).phone)
            intent.putExtra("address",list.get(position).address)
            intent.putExtra("nid",list.get(position).nid)
            startActivity(intent)
        }

        searchBtn.setOnClickListener {
            var searchedText = searchEditText.text.toString()
            var searchList : MutableList<ContactModel> = db.search(searchedText)
            var searchAdapter = ContactAdapter(this.requireActivity(),searchList)
            homeListView.adapter = searchAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}