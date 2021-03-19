package com.example.databindingtest.service


import com.example.databindingtest.model.Address
import com.example.databindingtest.network.Network
import com.example.databindingtest.util.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@Network.Route("https://viacep.com.br/ws/")
interface IAddressAPI {
    @GET("{cep}/json/")
     suspend fun getAddress (@Path("cep") cep: String) : Deferred<ApiResponse<Address>>
}