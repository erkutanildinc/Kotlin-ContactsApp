package com.example.mycontactsapp.ui.business

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
import com.example.mycontactsapp.databinding.FragmentBusinessBinding
import com.example.mycontactsapp.databinding.FragmentFamilyBinding
import com.example.mycontactsapp.model.ContactModel
import com.example.mycontactsapp.ui.family.FamilyFragment
import com.example.mycontactsapp.ui.family.FamilyViewModel

class BusinessFragment : Fragment() {

    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!
    private lateinit var businessListView : ListView
    private lateinit var db : ContactDao

    companion object {
        fun newInstance() = BusinessFragment()
    }

    private lateinit var viewModel: FamilyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        val root: View = binding.root
        businessListView = binding.businessListView
        db = ContactDatabase.getInstance(this.requireContext())?.contactDao()!!
        return root
    }

    override fun onStart() {
        super.onStart()
        var list : MutableList<ContactModel> = db.getCategory("Business")
        var adapter = ContactAdapter(this.requireActivity(),list)
        businessListView.adapter = adapter

        businessListView.setOnItemClickListener { adapterView, view, position, l ->
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