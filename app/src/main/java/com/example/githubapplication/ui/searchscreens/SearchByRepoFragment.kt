package com.example.githubapplication.ui.searchscreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapplication.R
import com.example.githubapplication.databinding.FragmentSearchByRepoBinding
import com.example.githubapplication.presentation.MainViewModel
import com.example.githubapplication.ui.Adapter.SearchRepoAdapter
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.view.clicks

class SearchByRepoFragment : Fragment(R.layout.fragment_search_by_repo) {

    private val  binding by viewBinding(FragmentSearchByRepoBinding::bind)
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter: SearchRepoAdapter
    private val navArgs: SearchByRepoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initData()
        initObservers()
        initListeners()

        lifecycleScope.launchWhenResumed {
            viewModel.searchRepoByRepoName(navArgs.repoName)
        }
    }

    private fun initData() {
        binding.apply {
            adapter = SearchRepoAdapter()
            recyclerView.adapter = adapter

            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun initObservers() {
        viewModel.searchReposByRepoNameFlow.onEach {
            if (it.isEmpty()) {
                binding.tvNull.visibility = View.VISIBLE
            } else {
                adapter.submitList(it)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        binding.ivBack.clicks().debounce(200).onEach {
            findNavController().popBackStack()
        }.launchIn(lifecycleScope)
    }
}
