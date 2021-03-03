package com.example.databindingtest.view.edit

import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.databindingtest.R
import com.example.databindingtest.databinding.ActivityEditAddressBinding
import com.example.databindingtest.view.main.MainActivityViewModel

class EditAddressActivity : AppCompatActivity() {

    private val viewModel: EditAddressViewModel by viewModels()
    lateinit var address: com.example.databindingtest.model.Address


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityEditAddressBinding>(this, R.layout.activity_edit_address)

        val a: com.example.databindingtest.model.Address = intent.getSerializableExtra("address") as com.example.databindingtest.model.Address

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.address = a
    }
}