package com.step.lclib.work.bestpractice.page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.step.lclib.R
import com.step.lclib.work.lclog


private const val ARG_OBJECT = "object"

class ScreenSlidePageFragment : Fragment() {

    var position = 1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lclog("ScreenSlidePageFragment - onAttach - $position")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lclog("ScreenSlidePageFragment - onCreate - $position")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lclog("ScreenSlidePageFragment - onCreateView - $position")
        return inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_viewpager).text =
            "ViewPage-${arguments?.getInt(ARG_OBJECT)}"
        position = arguments?.getInt(ARG_OBJECT) ?: 0
        lclog("ScreenSlidePageFragment - onViewCreated - $position")
    }


    override fun onStart() {
        super.onStart()
        lclog("ScreenSlidePageFragment - onStart - $position")
    }

    override fun onResume() {
        super.onResume()
        lclog("ScreenSlidePageFragment - onResume - $position")
    }

    override fun onPause() {
        super.onPause()
        lclog("ScreenSlidePageFragment - onPause - $position")
    }

    override fun onStop() {
        super.onStop()
        lclog("ScreenSlidePageFragment - onStop - $position")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        lclog("ScreenSlidePageFragment - onDestroyView - $position")
    }

    override fun onDestroy() {
        super.onDestroy()
        lclog("ScreenSlidePageFragment - onDestroy - $position")
    }

    override fun onDetach() {
        super.onDetach()
        lclog("ScreenSlidePageFragment - onDetach - $position")
    }
}

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        val fragment = ScreenSlidePageFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }

}

class ViewPager2Activity : AppCompatActivity() {

    private lateinit var mViewpager: ViewPager2
    private lateinit var mTabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        mViewpager = findViewById(R.id.pager)
        mTabLayout = findViewById(R.id.tab_layout)

        val screenSlidePagerAdapter = ScreenSlidePagerAdapter(this)

        mViewpager.adapter = screenSlidePagerAdapter

        TabLayoutMediator(mTabLayout, mViewpager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
//        mViewpager.offscreenPageLimit = 5

    }

    override fun onBackPressed() {
        if (mViewpager.currentItem == 0) {
            super.onBackPressed()
        } else {
            mViewpager.currentItem = mViewpager.currentItem - 1
        }

    }
}