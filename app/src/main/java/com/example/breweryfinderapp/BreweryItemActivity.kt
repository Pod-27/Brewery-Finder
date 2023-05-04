package com.example.breweryfinderapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BreweryItemActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breweryitem)

//        val bundle : Bundle?= intent.extras
//        val name = bundle?.getString("name")
//        val street = bundle?.getString("street")
//        val city = bundle?.getString("city")
//        val state = bundle?.getString("state")
//        val phone = bundle?.getString("phone")
//        val website = bundle?.getString("website")
        val extras = intent.extras
        var name = ""
        var street = ""
        var city = ""
        var state = ""
        var phone = ""
        var website = ""
        if (extras != null) {
            name = extras.getString("name").toString()
            street = extras.getString("street").toString()
            city = extras.getString("city").toString()
            state = extras.getString("state").toString()
            phone = extras.getString("phone").toString()
            website = extras.getString("website").toString()
        }
        else {
            name = "null"
            street = "null"
            city = "null"
            state = "null"
            phone = "null"
            website = "null"
        }

        val nameView : TextView = findViewById(R.id.name)
        val streetView : TextView = findViewById(R.id.street)
        val cityView : TextView = findViewById(R.id.city)
        val stateView : TextView = findViewById(R.id.state)
        val phoneView : TextView = findViewById(R.id.phone)
        val websiteView : TextView = findViewById(R.id.website)

        nameView.text = name
        streetView.text = street
        cityView.text = city
        stateView.text = state
        phoneView.text = phone
        websiteView.text = website
    }
}