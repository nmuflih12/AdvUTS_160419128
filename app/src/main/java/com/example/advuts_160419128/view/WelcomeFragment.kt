package com.example.advuts_160419128.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.advuts_160419128.R
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            val nama = WelcomeFragmentArgs.fromBundle(requireArguments()).name
            Log.d("shownama", nama)
            textWelcomeName.text = nama
        }

        buttonWelcomeBack.setOnClickListener{
            val action  = WelcomeFragmentDirections.actionWelcomeToHome()
            Navigation.findNavController(it).navigate(action)
        }
    }
}