package com.example.casestudy

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.casestudy.ui.homePage.HomePageFragment
import com.example.casestudy.ui.serviceDetailPage.ServiceDetailPageFragment
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class FragmentLifeCycleTest {

    private lateinit var homePageFragment: HomePageFragment
    private lateinit var detailPageFragment: ServiceDetailPageFragment

    @Before
    fun setUp() {
        homePageFragment = HomePageFragment()
        detailPageFragment = ServiceDetailPageFragment()
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(homePageFragment)
        assertNotNull(detailPageFragment)
    }

    @Test
    fun startFragment() {
        fragmentStart(homePageFragment)
        fragmentStart(detailPageFragment)
    }

    @Test
    fun pauseFragment() {
        fragmentStart(homePageFragment)
        homePageFragment.onPause()
        fragmentStart(detailPageFragment)
        detailPageFragment.onPause()
    }

    @Test
    fun stopFragment() {
        fragmentStart(homePageFragment)
        homePageFragment.onStop()
        fragmentStart(detailPageFragment)
        detailPageFragment.onStop()
    }

    @Test
    fun destroyFragment() {
        fragmentStart(homePageFragment)
        homePageFragment.onDestroy()
        fragmentStart(detailPageFragment)
        detailPageFragment.onDestroy()
    }

    @Test
    fun resumeFragment() {
        fragmentStart(homePageFragment)
        homePageFragment.onDestroy()
        fragmentStart(detailPageFragment)
        detailPageFragment.onDestroy()
    }

    fun fragmentStart(fragment: Fragment) {
        val activity: FragmentActivity = Robolectric.buildActivity(FragmentActivity::class.java)
            .create()
            .start()
            .resume()
            .get()
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(fragment, null)
        fragmentTransaction.commit()
    }
}