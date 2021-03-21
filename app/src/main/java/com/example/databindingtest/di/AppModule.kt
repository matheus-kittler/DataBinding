package com.example.databindingtest.di


import com.example.databindingtest.dispatcher.AppDispatchers
import com.example.databindingtest.dispatcher.IAppDispatchers
import com.example.databindingtest.network.module.RetrofitModule
import com.example.databindingtest.service.IAddressAPI
import com.example.databindingtest.service.backend.AddressService
import com.example.databindingtest.service.backend.IAddressService
import com.example.databindingtest.view.main.MainActivityViewModel
import mezzari.torres.lucas.network.source.Network
import mezzari.torres.lucas.network.source.module.client.LogModule
import mezzari.torres.lucas.network.source.module.retrofit.GsonConverterModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val genericModule = module {
    single<IAppDispatchers> { AppDispatchers() }
}

val networkModule = module {
    single {
        Network.initialize(
                retrofitLevelModules = arrayListOf(GsonConverterModule(), RetrofitModule()),
                okHttpClientLevelModule = arrayListOf(LogModule())
        )

        return@single Network
    }

    single { get<Network>().build<IAddressAPI>("https://viacep.com.br/ws/") }


    single<IAddressService> { AddressService(get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
}

val appModule = listOf(genericModule, networkModule, viewModelModule)