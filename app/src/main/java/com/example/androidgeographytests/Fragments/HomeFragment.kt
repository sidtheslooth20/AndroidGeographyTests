package com.example.androidgeographytests.Fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.androidgeographytests.R
import kotlinx.android.synthetic.main.fragment_home2.*
import kotlinx.android.synthetic.main.fragment_home2.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home2, container, false)

        view.image_1.setOnClickListener {
            image_1.setColorFilter(Color.RED)
        }
        // Inflate the layout for this fragment
        return view
    }
}