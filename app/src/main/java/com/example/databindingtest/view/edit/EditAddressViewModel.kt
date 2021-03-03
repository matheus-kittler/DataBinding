package com.example.databindingtest.view.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databindingtest.model.Address

class EditAddressViewModel : ViewModel() {

    var address: MutableLiveData<Address> = MutableLiveData<Address>()


}