package com.viktorija.bakingapp.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viktorija.bakingapp.data.model.Ingredient
import com.viktorija.bakingapp.databinding.IngredientListItemBinding

class IngredientsListAdapter :
    ListAdapter<Ingredient, IngredientsListAdapter.ViewHolder>(
        IngredientDiffCallback()
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
    class ViewHolder(val binding: IngredientListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // add <data>, <variable> blocks in xml,
        // add app:text="@{recipe.title}" to text view - replacing reference to string text resource
        // android:onClick="@{() -> clickListener.onClick(recipe)}" - replacing reference to ClickListener
        fun bind(item: Ingredient) {

            binding.ingredient = item

        }

        // same as static method in java
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(
                    binding
                )
            }
        }
    }
}

// Implementing RecipeDiffCallback for ListAdapter to figure out what changed in the list.
class IngredientDiffCallback : DiffUtil.ItemCallback<Ingredient>() {

    // DiffUtil uses these two methods to figure out how the list and items have changed.
    // code that tests whether the two passed-in SleepNight items, oldItem and newItem, are the same
    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.name == newItem.name
    }

    // code that checks whether oldItem and newItem contain the same data; that is, whether they are equal
    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }
}