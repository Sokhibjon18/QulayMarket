package uz.triples.qulaymarket.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MainFragmentViewModelFactory(private val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainFragmentViewModel::class.java)){
            return MainFragmentViewModel(context = context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}