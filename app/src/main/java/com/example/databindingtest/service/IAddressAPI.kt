package com.example.databindingtest.service


import com.example.databindingtest.model.Address
import com.example.databindingtest.service.resource.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface IAddressAPI {
    @GET("{cep}/json/")
    fun getAddress(@Path("cep") cep: String): Deferred<ApiResponse<Address>>
}