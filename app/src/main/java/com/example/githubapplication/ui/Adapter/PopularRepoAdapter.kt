package com.example.githubapplication.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapplication.R
import com.example.githubapplication.databinding.ItemPopularRepoBinding
import com.example.githubapplication.models.data.GetUserRepositories

class PopularRepoAdapter: ListAdapter<GetUserRepositories, PopularRepoAdapter.PopularRepoViewHolder>(diffCallBack) {

    inner class PopularRepoViewHolder(private val binding: ItemPopularRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val d = getItem(absoluteAdapterPosition)
            binding.apply {
                Glide.with(ivProf)
                    .load(d.owner.avatar_url)
                    .into(ivProf)
                username.text = d.owner.login
                appName.text = d.name
                tvStar.text = d.stargazers_count.toString()
                language.text = d.language
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularRepoViewHolder {
        return PopularRepoViewHolder(
            ItemPopularRepoBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_popular_repo, parent, false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: PopularRepoViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallBack: DiffUtil.ItemCallback<GetUserRepositories>() {
        override fun areItemsTheSame(oldItem: GetUserRepositories, newItem: GetUserRepositories): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GetUserRepositories, newItem: GetUserRepositories): Boolean {
            return oldItem == newItem
        }

    }
}