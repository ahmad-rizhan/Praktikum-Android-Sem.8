package com.ahmadrizhan.praktikumsemester8.ui.jenisbarang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrizhan.praktikumsemester8.model.Jenisbarang
import com.ahmadrizhan.praktikumsemester8.model.JenisbarangData
import com.ahmadrizhan.praktikumsemester8.model.JenisbarangResponse
import com.ahmadrizhan.praktikumsemester8.network.Api
import kotlinx.coroutines.launch

class JenisbarangViewModel : ViewModel() {
    private val _response = MutableLiveData<Jenisbarang>()
    val createResponse = MutableLiveData<retrofit2.Response<JenisbarangResponse>>()
    val response: LiveData<Jenisbarang>
        get() = _response

    init {
        setResponse()
    }

    fun create(jenisbarangData: JenisbarangData) {
        viewModelScope.launch {
            val response = Api.retrofitService.create(jenisbarangData)
            createResponse.value = response
        }
    }
    private fun setResponse() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getJenisbarang()
                _response.value = listResult
            } catch (e: Exception) {
                _response.value = null
            }
        }
    }
}