package io.github.pengdst.githubpage.components.adapters.pagers

import androidx.fragment.app.*

class UserStatePagerAdapter(fa: FragmentActivity): FragmentStatePagerAdapter(fa.supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = LinkedHashMap<String, Fragment>()

    fun addFragment(fragment: Pair<String, Fragment>){
        fragmentList[fragment.first] = fragment.second
        notifyDataSetChanged()
    }

    fun clearFragment(){
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