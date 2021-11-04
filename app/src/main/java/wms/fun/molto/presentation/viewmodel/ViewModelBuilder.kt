package com.example.solidarchitecture.presentation.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.solidarchitecture.domain.repository.LoginRepository
import com.example.solidarchitecture.domain.usecase.LoginUseCase
import com.example.solidarchitecture.viewmodel.LoginViewModel
import wms.`fun`.molto.data.ApiClientInterface

class ViewModelBuilder {

    companion object{

        fun createLoginViewModel(owner: ViewModelStoreOwner): LoginViewModel {
            val apiClient = ApiClientInterface()
            val repository = LoginRepository(apiClient)
            val loginUseCase = LoginUseCase(repository)
            val viewModel = ViewModelProvider(owner).get(LoginViewModel::class.java)
            viewModel.setLoginUseCase(loginUseCase)
            return viewModel
        }

    }
}