package com.example.githubapplication.domain.usecase

import com.example.githubapplication.models.data.*
import kotlinx.coroutines.flow.Flow

interface MainUseCase {

    suspend fun loginApi(): Flow<ResultData<String>>

    suspend fun getUserProfileInfo(): Flow<ResultData<GetUserProfileInfoResponceData>>

    suspend fun searchUsersByUsername(userName: String): Flow<ResultData<List<ItemsData>>>

    suspend fun getUserRepositories(): Flow<ResultData<List<GetUserRepositories>>>

    suspend fun searchRepoByRepoName(repoName: String): Flow<ResultData<List<ItemsRepoData>>>
}