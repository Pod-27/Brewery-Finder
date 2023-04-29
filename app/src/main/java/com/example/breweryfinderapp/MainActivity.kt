package com.example.breweryfinderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log



import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.widget.SearchView


import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {


    private lateinit var breweryList: MutableList<Triple<String,String,String>>
    private lateinit var rvBreweries: RecyclerView

    private final lateinit var brewerySearch: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        breweryList = arrayListOf()
        rvBreweries = findViewById(R.id.brewery_list)


        brewerySearch = findViewById(R.id.search_bar)

        getBrewery()
    }

    private fun getBrewery() {
        val client = AsyncHttpClient()

        client["https://api.openbrewerydb.org/v1/breweries", object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Brewery Error", "error")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d("Brewery", "success! $json")

                val array = json?.jsonArray

                if (array != null) {
                    for(i in 0 until array.length()) {
                        val name = array.getJSONObject(i).getString("name")
                        val street = array.getJSONObject(i).getString("street")
                        val city = array.getJSONObject(i).getString("city")

                        breweryList.add(Triple(name, street, city))
                    }
                    val adapter = BreweryAdapter(breweryList)
                    rvBreweries.adapter = adapter
                    rvBreweries.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvBreweries.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))

                }
            }

        }]
    }
}