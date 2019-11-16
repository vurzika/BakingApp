package com.viktorija.bakingapp.recipes

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.viktorija.bakingapp.R
import com.viktorija.bakingapp.databinding.RecipesListFragmentBinding

class RecipesListFragment : Fragment() {

    private lateinit var binding: RecipesListFragmentBinding

    // viewModel setup
    private val viewModel: RecipesListViewModel by viewModels {
        RecipesListViewModel.Factory(
            requireNotNull(this.activity).application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflating the layout
        // for data binding the xml layout should be in <layout> tag
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.recipes_list_fragment,
            container,
            false
        )

        // Telling RecyclerView about the Adapter
        val adapter = RecipesListAdapter(
            RecipeClickListener(
                clickListener = {
                    findNavController().navigate(
                        RecipesListFragmentDirections.actionRecipesListFragmentToDetailsFragment(it)
                    )
                }
            )
        )

        binding.recipesList.adapter = adapter

        // adding divider
        val divider = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
        binding.recipesList.addItemDecoration(divider)


        // Creating an observer on the recipes variable to observe changes and get data into the adapter
        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Set toolbar title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_my_recipes)

        return binding.root
    }

}