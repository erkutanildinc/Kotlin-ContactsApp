package com.example.mycontactsapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mycontactsapp.databinding.ActivityContactAddBinding
import com.example.mycontactsapp.databinding.ActivityUserDetailBinding
import com.example.mycontactsapp.model.ContactModel
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class UserDetail : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    lateinit var deleteBtn : Button
    lateinit var userNameSurnameTextView : TextView
    lateinit var phoneTextView : TextView
    lateinit var addressTextView : TextView
    lateinit var categoryTextView : TextView
    lateinit var updateBtn : Button
    private lateinit var db : ContactDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userNameSurnameTextView = binding.nameSurnameTextView
        phoneTextView = binding.phoneNumberTextView
        addressTextView = binding.addressDetailTextView
        categoryTextView = binding.categoryTextView
        deleteBtn = binding.deleteBtn
        updateBtn = binding.updateBtn
        db = ContactDatabase.getInstance(this)?.contactDao()!!

        var name = intent.getStringExtra("name")
        var surname = intent.getStringExtra("surname")
        var phoneNum = intent.getStringExtra("phone")
        var address = intent.getStringExtra("address")
        var category = intent.getStringExtra("category")
        var nid = intent.getIntExtra("nid",0)

        userNameSurnameTextView.text = name + " " + surname
        phoneTextView.text = phoneNum
        addressTextView.text = address
        categoryTextView.text = category

        var contact = ContactModel(name = name, surname = surname, phone = phoneNum, address = address!!, category = category, nid = nid)
        deleteBtn.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Contact!")
            builder.setMessage("Are you sure you want to delete this person?")
            builder.setCancelable(true)

            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                db.delete(contact)
                finish()
                Toast.makeText(this, "Person Deleted", Toast.LENGTH_LONG).show()
            })

            builder.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
            })
            builder.show()
        }

        updateBtn.setOnClickListener {

            var updatedPhone = phoneTextView.text
            var updatedAdress = addressTextView.text
            var contactUpdated = ContactModel(name = name, surname = surname, phone = updatedPhone.toString(), address = updatedAdress.toString(), category = category, nid = nid)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Update Contact")
            builder.setMessage("Are you sure you want to update this person's information?")
            builder.setCancelable(true)

            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                db.update(contactUpdated)
                var sb = Snackbar.make(it, "Updated Succesfully", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).setDuration(2000)
                sb.show()
            })

            builder.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
            })
            builder.show()
        }
    }

}