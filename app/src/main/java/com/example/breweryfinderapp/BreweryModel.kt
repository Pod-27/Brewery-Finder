package com.example.breweryfinderapp

class BreweryModel(private var breweryName: String, private var breweryStreet: String, private var breweryCity: String) {
    // creating getter and setter methods.
    fun getBreweryName(): String {
        return breweryName
    }

    fun setBreweryName(breweryName: String) {
        this.breweryName = breweryName
    }

    fun getBreweryStreet(): String {
        return breweryStreet
    }

    fun setBreweryStreet(breweryStreet: String) {
        this.breweryStreet = breweryStreet
    }

    fun getBreweryCity(): String {
        return breweryCity
    }

    fun setBreweryCity(breweryCity: String) {
        this.breweryCity = breweryCity
    }
}