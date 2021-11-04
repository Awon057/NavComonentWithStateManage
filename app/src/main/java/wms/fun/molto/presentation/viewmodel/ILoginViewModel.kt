package com.example.solidarchitecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solidarchitecture.domain.usecase.ILoginUseCase

import com.google.gson.JsonObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import wms.`fun`.molto.domain.entities.LoginResponse

interface ILoginViewModel {

    var loginResponse: MutableLiveData<LoginResponse>
    var isLoading : MutableLiveData<Boolean>
    var isError: MutableLiveData<String>

    fun getLoginApiCall(jsonObject: JsonObject)
}


class LoginViewModel : ViewModel() , ILoginViewModel{

    open lateinit var iLoginUseCase : ILoginUseCase

    override var loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    override var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    override var isError: MutableLiveData<String> = MutableLiveData()


    open fun setLoginUseCase( useCase: ILoginUseCase){
        this.iLoginUseCase = useCase
    }

    override fun getLoginApiCall(jsonObject: JsonObject) {
        isLoading.value = true
        iLoginUseCase.userLogin(jsonObject).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::loginResponse , this::loginError)
    }

    private fun loginResponse(response: LoginResponse){
        response.let {
            isLoading.value = false
            loginResponse.postValue(response)
        }
    }

    private fun loginError(t : Throwable){
        isLoading.value = false
        isError.postValue("${t.printStackTrace()}")
    }

}


