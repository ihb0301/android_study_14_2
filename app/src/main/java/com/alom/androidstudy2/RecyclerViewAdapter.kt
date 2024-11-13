package com.alom.androidstudy2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alom.androidstudy2.databinding.MainRecyclerviewItemBinding
import com.bumptech.glide.Glide

class RecyclerViewAdapter(): ListAdapter<ItemData, RecyclerViewAdapter.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        return ViewHolder(MainRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<ItemData>() {
            override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val binding:MainRecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindItem(item: ItemData){
            binding.itemTitle.text = item.title
            binding.itemPrice.text = item.price
            binding.itemTime.text = item.time
            Glide.with(binding.root.context)
                .load(item.image_url)
                .into(binding.itemImage)
        }
    }
}