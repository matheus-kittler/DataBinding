package com.example.databindingtest.view.main


import androidx.lifecycle.*
import com.example.databindingtest.dispatcher.IAppDispatchers
import com.example.databindingtest.model.Address
import com.example.databindingtest.service.backend.IAddressService
import com.example.databindingtest.service.resource.Resource
import com.example.databindingtest.service.resource.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivityViewModel(
    private val service: IAddressService,
    private val dispatchers: IAppDispatchers
) : ViewModel() {

    private val addressResource: MutableLiveData<Resource<Address>> =
        MutableLiveData<Resource<Address>>()
    val cep: MutableLiveData<String> = MutableLiveData<String>()

    val serviceLoad: LiveData<Boolean> = Transformations.map(addressResource) {
        return@map it?.status == Status.LOADING
    }// TODO mostra que o serviço está carregando

    val error: LiveData<String> = Transformations.map(addressResource) {
//        (if (it.message != null || it.status == Status.SUCCESS) {
//            return@map it?.message
//        } else if (it.message == null || it.status == Status.ERROR) {
//            return@map it?.status.toString()
//        } else {
//
//        }).toString()

        if (it.message == null && it.status == Status.ERROR) {
            return@map "Digite o CEP corretamente"
        } else {
            return@map it?.message
        }

    }// TODO mostra que o serviço deu erro

    val address: LiveData<Address> = Transformations.map(addressResource) {
        return@map it?.data
    }// TODO retorna o endereço

//    val isEnable: LiveData<Boolean> = Transformations.map(cep) {
//        return@map it?.length == 8
//    }

    fun getAddres() {
        cep.value?.let { cep ->
            viewModelScope.launch(dispatchers.io) {
                service.getAddress(cep).collect {
                    addressResource.postValue(it)
                }
            }
        }
    }
}