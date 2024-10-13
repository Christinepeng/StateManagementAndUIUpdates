package com.example.statemanagementanduiupdates
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    var items = mutableStateListOf<String>()
        private set

    fun addItem(item: String) {
        items.add(item)
    }

    fun removeItem(index: Int) {
        if (index in items.indices) {
            items.removeAt(index)
        }
    }

    fun updateItem(index: Int, newItem: String) {
        if (index in items.indices) {
            items[index] = newItem
        }
    }
}
