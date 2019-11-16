package com.viktorija.bakingapp.details

import android.app.Application
import androidx.lifecycle.*
import com.viktorija.bakingapp.data.model.Recipe

class DetailsViewModel(recipe: Recipe, application: Application) : AndroidViewModel(application) {
    // (1) Inside the class definition, add LiveData for the selected recipe,
    // to expose that information to the detail view.
    private val _selectedRecipe = MutableLiveData<Recipe>()
    val selectedRecipe: LiveData<Recipe>
        get() = _selectedRecipe

    // (1) Create an init {} block and set the value of the selected Recipe
    // with the Recipe object from the constructor.
    init {
        _selectedRecipe.value = recipe
    }


    // Factory for constructing ViewModel as we need to pass application
    class Factory(private val recipe: Recipe, private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(recipe, application) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }
}