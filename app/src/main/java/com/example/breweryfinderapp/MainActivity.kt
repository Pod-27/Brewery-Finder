package com.example.breweryfinderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private final lateinit var brewerySearch: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                getBreweryNames(brewerySearch)
                Log.d("Brewery","success! $response")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d("Brewery", "success! $json")
            }

        }]
    }

    data class Brewery(
        val id: Int,
        val name: String,
        val street: String?,
        val city: String?,
        val state: String?,
        val postal_code: String?,
        val country: String?,
        val phone: String?,
        val website_url: String?
    )
    private fun searchBreweries(query: String): List<Brewery> {
        return searchBreweries(query)
    }

    private fun getBreweryNames(searchView: SearchView){
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let { searchBreweries(it) }?.forEach { brewery ->
//                    println(brewery.name)
//                }
                val breweries = query?.let { searchBreweries(it) }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val breweries = query?.let { searchBreweries(it) }
                return false
            }
        })
    }


}