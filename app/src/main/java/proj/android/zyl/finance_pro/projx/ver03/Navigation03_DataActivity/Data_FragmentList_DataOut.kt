package proj.android.zyl.finance_pro.projx.ver03.Navigation03_DataActivity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.OutaccountDAO
import proj.android.zyl.finance_pro.model.Tb_outaccount
import proj.android.zyl.finance_pro.projx.ver01.ProjXInfoManage
import proj.android.zyl.finance_pro.projx.ver01.ProjXOutaccountinfo

class Data_FragmentList_DataOut : Fragment() {
    internal var lvinfo: ListView
    internal var strType = ""
    internal var btnClose: Button


    @Override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle): View {
        val view = inflater.inflate(R.layout.data_fragmentlist_dataout, container, false)



        super.onCreate(savedInstanceState)
        lvinfo = view.findViewById(R.id.lvoutaccountinfo)

        ShowInfo(R.id.btnoutinfo)

        btnClose = view.findViewById(R.id.btnClose)

        lvinfo.setOnItemClickListener(object : AdapterView.OnItemClickListener() {
            fun onItemClick(parent: AdapterView<*>, view: View,
                            position: Int, id: Long) {
                val strInfo = String.valueOf((view as TextView).getText())
                val strid = strInfo.substring(0, strInfo.indexOf('-'))
                val intent = Intent(getActivity(), Data_FragmentList_DataAdmin::class.java)
                intent.putExtra(FLAG, arrayOf(strid, strType))
                startActivity(intent)
            }
        })


        return view
    }


    private fun ShowInfo(intType: Int) {
        var strInfos: Array<String>? = null
        var arrayAdapter: ArrayAdapter<String>? = null
        strType = "btnoutinfo"
        val outaccountinfo = OutaccountDAO(getActivity())
        val listoutinfos = outaccountinfo.getScrollData(0,
                outaccountinfo.getCount() as Int)
        strInfos = arrayOfNulls(listoutinfos.size())
        var i = 0
        for (tb_outaccount in listoutinfos) {                        // 将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
            strInfos[i] = (tb_outaccount.getid() + "-" + tb_outaccount.getType()
                    + " " + String.valueOf(tb_outaccount.getMoney()) + "元     "
                    + tb_outaccount.getTime())
            i++
        }
        arrayAdapter = ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strInfos)
        lvinfo.setAdapter(arrayAdapter)
    }


    fun onResume() {
        super.onResume()
        ShowInfo(R.id.btnoutinfo)// 显示收入信息
    }

    companion object {

        val FLAG = "id"
    }

}