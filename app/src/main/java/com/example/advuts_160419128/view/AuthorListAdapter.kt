package com.example.advuts_160419128.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advuts_160419128.R
import com.example.advuts_160419128.model.Author
import com.example.advuts_160419128.util.loadImage
import kotlinx.android.synthetic.main.list_author_item.view.*

class AuthorListAdapter(val authorList:ArrayList<Author>) : RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder>() {
    class AuthorViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorListAdapter.AuthorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_author_item, parent, false)

        return AuthorListAdapter.AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorListAdapter.AuthorViewHolder, position: Int) {
        val author = authorList[position]

        with(holder.view){
            textAuthorName.text = author.name
            imageAuthor.loadImage(author.photourl, progressBarAuthorImage)
        }
    }

    override fun getItemCount() = authorList.size

    fun updateAuthorList(newAuthorList: ArrayList<Author>){
        authorList.clear()
        authorList.addAll(newAuthorList)
        notifyDataSetChanged()
    }
}