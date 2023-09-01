package com.example.superheroescomics.visualpresentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroescomics.data.local.SuperHeroEntity
import com.example.superheroescomics.databinding.ItemListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    lateinit var itemBinding: ItemListBinding
    private val listOfSuperHeroes = mutableListOf<SuperHeroEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        itemBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context))
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        val superHero = listOfSuperHeroes[position]
        holder.bind(superHero)
    }

    override fun getItemCount(): Int {
        return listOfSuperHeroes.size
    }

    fun setData(cellPhones: List<SuperHeroEntity>) {
        this.listOfSuperHeroes.clear()
        this.listOfSuperHeroes.addAll(cellPhones)
        notifyDataSetChanged()
    }

    class ListViewHolder(private val itemBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(superHero: SuperHeroEntity) {
            itemBinding.imageViewItem.load(superHero.imageUrl)
            itemBinding.tvNameItem.text = superHero.name
        }
    }
}