package com.example.databindingtest.service.backend

import com.example.databindingtest.util.Resource
import com.example.databindingtest.model.Address
import com.example.databindingtest.network.Network
import com.example.databindingtest.service.IAddressAPI
import com.example.databindingtest.util.NetworkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddressService(
    private val service: IAddressAPI
    ) : IAddressService {

    override suspend fun getAddress(cep: String): Flow<Resource<Address>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                call = service.getAddress(cep)
            ) {
                it
            }.build()
        }
    }



//    fun getAddress(cep: String) : LiveData<Address> {
//        val address = MutableLiveData<Address>()
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                address.postValue(service.getAddress(cep).body())
//            } catch (e: Throwable) {
//
//            }
//        }
//        return address
//    }
}