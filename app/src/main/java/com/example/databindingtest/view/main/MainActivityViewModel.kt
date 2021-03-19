package com.example.databindingtest.view.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databindingtest.dispatcher.IAppDispatchers
import com.example.databindingtest.model.Address
import com.example.databindingtest.service.backend.IAddressService
import com.example.databindingtest.util.Resource
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
}