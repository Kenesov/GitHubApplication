package com.example.githubapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapplication.domain.usecase.MainUseCase
import com.example.githubapplication.models.data.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val useCase: MainUseCase): ViewModel(){

    val getSuccessFlow = MutableSharedFlow<String>()
    val getMessageFlow = MutableSharedFlow<String>()
    val getErrorFlow = MutableSharedFlow<Throwable>()

    val getUserPrInfoSuccessFlow = MutableSharedFlow<GetUserProfileInfoResponceData>()
    val getUserRepositoriesFlow = MutableSharedFlow<List<GetUserRepositories>>()
    val getSearchByUserFlow = MutableSharedFlow<List<ItemsData>>()
    val searchReposByRepoNameFlow = MutableSharedFlow<List<ItemsRepoData>>()

    suspend fun isSuccess() {
        useCase.loginApi().onEach {
            when(it) {
                is ResultData.Success -> {
                    getSuccessFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getUserProfileInfo() {
        useCase.getUserProfileInfo().onEach {
            when(it) {
                is ResultData.Success -> {
                    getUserPrInfoSuccessFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun searchUsersByUserName(userName: String) {
        useCase.searchUsersByUsername(userName).onEach {
            when (it) {
                is ResultData.Success -> {
                    getSearchByUserFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getUserRepositories() {
        useCase.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun searchRepoByRepoName(repoName: String) {
        useCase.searchRepoByRepoName(repoName).onEach {
            when (it) {
                is ResultData.Success -> {
                    searchReposByRepoNameFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}