package com.example.githubapplication.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubapplication.R
import com.example.githubapplication.databinding.FragmentFirstBinding
import com.example.githubapplication.models.local.LocalStorage
import com.example.githubapplication.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        initObservers()

        binding.apply {
            btnSingIn.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo"
                    )
                )
                startActivity(intent)
            }
        }
    }

    private fun initObservers() {
        viewModel.getSuccessFlow.onEach {
            LocalStorage().token = it
            LocalStorage().isLogin = true
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToContainerHomeFragment()
            )
        }.launchIn(lifecycleScope)

        viewModel.getMessageFlow.onEach {
            Toast.makeText(requireContext(), "Che-to neto", Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = requireActivity().intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                //get Access Token zapros ketedi codedi alg'annan son'
                LocalStorage().code = code
                Toast.makeText(requireContext(), "Login success: $code", Toast.LENGTH_SHORT).show()
                lifecycleScope.launchWhenResumed {
                    viewModel.isSuccess()
                }
            } else if ((uri.getQueryParameter("error")) != null) {
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}