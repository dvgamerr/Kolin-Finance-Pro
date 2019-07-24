package proj.android.zyl.finance_pro.projx.ver03.Navigation03_DataActivity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.InaccountDAO
import proj.android.zyl.finance_pro.model.Tb_inaccount

class Data_FragmentList_DataIn : Fragment() {
    internal var lvinfo: ListView
    internal var strType = ""


    fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle): View {
        val view = inflater.inflate(R.layout.data_fragmentlist_datain, container, false)


        super.onCreate(savedInstanceState)
        lvinfo = view.findViewById(R.id.lvinaccountinfo)


        lvinfo.setOnItemClickListener(object : AdapterView.OnItemClickListener() {
            fun onItemClick(parent: AdapterView<*>, view: View,
                            position: Int, id: Long) {
                val strInfo = String.valueOf((view as TextView).getText())
                val strid = strInfo.substring(0, strInfo.indexOf('-'))
                val intent01 = Intent()
                intent01.setClass(getActivity(), Data_FragmentList_DataAdmin::class.java)
                intent01.putExtra(FLAG, arrayOf(strid, strType))
                startActivity(intent01)
            }
        })

        return view
    }


    private fun ShowInfo(intType: Int) {
        var strInfos: Array<String>? = null
        var arrayAdapter: ArrayAdapter<String>? = null
        strType = "btnininfo"
        val inaccountinfo = InaccountDAO(getActivity())
        val listinfos = inaccountinfo.getScrollData(0,
                inaccountinfo.getCount() as Int)
        strInfos = arrayOfNulls(listinfos.size())
        var m = 0
        for (tb_inaccount in listinfos) {
            strInfos[m] = (tb_inaccount.getid() + "-" + tb_inaccount.getType()
                    + " " + String.valueOf(tb_inaccount.getMoney()) + "元     "
                    + tb_inaccount.getTime())
            m++
        }
        arrayAdapter = ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strInfos)
        lvinfo.setAdapter(arrayAdapter)
    }

    fun onResume() {
        super.onResume()
        ShowInfo(R.id.btnininfo)// 显示收入信息
    }

    companion object {


        val FLAG = "id"
    }
}