package com.example.ctrlbee.presentation.fragment.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.ctrlbee.R
import com.example.ctrlbee.data.repository.SharedPreferencesRepo
import com.example.ctrlbee.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment: Fragment(R.layout.fragment_profile) {

    @Inject lateinit var sharedPreferencesRepo: SharedPreferencesRepo
    private val viewBinding: FragmentProfileBinding by viewBinding()

    private val pagerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ProfilePagerAdapter(this)
    }

    private val tabTitles = listOf("Media", "Statistics")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActions()
    }

    private fun initActions() = with(viewBinding) {
        profilePager.adapter = pagerAdapter
        TabLayoutMediator(profileTabs, profilePager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        Glide.with(viewBinding.root)
            .load("https://i.pinimg.com/236x/e6/8c/2b/e68c2bd8fa49f1b3400e2e152f2c2ef4.jpg")
            .circleCrop()
            .error(R.drawable.bee)
            .into(viewBinding.profileImage)

        iconMenu.setOnClickListener {
            sharedPreferencesRepo.clearAll()
            activity?.finish()
        }
    }
}