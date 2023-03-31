package com.example.githubapplication.domain.repository

import android.util.Log
import com.example.githubapplication.models.data.ResultData
import com.example.githubapplication.models.local.LocalStorage
import com.example.githubapplication.retrafit.GitHubApi
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(private val api: GitHubApi): MainRepository {

    override suspend fun loginApi() = flow {
        val responce =
            api.getAccessToken(
                "8f3cf5f09bd0c93a0528",
                "5447af3efb5afba3751aa6a0025e97affcf1a538",
                LocalStorage().code
            )

        Log.e("TTTT", responce.body()!!.accessToken)
        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!.accessToken))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    override suspend fun getUserProfileInfo() = flow {
        val responce =
            api.getUserProfileInfo()


        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    override suspend fun searchUsersByUsername(userName: String) = flow {
        val responce =
            api.searchUsersByUsername(userName)

        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!.items))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    override suspend fun getUserRepositories() = flow {
        val responce =
            api.getUserRepositories()

        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    override suspend fun searchRepoByRepoName(repoName: String) = flow {
        val responce =
            api.searchRepoByRepoName(repoName)

        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!.items))
        }
        else if (responce.body()!!.incomplete_results) {

        }
        else {
            emit(ResultData.Message(responce.message()))
        }
    }
}