package com.pss.jonjung.view

import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.widget.ViewPager2
import com.pss.jonjung.R
import com.pss.jonjung.base.BaseActivity
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.databinding.ActivityMainBinding
import com.pss.jonjung.view.adapter.MainViewPagerAdapter
import com.pss.jonjung.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<PostViewModel>()

    override fun init() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
    }
}