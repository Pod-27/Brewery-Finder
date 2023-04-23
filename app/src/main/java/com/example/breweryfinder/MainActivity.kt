package com.example.breweryfinder

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.breweryfinder.ui.theme.BreweryFinderTheme
import okhttp3.Headers

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            }

        }]
    }
}

