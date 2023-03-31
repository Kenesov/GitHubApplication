package com.example.githubapplication.ui.bnavsceens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapplication.R
import com.example.githubapplication.databinding.FragmentHomeBinding
import com.example.githubapplication.ui.nestedgraph.ContainerHomeFragmentDirections
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initData()
        initListeners()


    }

    private fun initData() {
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container)
    }

    @OptIn(FlowPreview::class)
    private fun initListeners() {
        binding.apply {

            llRepo.clicks().debounce(200).onEach {
                navController.navigate(
                    ContainerHomeFragmentDirections.actionContainerHomeFragmentToRepositoriesFragment()
                )
            }.launchIn(lifecycleScope)

            ivSearch.clicks().debounce(200).onEach {
                navController.navigate(
                    ContainerHomeFragmentDirections.actionContainerHomeFragmentToSearchFragment()
                )
            }.launchIn(lifecycleScope)
        }
    }
}