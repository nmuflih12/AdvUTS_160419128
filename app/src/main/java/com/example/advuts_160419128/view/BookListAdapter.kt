package com.example.advuts_160419128.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advuts_160419128.R
import com.example.advuts_160419128.model.Book
import com.example.advuts_160419128.util.loadImage
import kotlinx.android.synthetic.main.list_book_item.view.*

class BookListAdapter(val bookList:ArrayList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>(){
    class BookViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_book_item, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]

        with(holder.view){
            textNamaBuku.text = book.title
            textAuthor.text = book.author
            textKategori.text = book.category

            buttonDetail.setOnClickListener{
                val action = book.id?.let{
                    BookListFragmentDirections.actionToBookDetail(it)
                }
                if(action != null){
                    Navigation.findNavController(it).navigate(action)
                }
            }
            imageBookPhoto.loadImage(book.photourl, progressBookPhoto)
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: ArrayList<Book>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }


}