package com.example.databindingtest.view.main

import android.content.Intent
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.databindingtest.R
import com.example.databindingtest.databinding.ActivityMainBinding
import com.example.databindingtest.view.edit.EditAddressActivity
import com.example.databindingtest.view.edit.EditAddressViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainActivityViewModel by viewModels()
    var address: com.example.databindingtest.model.Address = com.example.databindingtest.model.Address("", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        binding.viewModel = mainViewModel

//        binding.searchCepButton.setOnClickListener {
//            mainViewModel.getAddres().observe(this, Observer { binding.address = it })
//        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, EditAddressActivity::class.java)
            address.logradouro = binding.rua.text.toString()
            address.complemento = binding.complemento.text.toString()
            address.bairro = binding.bairro.text.toString()
            address.localidade = binding.cidade.text.toString()
            address.uf = binding.estado.text.toString()
            intent.putExtra("address", address)
            startActivity(intent)
        }

//        val addressObserver = Observer<Address> {
//            binding.address = it
//        }
//
//        mainViewModel.address.observe(this, addressObserver)

        mainViewModel.error.observe(this, Observer { binding.searchCepEdt.error = it })
    }
}