package com.example.advuts_160419128.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advuts_160419128.R
import com.example.advuts_160419128.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.fragment_list_book.*

class BookListFragment : Fragment() {
    private lateinit var viewModelBook: BookListViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModelBook = ViewModelProvider(this).get(BookListViewModel::class.java)
        viewModelBook.refresh()

        recViewListBook.layoutManager = LinearLayoutManager(context)
        recViewListBook.adapter = bookListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewListBook.visibility = View.GONE
            textError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModelBook.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModelBook.booksLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }

        viewModelBook.booksLoadErrorLiveData.observe(viewLifecycleOwner) {
            textError.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModelBook.loadingLiveData.observe(viewLifecycleOwner){
            if(it) {
                recViewListBook.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            }else{
                recViewListBook.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        }
    }
}