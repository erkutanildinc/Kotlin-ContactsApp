package com.example.mycontactsapp.ui.family

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.mycontactsapp.ContactDao
import com.example.mycontactsapp.ContactDatabase
import com.example.mycontactsapp.R
import com.example.mycontactsapp.UserDetail
import com.example.mycontactsapp.adapter.ContactAdapter
import com.example.mycontactsapp.databinding.FragmentFamilyBinding
import com.example.mycontactsapp.databinding.FragmentHomeBinding
import com.example.mycontactsapp.model.ContactModel

class FamilyFragment : Fragment() {

    private var _binding: FragmentFamilyBinding? = null
    private val binding get() = _binding!!
    private lateinit var familyListView : ListView
    private lateinit var db : ContactDao

    companion object {
        fun newInstance() = FamilyFragment()
    }

    private lateinit var viewModel: FamilyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFamilyBinding.inflate(inflater, container, false)
        val root: View = binding.root
        familyListView = binding.familyListView
        db = ContactDatabase.getInstance(this.requireContext())?.contactDao()!!
        return root
    }

    override fun onStart() {

        super.onStart()
        var list : MutableList<ContactModel> = db.getCategory("Family")
        var adapter = ContactAdapter(this.requireActivity(),list)
        familyListView.adapter = adapter

        familyListView.setOnItemClickListener { adapterView, view, position, l ->
            var intent = Intent(this.requireContext(), UserDetail::class.java)
            intent.putExtra("name",list.get(position).name)
            intent.putExtra("surname",list.get(position).surname)
            intent.putExtra("category",list.get(position).category)
            intent.putExtra("phone",list.get(position).phone)
            intent.putExtra("address",list.get(position).address)
            intent.putExtra("nid",list.get(position).nid)
            startActivity(intent)
        }

    }

}