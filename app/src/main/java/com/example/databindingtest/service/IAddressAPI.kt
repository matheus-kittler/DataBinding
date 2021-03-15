package com.example.databindingtest.service


import com.example.databindingtest.model.Address
import com.example.databindingtest.util.ApiResponse
import kotlinx.coroutines.Deferred
import mezzari.torres.lucas.network.annotation.Route
import retrofit2.http.GET
import retrofit2.http.Path

@Route("https://viacep.com.br/ws/")
interface IAddressAPI {
    @GET("{cep}/json/")
    fun getAddress(
        @Path("cep") cep: String
    ): Deferred<ApiResponse<Address>>
}