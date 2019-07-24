package proj.android.zyl.finance_pro.projx.ver03.Navigation01_MainActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import proj.android.zyl.finance_pro.R


class Main_FragmentList_Readme : Fragment() {
    @Override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle): View {


        return inflater.inflate(R.layout.main_fragmentlist_readme, container, false)
    }
}