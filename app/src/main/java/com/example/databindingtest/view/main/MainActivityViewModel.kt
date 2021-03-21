package com.example.databindingtest.view.main


import androidx.lifecycle.*
import com.example.databindingtest.dispatcher.IAppDispatchers
import com.example.databindingtest.model.Address
import com.example.databindingtest.service.backend.IAddressService
import com.example.databindingtest.util.Resource
import com.example.databindingtest.util.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivityViewModel(
    private val service: IAddressService,
    private val dispatchers: IAppDispatchers
) : ViewModel() {

    val cep: MutableLiveData<String> = MutableLiveData<String>()
    val addressResource: MutableLiveData<Resource<Address>> = MutableLiveData<Resource<Address>>()
    val error: MutableLiveData<String> = MutableLiveData<String>()
    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = Transformations.map(addressResource) {
        return@map it?.status == Status.LOADING

    }// TODO mostra que o serviço está carregando

    val isError: LiveData<String> = Transformations.map(addressResource) {
        return@map it?.message
    }// TODO mostra que o serviço deu erro

    val address: LiveData<Address> = Transformations.map(addressResource) {
        return@map it?.data
    }// TODO retorna o endereço
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

    fun getAddres() {
        cep.value?.let { cep ->
            viewModelScope.launch(dispatchers.io) {
                service.getAddress(cep).collect {
                    addressResource.postValue(it)
                }
            }
        }
    }

//    fun getAddres(): LiveData<Address> {
//        return service.getAddress(cep.value ?: "")
//    } TODO coroutines normal
}