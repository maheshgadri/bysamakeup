package com.example.makeu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.makeu.model.MakeupItem
import com.example.makeu.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: ItemRepository)
    : ViewModel() {

    fun getAllItem(): LiveData<List<MakeupItem>> {
        return repository.getItem()
    }

    fun makeApiCall() {
        repository.makeApiCall("maybelline")
    }

}