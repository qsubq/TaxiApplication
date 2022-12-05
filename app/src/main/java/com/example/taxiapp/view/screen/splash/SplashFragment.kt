package com.example.taxiapp.view.screen.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taxiapp.R
import com.example.taxiapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
            {
                // После Splash Screen перенаправляем на нужную Activity
                val user = UserDb.getCurrentUser()
                routeToAppropriatePage(user)
                finish()
            },
            splashScreenDuration
        )
    }

    private fun getSplashScreenDuration() = 2000L

    private fun routeToAppropriatePage(user: User?) {
        // пример перенаправления
        when {
            user == null -> OnboardingActivity.start(this)
            user.hasPhoneNumber() -> EditProfileActivity.start(this)
            user.hasSubscriptionExpired() -> PaymentPlansActivity.start(this)
            else -> HomeActivity.start(this)
        }
    }
}