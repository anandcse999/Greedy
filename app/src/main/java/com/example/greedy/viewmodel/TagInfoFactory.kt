package com.example.greedy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TagInfoFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TagInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TagInfoViewModel() as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}