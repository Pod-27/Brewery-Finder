package com.example.breweryfinderapp


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import java.util.*


class MainActivity : AppCompatActivity() {


    //private lateinit var breweryList: MutableList<Triple<String,String,String>>
    private lateinit var breweryList: ArrayList<BreweryModel>
    //private lateinit var filteredList: MutableList<Triple<String,String,String>>
    private lateinit var rvBreweries: RecyclerView

    private lateinit var adapter: BreweryAdapter

    private final lateinit var brewerySearch: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        breweryList = arrayListOf()
        rvBreweries = findViewById(R.id.brewery_list)

//        brewerySearch = findViewById(R.id.search_bar)

        getBrewery()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.activi
        inflater.inflate(R.menu.search_menu, menu)

        // below line is to get our menu item.
        val searchItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
//        val searchView = searchItem.actionView

        val searchView = searchItem.actionView as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist = ArrayList<BreweryModel>()
        // running a for loop to compare elements.
        for (item in breweryList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getBreweryName().lowercase(Locale.ROOT).contains(text.lowercase(Locale.getDefault()))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item as BreweryModel)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            //val adapter = BreweryAdapter(filteredlist)
            adapter.filterList(filteredlist)
        }
    }

    private fun getBrewery() {
        val client = AsyncHttpClient()

        client["https://api.openbrewerydb.org/v1/breweries?per_page=200", object : JsonHttpResponseHandler() {
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

                        breweryList.add(BreweryModel(name, street, city))
                    }
                    adapter = BreweryAdapter(breweryList)
                    rvBreweries.adapter = adapter
                    rvBreweries.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvBreweries.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))

                }
            }

        }]
    }
}