package com.example.databindingtest


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IAddressAPI {
    @GET("{cep}/json/")
    fun getAddress (@Path("cep") cep: String) : Call<Address>
}