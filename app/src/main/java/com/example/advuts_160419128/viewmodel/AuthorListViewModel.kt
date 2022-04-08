package com.example.advuts_160419128.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advuts_160419128.model.Author
import com.example.advuts_160419128.model.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AuthorListViewModel(application: Application) : AndroidViewModel(application) {
    val authorLiveData = MutableLiveData<ArrayList<Author>>()
    val authorLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadingLiveData.value = true
        authorLoadErrorLiveData.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.1.7/anmp/author_uts.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val aType = object : TypeToken<ArrayList<Author>>() {}.type
                val result = Gson().fromJson<ArrayList<Author>>(it, aType)
                authorLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                authorLoadErrorLiveData.value = true
                Log.d("errorvolley", it.toString())
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