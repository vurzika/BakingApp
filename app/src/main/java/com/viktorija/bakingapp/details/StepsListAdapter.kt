package com.viktorija.bakingapp.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viktorija.bakingapp.data.model.Step
import com.viktorija.bakingapp.databinding.StepsListItemBinding

class StepsListAdapter : ListAdapter<Step, StepsListAdapter.ViewHolder>(
    StepDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)
    }

    // Create ViewHolder class that extends RecyclerView.ViewHolder.
    class ViewHolder(val binding: StepsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // add <data>, <variable> blocks in xml
        fun bind(item: Step) {
            binding.step = item
        }

        // same as static method in java
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StepsListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(
                    binding
                )
            }
        }
    }
}

// Implementing RecipeDiffCallback for ListAdapter to figure out what changed in the list.
class StepDiffCallback : DiffUtil.ItemCallback<Step>() {

    // DiffUtil uses these two methods to figure out how the list and items have changed.
    // code that tests whether the two passed-in SleepNight items, oldItem and newItem, are the same
    override fun areItemsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem.description == newItem.description
    }

    // code that checks whether oldItem and newItem contain the same data; that is, whether they are equal
    override fun areContentsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem == newItem
    }
}