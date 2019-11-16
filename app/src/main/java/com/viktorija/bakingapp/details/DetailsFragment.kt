package com.viktorija.bakingapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.viktorija.bakingapp.data.model.Recipe
import com.viktorija.bakingapp.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    lateinit var recipe : Recipe

    // view model setup for the selected id
    val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Factory(recipe, requireNotNull(activity).application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        // Get the selectedRecipe from the fragment arguments with DetailFragmentArgs
        recipe = DetailsFragmentArgs.fromBundle(arguments!!).selectedRecipe

        // Telling RecyclerView about the IngredientsListAdapter
        val ingredientsListAdapter = IngredientsListAdapter()
        binding.ingredientsList.adapter = ingredientsListAdapter

        // Telling RecyclerView about the IngredientsListAdapter
        val stepsListAdapter = StepsListAdapter()
        binding.stepsList.adapter = stepsListAdapter


        viewModel.selectedRecipe.observe(this, Observer {
            // if we get the data then set text
            it?.let {
                binding.servingsNumber.text = it.servings.toString()
                ingredientsListAdapter.submitList(it.ingredients)
                stepsListAdapter.submitList(it.steps)

                // Set toolbar title
                (activity as AppCompatActivity).supportActionBar?.title = it.title

            }
        })
        return binding.root
    }
}