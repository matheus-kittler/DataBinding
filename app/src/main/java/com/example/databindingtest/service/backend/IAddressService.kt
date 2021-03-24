package com.example.databindingtest.service.backend

import com.example.databindingtest.model.Address
import com.example.databindingtest.service.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IAddressService {
    suspend fun getAddress(cep: String): Flow<Resource<Address>>
}