package com.example.solidarchitecture.domain.repository


import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import wms.`fun`.molto.data.ApiClientInterface
import wms.`fun`.molto.domain.entities.LoginResponse

interface ILoginRepository {

    var apiInterface: ApiClientInterface
    fun loginUser(jsonObject: JsonObject) : Single<LoginResponse>

}