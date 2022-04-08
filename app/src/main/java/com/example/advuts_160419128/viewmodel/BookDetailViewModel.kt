package com.example.advuts_160419128.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advuts_160419128.model.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookDetailViewModel (application: Application) : AndroidViewModel(application){
    val booksLiveData = MutableLiveData<Book>()
    val booksLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "detailtag"

    private var queue: RequestQueue? = null

    fun fetch(bookID: String?){
        booksLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())

        val url = "http://192.168.1.7/anmp/"+ bookID + ".php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val bType = object : TypeToken<Book>(){}.type
                val result = Gson().fromJson<Book>(it, bType)
                booksLiveData.value = result
                Log.d("showbook", result.toString())
            },
            {
                loadingLiveData.value = false
                booksLoadErrorLiveData.value = true
                Log.d("errorbook", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}