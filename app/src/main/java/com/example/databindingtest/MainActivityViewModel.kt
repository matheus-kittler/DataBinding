package com.example.databindingtest

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel(
    private val service: IAddressAPI = Network.getInstance(IAddressAPI::class.java)
        .build("https://viacep.com.br/ws/")
) : ViewModel() {

    var address : MutableLiveData<Address> = MutableLiveData<Address>(Address("", "", "", "", "", ""))
    val cep: MutableLiveData<String> = MutableLiveData<String>()
    val error: MutableLiveData<String> = MutableLiveData<String>()
    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isEnable: LiveData<Boolean> = Transformations.map(cep) {
        return@map it?.length == 8
    }

    fun loadAddress() {
        val cep: String = cep.value ?: return
        loading.value = true
        service.getAddress(cep).enqueue(object : Callback<Address> {
            override fun onResponse(call: Call<Address>, response: Response<Address>) {
                if (response.isSuccessful) {
                    address.value = response.body()
                } else {
                    error.value = "Error"
                }
                loading.value = false
            }

            override fun onFailure(call: Call<Address>, t: Throwable) {
                error.value = t.message
                loading.value = false
            }
        })
    }
}