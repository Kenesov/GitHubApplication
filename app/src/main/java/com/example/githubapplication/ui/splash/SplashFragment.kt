package com.example.githubapplication.ui.splash

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubapplication.R
import com.example.githubapplication.models.local.LocalStorage
import com.example.githubapplication.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay

class SplashFragment : Fragment(R.layout.fragment_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            delay(2300)
            if (LocalStorage().isLogin) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToContainerHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFirstFragment())
            }
        }
    }
}
