package com.viktorija.bakingapp.recipes

import android.app.Application
import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.lifecycle.*
import com.viktorija.bakingapp.data.model.Recipe
import com.viktorija.bakingapp.data.network.RecipeApi
import kotlinx.coroutines.Dispatchers


class RecipesListViewModel internal constructor(application: Application) :
    AndroidViewModel(application) {

    // Getting data from internet
    // Getting recipes from RecipeApi
    val recipes: LiveData<List<Recipe>> = liveData(Dispatchers.IO) {
        try {
            val recipes = RecipeApi.retrofitService.getRecipes().await()
            emit(recipes)
        } catch (e: Exception) {
            //handle the network error:
            //_eventNetworkError.value = "Failure: ${e.message}"
            Log.e(TAG, "Error")
        }
    }

    // Factory for constructing ViewModel as we need to pass application
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipesListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RecipesListViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}