package com.example.databindingtest.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindingtest.model.Address
import com.example.databindingtest.network.Network
import com.example.databindingtest.view.edit.EditAddressViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddressService(
    private val service: IAddressAPI = Network.getInstance(IAddressAPI::class.java)
    .build("https://viacep.com.br/ws/")) {
    fun getAddress(cep: String) : LiveData<Address> {
        val address = MutableLiveData<Address>()
        CoroutineScope(Dispatchers.IO).launch {
            address.postValue(service.getAddress(cep).body())
        }
        return address
    }
}