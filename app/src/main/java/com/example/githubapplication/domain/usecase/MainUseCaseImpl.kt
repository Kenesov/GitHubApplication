package com.example.githubapplication.domain.usecase

import com.example.githubapplication.domain.repository.MainRepository
import com.example.githubapplication.models.data.*
import kotlinx.coroutines.flow.Flow

class MainUseCaseImpl(private val repo: MainRepository) : MainUseCase {

    override suspend fun loginApi(): Flow<ResultData<String>> = repo.loginApi()

    override suspend fun getUserProfileInfo(): Flow<ResultData<GetUserProfileInfoResponceData>> = repo.getUserProfileInfo()

    override suspend fun searchUsersByUsername(userName: String): Flow<ResultData<List<ItemsData>>> = repo.searchUsersByUsername(userName)

    override suspend fun getUserRepositories(): Flow<ResultData<List<GetUserRepositories>>> = repo.getUserRepositories()

    override suspend fun searchRepoByRepoName(repoName: String): Flow<ResultData<List<ItemsRepoData>>> = repo.searchRepoByRepoName(repoName)
}