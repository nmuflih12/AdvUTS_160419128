package com.example.advuts_160419128.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advuts_160419128.R
import com.example.advuts_160419128.viewmodel.AuthorListViewModel
import kotlinx.android.synthetic.main.fragment_author_list.*

class AuthorListFragment : Fragment() {
    private lateinit var viewModelAuthor: AuthorListViewModel
    private val authorListAdapter = AuthorListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModelAuthor = ViewModelProvider(this).get(AuthorListViewModel::class.java)
        viewModelAuthor.refresh()

        recViewListAuthor.layoutManager = LinearLayoutManager(context)
        recViewListAuthor.adapter = authorListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewListAuthor.visibility = View.GONE
            textErrorAuthorList.visibility = View.GONE
            progressLoadListAuthor.visibility = View.VISIBLE
            viewModelAuthor.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModelAuthor.authorLiveData.observe(viewLifecycleOwner) {
            authorListAdapter.updateAuthorList(it)
        }

        viewModelAuthor.authorLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorAuthorList.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModelAuthor.loadingLiveData.observe(viewLifecycleOwner){
            if(it) {
                recViewListAuthor.visibility = View.GONE
                progressLoadListAuthor.visibility = View.VISIBLE
            }else{
                recViewListAuthor.visibility = View.VISIBLE
                progressLoadListAuthor.visibility = View.GONE
            }
        }
    }
}