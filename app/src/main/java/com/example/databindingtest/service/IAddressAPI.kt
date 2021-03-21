package com.example.databindingtest.service


import com.example.databindingtest.model.Address
import com.example.databindingtest.network.Network
import com.example.databindingtest.util.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IAddressAPI {
    @GET("{cep}/json/")
    fun getAddress(@Path("cep") cep: String): Deferred<ApiResponse<Address>>
}