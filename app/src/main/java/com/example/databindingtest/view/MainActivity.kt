package com.example.databindingtest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.databindingtest.R
import com.example.databindingtest.databinding.ActivityMainBinding
import com.example.databindingtest.model.Address

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        binding.viewModel = mainViewModel

        binding.searchCepButton.setOnClickListener {
            mainViewModel.getAddres().observe(this, Observer { binding.address = it })
        }

//        val addressObserver = Observer<Address> {
//            binding.address = it
//        }
//
//        mainViewModel.address.observe(this, addressObserver)

        mainViewModel.error.observe(this, Observer { binding.searchCepEdt.error = it })
    }
}