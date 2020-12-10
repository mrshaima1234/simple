package com.e.e_commerce.ui.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.e.e_commerce.R
import com.e.e_commerce.base.BaseActivity
import com.e.e_commerce.interfaces.CommonInterface
import com.e.e_commerce.ui.main.home.HomeFragment
import com.e.e_commerce.ui.main.viewmodel.CommonViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() , NavigationView.OnNavigationItemSelectedListener,CommonInterface{
    override val layoutId: Int
        get() = R.layout.activity_main
    override val setToolbar: Boolean
        get() = true
    override val hideStatusBar: Boolean
        get() = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.title = "Home"
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        launchNavigation()
        setUpTab();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun launchNavigation() {
        val actionBarDrawerToggle: ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
                R.string.openDrawer, R.string.closeDrawer
            ) {
                override fun onDrawerClosed(drawerView: View) {
                    // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                    super.onDrawerClosed(drawerView)
                }

                override fun onDrawerOpened(drawerView: View) {
                    // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                    super.onDrawerOpened(drawerView)
                }

                override fun onDrawerSlide(
                    drawerView: View,
                    slideOffset: Float
                ) {
                    super.onDrawerSlide(drawerView, slideOffset)
                }
            }

        //Setting the actionbarToggle to drawer layout
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled=true
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState()
    }
    private fun setUpTab() {
        val commonViewModel = CommonViewModel(this)
        commonViewModel.getBrands()
            .observe(this, Observer {
                if (it.status) {
                    val viewPagerAdapter =
                        ViewPagerAdapter(supportFragmentManager)
                    viewPagerAdapter.addFrag(HomeFragment.newInstance(), "ALL")

                    for (i in it.data.indices)
                        viewPagerAdapter.addFrag(HomeFragment.newInstance(), it.data[i].name)
                    viewPager.adapter= viewPagerAdapter
                    tab_layout.setupWithViewPager(viewPager)
                } else {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onFailure() {
    }

    override fun onShowProgress() {
        showProgress()
    }

    override fun onDismissProgress() {
        dismissProgress()
    }
}