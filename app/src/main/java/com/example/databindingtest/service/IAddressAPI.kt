package com.example.databindingtest.service


import com.example.databindingtest.model.Address
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface IAddressAPI {
    @GET("{cep}/json/")
     suspend fun getAddress (@Path("cep") cep: String) : Response<Address>
}