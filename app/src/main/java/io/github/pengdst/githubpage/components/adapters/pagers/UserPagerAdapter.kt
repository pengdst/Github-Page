package io.github.pengdst.githubpage.components.adapters.pagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import javax.inject.Inject

class UserPagerAdapter @Inject constructor(
    fa: FragmentActivity
) : FragmentPagerAdapter(fa.supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = LinkedHashMap<String, Fragment>()

    fun addFragment(fragment: Pair<String, Fragment>) {
        fragmentList[fragment.first] = fragment.second
        notifyDataSetChanged()
    }

    fun clearFragment() {
        fragmentList.clear()
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentList.keys.toList()[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.values.toList()[position]
    }

    override fun getCount() = fragmentList.size
}