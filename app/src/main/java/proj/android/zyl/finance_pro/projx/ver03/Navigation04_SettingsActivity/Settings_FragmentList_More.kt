package proj.android.zyl.finance_pro.projx.ver03.Navigation04_SettingsActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import proj.android.zyl.finance_pro.R

class Settings_FragmentList_More : Fragment() {
    @Override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle): View {


        return inflater.inflate(R.layout.settings_fragmentlist_more, container, false)
    }
}