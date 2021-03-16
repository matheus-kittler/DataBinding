package com.example.databindingtest.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databindingtest.service.IAddressAPI
import com.example.databindingtest.network.Network
import com.example.databindingtest.model.Address
import com.example.databindingtest.service.AddressService
import kotlinx.coroutines.*
import retrofit2.await


class MainActivityViewModel(
    private val service: AddressService = AddressService()
) : ViewModel() {

    val cep: MutableLiveData<String> = MutableLiveData<String>()
    val error: MutableLiveData<String> = MutableLiveData<String>()
    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
//    val isEnable: LiveData<Boolean> = Transformations.map(cep) {
//        return@map it?.length == 8
//    }

//    fun loadAddress() {
//        val cep: String = cep.value ?: return
//        loading.value = true
//        service.getAddress(cep).enqueue(object : Callback<Address> {
//            override fun onResponse(call: Call<Address>, response: Response<Address>) {
//                if (response.isSuccessful) {
//                    address.value = response.body()
//                } else {
//                    error.value = "Error"
//                }
//                loading.value = false
//            }
//
//            override fun onFailure(call: Call<Address>, t: Throwable) {
//                error.value = t.message
//                loading.value = false
//            }
//        })
//    }

    fun getAddres(): LiveData<Address> {
        return service.getAddress(cep.value ?: "")
    }
}