package com.example.githubapplication.ui.searchscreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapplication.R
import com.example.githubapplication.databinding.FragmentSearchByUsernameBinding
import com.example.githubapplication.presentation.MainViewModel
import com.example.githubapplication.ui.Adapter.SearchUserAdapter
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.view.clicks

class SearchByUserNameFragment : Fragment(R.layout.fragment_search_by_username) {

    private val binding by viewBinding(FragmentSearchByUsernameBinding::bind)
    private val viewModel by viewModel<MainViewModel>()
    private val navArgs: SearchByUserNameFragmentArgs by navArgs()
    private lateinit var adapter: SearchUserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initData()
        initObservers()
        initListeners()



        lifecycleScope.launchWhenResumed {
            viewModel.searchUsersByUserName(navArgs.username)
        }


    }

    private fun initData() {
        adapter = SearchUserAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.getSearchByUserFlow.onEach {
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