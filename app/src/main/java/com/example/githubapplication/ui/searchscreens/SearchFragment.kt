package com.example.githubapplication.ui.searchscreens

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapplication.R
import com.example.githubapplication.databinding.FragmentSearchBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initListeners()


    }

    private fun initListeners() {
        binding.apply {

            ivBack.clicks().debounce(200).onEach {
                findNavController().popBackStack()
            }.launchIn(lifecycleScope)

            etSearch.addTextChangedListener {
                val value = etSearch.text.toString()

                tvFind.visibility = View.INVISIBLE
                tvAllOf.visibility = View.INVISIBLE

                layoutPods.visibility = View.VISIBLE

                tvRepo.text = getString(R.string.repo_text, value)
                tvPeople.text = getString(R.string.people_text, value)

                llPeople.clicks().debounce(200).onEach {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchByUserNameFragment(value)
                    )
                }.launchIn(lifecycleScope)

                llRepo.clicks().debounce(200).onEach {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchByRepoFragment(value)
                    )
                }.launchIn(lifecycleScope)
            }

        }
    }
}