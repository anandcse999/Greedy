package com.example.greedy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TagFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TagFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}