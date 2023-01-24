package com.example.android.networkconnect.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.networkconnect.MainActivity
import com.example.android.networkconnect.databinding.CharacterItemListBinding
import com.example.android.networkconnect.model.CharacterProfile
import com.example.android.networkconnect.model.CharacterResponse

class CharacterAdapter(
    mainActivity: MainActivity,
    characterProfiles: CharacterResponse
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCharacter = data[position]
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

    //check if an item are the same or if the content are the same
    private val characterDiffCallback = object : DiffUtil.ItemCallback<CharacterProfile>() {
        override fun areItemsTheSame(oldItem: CharacterProfile, newItem: CharacterProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterProfile, newItem: CharacterProfile): Boolean {
            return newItem == oldItem
        }
    }

    //calculate the difference between two lists in a background thread
    private val differ = AsyncListDiffer(this, characterDiffCallback)
    var data: List<CharacterProfile>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


}


