package proj.android.zyl.finance_pro.projx.ver03

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class ViewPagerFragmentAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {

    val count: Int
        @Override
        get() = fragmentList.size()

    @Override
    fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

}
