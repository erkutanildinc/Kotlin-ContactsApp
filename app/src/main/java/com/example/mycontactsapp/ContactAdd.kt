package com.example.mycontactsapp

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.mycontactsapp.adapter.SpinnerGroupAdapter
import com.example.mycontactsapp.databinding.ActivityContactAddBinding
import com.example.mycontactsapp.model.ContactModel
import com.google.android.material.snackbar.Snackbar

class ContactAdd : AppCompatActivity() {

    private lateinit var binding: ActivityContactAddBinding
    private lateinit var spinner : Spinner
    private lateinit var groupAdapter : SpinnerGroupAdapter
    lateinit var nameTextView : EditText
    lateinit var surnameTextView : EditText
    lateinit var phoneTextView : EditText
    lateinit var addressTextView : EditText
    lateinit var addContactButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        groupAdapter = SpinnerGroupAdapter(applicationContext,SpinnerGroupData().getGroupItems())
        spinner = binding.groupSpinner
        spinner.adapter = groupAdapter

        nameTextView = binding.contactNameEditText
        surnameTextView = binding.contactSurnameEditText
        phoneTextView = binding.contactPhoneEditText
        addressTextView = binding.contactAddressEditText
        addContactButton = binding.addContactButton

        var db: ContactDao = ContactDatabase.getInstance(this)?.contactDao()!!

        addContactButton.setOnClickListener {
            var name = nameTextView.text.toString()
            var surname = surnameTextView.text.toString()
            var phone = phoneTextView.text.toString()
            var address = addressTextView.text.toString()
            var itemInSpinner = spinner.selectedItem.toString()
            var groupList = SpinnerGroupData().getGroupItems()
            var group = groupList.get(itemInSpinner.toInt()).group

            var contact = ContactModel(
                name = name,
                surname = surname,
                phone = phone,
                address = address,
                category = group,
                nid = null
            )
            db.insert(contact)
            var sb = Snackbar.make(it, "Added Succesfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).setDuration(3000)
            sb.show()

            val handler = Handler()
            handler.postDelayed(Runnable {
                finish()
            }, 1000)
        }
    }

}