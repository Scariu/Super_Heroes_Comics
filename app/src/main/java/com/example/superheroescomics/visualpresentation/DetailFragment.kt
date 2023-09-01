package com.example.superheroescomics.visualpresentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.superheroescomics.databinding.FragmentDetailBinding

private const val ARG_PARAM1 = "param1"

class DetailFragment : Fragment() {
    private var param1: String? = null
    lateinit var binding: FragmentDetailBinding
    private val viewModel: SuperHeroViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        return binding.root
    }
}