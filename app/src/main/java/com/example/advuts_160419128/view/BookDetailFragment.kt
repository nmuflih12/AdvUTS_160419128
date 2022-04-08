package com.example.advuts_160419128.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.advuts_160419128.R
import com.example.advuts_160419128.util.loadImage
import com.example.advuts_160419128.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment(){
    private lateinit var viewModel: BookDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let{
            val bookID = BookDetailFragmentArgs.fromBundle(requireArguments()).bookID
            viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
            viewModel.fetch(bookID)

            observeViewModel()
        }
    }

    private fun observeViewModel(){
        viewModel.booksLiveData.observe(viewLifecycleOwner){
            //val book = viewModel.booksLiveData.value
            val book = it
            Log.d("hasil", book.toString())
            book?.let{
                Log.d("hasil", it.description.toString())
                textTitleDetailnew.text = it.title
                textAuthorDetailnew.setText(it.author)
                textDescDetailnew.setText(it.description)
                textAuthorDetailnew.setText(it.author)
                imageBookDetailnew.loadImage(it.photourl, progressBookDetail)
            }
        }
    }
}