package com.example.githubapplication.models.data

data class SearchUsersByUsernameResponceData(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<ItemsData>
)

