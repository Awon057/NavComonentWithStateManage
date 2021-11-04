package com.example.solidarchitecture.domain.repository


import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import wms.`fun`.molto.data.ApiClientInterface
import wms.`fun`.molto.domain.entities.LoginResponse

class LoginRepository(override var apiInterface: ApiClientInterface) : ILoginRepository {
    override fun loginUser(jsonObject: JsonObject): Single<LoginResponse> {
        return apiInterface.buildPostApiService().getLoginCall(jsonObject)
    }

}