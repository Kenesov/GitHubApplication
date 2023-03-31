package com.example.githubapplication.ui.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapplication.R
import com.example.githubapplication.databinding.FragmentRepositoriesBinding
import com.example.githubapplication.presentation.MainViewModel
import com.example.githubapplication.ui.Adapter.RepoAdapter
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.view.clicks

class RepositoriesFragment: Fragment(R.layout.fragment_repositories) {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter: RepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initData()
        initObservers()
        initListeners()



        binding.apply {
            lifecycleScope.launchWhenResumed {
                viewModel.getUserRepositories()
            }
        }
    }

    private fun initData() {
        adapter = RepoAdapter()
        binding.recyclerView.adapter = adapter

    }

    private fun initObservers() {
        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        binding.ivBack.clicks().debounce(200).onEach {
            findNavController().popBackStack()
        }.launchIn(lifecycleScope)
    }
}