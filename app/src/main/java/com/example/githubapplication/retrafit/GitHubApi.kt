package com.example.githubapplication.retrafit

import com.example.githubapplication.models.data.*
import retrofit2.Response
import retrofit2.http.*

interface GitHubApi {
    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String
    ): Response<GetAccessTokenResponceData>

    @GET("/user")
    suspend fun getUserProfileInfo() : Response<GetUserProfileInfoResponceData>

    @GET("/search/users?q")
    suspend fun searchUsersByUsername(@Query("q") username: String) : Response<SearchUsersByUsernameResponceData>

    @GET("/user/repos")
    suspend fun getUserRepositories() : Response<List<GetUserRepositories>>

    @GET("/search/repositories?q")
    suspend fun searchRepoByRepoName(@Query("q") repoName: String) : Response<SearchRepoByRepoNameResponceData>
}