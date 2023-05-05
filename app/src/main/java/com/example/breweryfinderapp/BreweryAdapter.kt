package com.example.breweryfinderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BreweryAdapter(private var breweryList: ArrayList<BreweryModel>) : RecyclerView.Adapter<BreweryAdapter.ViewHolder>() {

    private lateinit var myListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        myListener = listener
    }

    class ViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val breweryName: TextView
        val breweryStreet: TextView
        val breweryCity: TextView

        init {
            breweryName = view.findViewById(R.id.brewery_name)
            breweryStreet = view.findViewById(R.id.street)
            breweryCity = view.findViewById(R.id.city)

            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun filterList(filterList: ArrayList<BreweryModel>) {
        // below line is to add our filtered
        // list in our course array list.
        breweryList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)

            .inflate(R.layout.newbreweryitem, parent, false)


        return ViewHolder(view, myListener)
    }

    override fun getItemCount() = breweryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.breweryName.text = breweryList[position]
//        holder.breweryStreet.text = breweryList[position].second
//        holder.breweryCity.text = breweryList[position].third
        val model: BreweryModel = breweryList[position]
        holder.breweryName.text = model.getBreweryName()
        holder.breweryStreet.text = model.getBreweryStreet()
        holder.breweryCity.text = model.getBreweryCity()
    }

    init {
        this.breweryList = breweryList
    }
}
