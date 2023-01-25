package com.example.android.networkconnect.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.networkconnect.databinding.CharacterItemListBinding
import com.example.android.networkconnect.model.CharacterProfile
import com.example.android.networkconnect.model.CharacterResponse

class CharacterAdapter(private var characterResponse: CharacterResponse) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {


    override fun getItemCount() = characterResponse.results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCharacter = characterResponse.results[position]
        holder.bind(currentCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    //class to bind the characters to the character item list layout
    class ViewHolder private constructor(private val binding: CharacterItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //binding the character
        fun bind(currentCharacter: CharacterProfile) {
            binding.characterName.text = currentCharacter.name
            //load the image using Glide
            Glide.with(binding.root.context)
                .load(currentCharacter.image)
                .into(binding.characterImage)

            binding.executePendingBindings()
        }

        //inflate the view
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterItemListBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

}


