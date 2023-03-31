package com.example.githubapplication.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapplication.R
import com.example.githubapplication.databinding.ItemSearchByRepoBinding
import com.example.githubapplication.models.data.ItemsRepoData

class SearchRepoAdapter: ListAdapter<ItemsRepoData, SearchRepoAdapter.RepoViewHolder>(diffCallBack) {

    inner class RepoViewHolder(private val binding: ItemSearchByRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val d = getItem(absoluteAdapterPosition)
            binding.apply {
                Glide.with(ivProf)
                    .load(d.owner.avatar_url)
                    .into(ivProf)
                appName.text = d.name
                username.text = d.owner.login
                tvDesc.text = d.description
                tvStar.text = d.stargazers_count.toString()
                language.text = d.language

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemSearchByRepoBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_search_by_repo, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallBack : DiffUtil.ItemCallback<ItemsRepoData>() {
        override fun areItemsTheSame(oldItem: ItemsRepoData, newItem: ItemsRepoData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemsRepoData, newItem: ItemsRepoData): Boolean {
            return oldItem == newItem
        }

    }
}
