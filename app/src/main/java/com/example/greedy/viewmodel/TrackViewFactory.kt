package com.example.greedy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TrackViewFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TracksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TracksViewModel() as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}