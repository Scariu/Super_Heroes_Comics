package com.example.superheroescomics.visualpresentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.superheroescomics.R
import com.example.superheroescomics.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var superHeroId: String? = null
    lateinit var binding: FragmentDetailBinding
    private val viewModel: SuperHeroViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            superHeroId = it.getString("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        initComponents()
        return binding.root
    }

    private fun initComponents() {
        viewModel.superHeroDetailsViewModel(superHeroId.toString().toInt())

        viewModel.superHeroDetailLiveData(superHeroId.toString().toInt())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.imageViewImageDetail.load(it.imageUrl)
                    binding.tvNameDetail.text = it.name
                    binding.tvOriginDetail.text = it.origin
                    binding.tvYearDetail.text = it.year.toString()
                    binding.tvSuperPowerDetail.text = it.superPower
                    binding.tvColorDetail.text = it.color
                    if (!it.translate) {
                        binding.tvTranslateDetail.text = getString(R.string.translate_false)
                    } else {

                        binding.tvTranslateDetail.text = getString(R.string.translate_true)
                    }
                }
            }
    }
}
