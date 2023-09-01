package com.example.superheroescomics.visualpresentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.superheroescomics.R
import com.example.superheroescomics.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        return binding.root
    }
}