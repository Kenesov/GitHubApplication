package com.example.githubapplication.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapplication.R
import com.example.githubapplication.databinding.ItemRepoBinding
import com.example.githubapplication.models.data.GetUserRepositories

class RepoAdapter : ListAdapter<GetUserRepositories, RepoAdapter.RepoViewHolder>(diffCallBack) {

    inner class RepoViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val d = getItem(absoluteAdapterPosition)
            binding.apply {
                username.text = d.owner.login
                repoName.text = d.name
                Glide.with(ivProf)
                    .load(d.owner.avatar_url)
                    .into(ivProf)
            }
        }
    }

    private object diffCallBack: DiffUtil.ItemCallback<GetUserRepositories>() {
        override fun areItemsTheSame(
            oldItem: GetUserRepositories,
            newItem: GetUserRepositories
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: GetUserRepositories,
            newItem: GetUserRepositories
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemRepoBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_repo, parent, false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind()
    }
}