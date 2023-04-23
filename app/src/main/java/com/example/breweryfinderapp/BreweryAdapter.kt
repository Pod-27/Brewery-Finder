package com.example.breweryfinderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BreweryAdapter(private val breweryList: MutableList<Triple <String, String, String>>) : RecyclerView.Adapter<BreweryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val breweryName: TextView
        val breweryStreet: TextView
        val breweryCity: TextView

        init {
            breweryName = view.findViewById(R.id.brewery_name)
            breweryStreet = view.findViewById(R.id.street)
            breweryCity = view.findViewById(R.id.city)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.brewery_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = breweryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.breweryName.text = breweryList[position].first
        holder.breweryStreet.text = breweryList[position].second
        holder.breweryCity.text = breweryList[position].third

    }



}
