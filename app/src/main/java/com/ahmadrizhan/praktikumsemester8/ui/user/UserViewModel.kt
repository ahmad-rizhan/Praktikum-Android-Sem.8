package com.ahmadrizhan.praktikumsemester8.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrizhan.praktikumsemester8.model.User
import com.ahmadrizhan.praktikumsemester8.network.Api
import kotlinx.coroutines.launch

class UserViewModel :ViewModel() {
    val response = MutableLiveData<User>()

    fun getUsers() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getUsers()
                response.value = listResult
            } catch (e: Exception) {
                response.value = null
            }
        }
    }
}