package com.viktorija.bakingapp.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viktorija.bakingapp.data.model.Recipe
import com.viktorija.bakingapp.databinding.RecipeListItemBinding


// Modifying the constructor of the NotesListAdapter class to receive a
// val clickListener: NoteClickListener - to handle clicks on the note items
// Adding SleepNightDiffCallback() as a parameter to the constructor.
// The ListAdapter will use this to figure out what changed in the list.
class RecipesListAdapter(private val clickListener: RecipeClickListener) :
    ListAdapter<Recipe, RecipesListAdapter.ViewHolder>(
        RecipeDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item, clickListener)
    }

    // Create ViewHolder class that extends RecyclerView.ViewHolder.
    class ViewHolder(val binding: RecipeListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // add <data>, <variable> blocks in xml,
        // add app:text="@{recipe.title}" to text view - replacing reference to string text resource
        // android:onClick="@{() -> clickListener.onClick(recipe)}" - replacing reference to ClickListener
        fun bind(item: Recipe, clickListener: RecipeClickListener) {

            binding.recipe = item
            binding.clickListener = clickListener
        }

        // same as static method in java
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

// New class -  RecipeClickListener to handle clicks
class RecipeClickListener(
    val clickListener: (recipe: Recipe) -> Unit
) {
    fun onClick(recipe: Recipe) = clickListener(recipe)

}

// Implementing RecipeDiffCallback for ListAdapter to figure out what changed in the list.
class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    // DiffUtil uses these two methods to figure out how the list and items have changed.
    // code that tests whether the two passed-in SleepNight items, oldItem and newItem, are the same
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    // code that checks whether oldItem and newItem contain the same data; that is, whether they are equal
    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}
