package proj.android.zyl.finance_pro.projx.ver03.Navigation03_DataActivity

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView

import java.util.ArrayList

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.InaccountDAO
import proj.android.zyl.finance_pro.model.Tb_inaccount
import proj.android.zyl.finance_pro.projx.ver01.ProjXInaccountinfo
import proj.android.zyl.finance_pro.projx.ver03.Navigation_BaseActivity
import proj.android.zyl.finance_pro.projx.ver03.ViewPagerFragmentAdapter

class Navigation_DataActivity : Navigation_BaseActivity() {
    private var myViewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private val IconResID = intArrayOf(R.drawable.selector_03_1, R.drawable.selector_03_2, R.drawable.selector_03_3)
    private val TollBarTitle = intArrayOf(R.string.nv_data_admin, R.string.nv_data_in, R.string.nv_data_out)


    @Override
    protected fun onCreate(savedInstanceState: Bundle) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity_main)
        myViewPager = findViewById(R.id.NavigationMyViewPager) as ViewPager
        tabLayout = findViewById(R.id.NavigationTabLayout) as TabLayout
        toolbar.setTitle(TollBarTitle[0])//设置ToolBar Title
        setUpToolBar()//使用父类别的setUpToolBar()，设置ToolBar
        CurrentMenuItem = 2//当前的Navigation位置
        NV.getMenu().getItem(CurrentMenuItem).setChecked(true)//设置Navigation目前被选取项目
        setViewPager()
        tabLayout!!.setupWithViewPager(myViewPager)
        setTabLayoutIcon()


    }

    fun setTabLayoutIcon() {
        for (i in 0..2) {
            tabLayout!!.getTabAt(i).setIcon(IconResID[i])
        }
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener() {
            @Override
            fun onTabSelected(tab: TabLayout.Tab) {
                toolbar.getMenu().clear()
                when (tab.getPosition()) {
                    0 -> {
                        toolbar.inflateMenu(R.menu.menu_one)
                        toolbar.setTitle(TollBarTitle[0])
                    }
                    1 -> {
                        toolbar.inflateMenu(R.menu.menu_two)
                        toolbar.setTitle(TollBarTitle[1])
                    }
                    2 -> {
                        toolbar.inflateMenu(R.menu.menu_three)
                        toolbar.setTitle(TollBarTitle[2])
                    }
                }
            }

            @Override
            fun onTabUnselected(tab: TabLayout.Tab) {
            }

            @Override
            fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

    }

    private fun setViewPager() {
        val myFragment1 = Data_FragmentList_DataAdmin()
        val myFragment2 = Data_FragmentList_DataIn()
        val myFragment3 = Data_FragmentList_DataOut()
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(myFragment1)
        fragmentList.add(myFragment2)
        fragmentList.add(myFragment3)
        val myFragmentAdapter = ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList)
        myViewPager!!.setAdapter(myFragmentAdapter)
    }

    @Override
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_one, menu)
        return true
    }


}
