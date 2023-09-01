package com.example.superheroescomics.visualpresentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.superheroescomics.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    private val viewModel: SuperHeroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        viewModel.getSuperHeroesViewModel()
        val adapter = ListAdapter()
        binding.recyclerViewList.adapter = adapter
        viewModel.superHeroesLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}