package com.example.databindingtest.service


import com.example.databindingtest.model.Address
import com.example.databindingtest.network.Network
import mezzari.torres.lucas.network.annotation.Route
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@Route("https://viacep.com.br/ws/")
interface IAddressAPI {
    @GET("{cep}/json/")
     suspend fun getAddress (@Path("cep") cep: String) : Response<Address>
}