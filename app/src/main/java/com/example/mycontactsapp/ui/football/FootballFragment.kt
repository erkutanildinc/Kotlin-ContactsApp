package com.example.mycontactsapp.ui.football

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
import com.example.mycontactsapp.databinding.FragmentFootballBinding
import com.example.mycontactsapp.model.ContactModel
import com.example.mycontactsapp.ui.family.FamilyFragment
import com.example.mycontactsapp.ui.family.FamilyViewModel

class FootballFragment : Fragment() {

    private var _binding: FragmentFootballBinding? = null
    private val binding get() = _binding!!
    private lateinit var footballListView : ListView
    private lateinit var db : ContactDao

    companion object {
        fun newInstance() = FootballFragment()
    }

    private lateinit var viewModel: FootballViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFootballBinding.inflate(inflater, container, false)
        val root: View = binding.root
        footballListView = binding.footballListView
        db = ContactDatabase.getInstance(this.requireContext())?.contactDao()!!
        return root
    }

    override fun onStart() {

        super.onStart()
        var list : MutableList<ContactModel> = db.getCategory("Football")
        var adapter = ContactAdapter(this.requireActivity(),list)
        footballListView.adapter = adapter

        footballListView.setOnItemClickListener { adapterView, view, position, l ->
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