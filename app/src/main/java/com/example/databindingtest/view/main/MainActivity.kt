package com.example.databindingtest.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.databindingtest.R
import com.example.databindingtest.databinding.ActivityMainBinding
import com.example.databindingtest.service.resource.Status
import com.example.databindingtest.util.showAlert
import com.example.databindingtest.view.edit.EditAddressActivity

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainActivityViewModel by viewModel()
    var address: com.example.databindingtest.model.Address =
        com.example.databindingtest.model.Address("", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        binding.viewModel = mainViewModel

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

        mainViewModel.error.observe(this, Observer {
            binding.searchCepEdt.error = it
        })

        mainViewModel.serviceLoad.observe(this, Observer {
            binding.pbLoading.visibility = View.VISIBLE
        })
    }
}