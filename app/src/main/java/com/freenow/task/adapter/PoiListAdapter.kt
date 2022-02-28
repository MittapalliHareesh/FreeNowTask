package com.freenow.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freenow.task.databinding.PoiItemBinding
import com.freenow.task.model.PoiItem

class PoiListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<PoiItem, PoiListAdapter.ViewHolder>(PoiDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val poiItemBinding =
            PoiItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(poiItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder(private val binding: PoiItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(poiItem: PoiItem, listener: OnItemClickListener) {
            binding.clickListener = listener
            binding.poiItem = poiItem
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onImageClick(poiItem: PoiItem)
    }
}

class PoiDiffCallback : DiffUtil.ItemCallback<PoiItem>() {
    /**
     * This method will be called to check whether old and new items are the same.
     * @param oldItem Indicates model class
     * @param newItem Indicates model class
     */
    override fun areContentsTheSame(oldItem: PoiItem, newItem: PoiItem): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * This method will be called to check whether old and new items represent the same item visually.
     * This will only be called when areItemsTheSame() returns true.
     * @param oldItem Indicates model class
     * @param newItem Indicates model class
     */
    override fun areItemsTheSame(oldItem: PoiItem, newItem: PoiItem): Boolean {
        return oldItem == newItem
    }
}