package com.example.solidarchitecture.domain.usecase


import com.example.solidarchitecture.domain.repository.ILoginRepository
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import wms.`fun`.molto.domain.entities.LoginResponse

class LoginUseCase(override var iLoginRepository: ILoginRepository) : ILoginUseCase {

    override fun userLogin(jsonObject: JsonObject): Single<LoginResponse> {
        return iLoginRepository.loginUser(jsonObject)
    }

}