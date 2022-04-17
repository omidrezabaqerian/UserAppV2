package ir.omidrezabagherian.userapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.omidrezabagherian.userapp.data.ServiceLocator

class SearchViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)){
            val userRepository  = ServiceLocator.getUserRepository()
            return SearchViewModel(userRepository)as T
        }
        throw IllegalArgumentException("ViewModel Class Not Fount.")
    }
}