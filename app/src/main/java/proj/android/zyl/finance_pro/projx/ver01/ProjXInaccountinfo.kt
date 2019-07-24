package proj.android.zyl.finance_pro.projx.ver01

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.InaccountDAO
import proj.android.zyl.finance_pro.model.Tb_inaccount
import proj.android.zyl.finance_pro.projx.ver01.ProjXInfoManage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.AdapterView.OnItemClickListener

class ProjXInaccountinfo : Activity() {        // 收入信息界面
    internal var lvinfo: ListView
    internal var strType = ""
    internal var btnClose: Button
    @Override
    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver01_inaccountinfo)
        lvinfo = findViewById(R.id.lvinaccountinfo)
        ShowInfo(R.id.btnininfo)

        btnClose = findViewById(R.id.btnClose)

        lvinfo.setOnItemClickListener(object : OnItemClickListener() {
            fun onItemClick(parent: AdapterView<*>, view: View,
                            position: Int, id: Long) {
                val strInfo = String.valueOf((view as TextView).getText())
                val strid = strInfo.substring(0, strInfo.indexOf('-'))
                val intent = Intent(this@ProjXInaccountinfo, ProjXInfoManage::class.java)
                intent.putExtra(FLAG, arrayOf(strid, strType))
                startActivity(intent)
            }
        })

        btnClose.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View) {
                finish()  //退出
            }
        })
    }

    private fun ShowInfo(intType: Int) {
        var strInfos: Array<String>? = null
        var arrayAdapter: ArrayAdapter<String>? = null
        strType = "btnininfo"
        val inaccountinfo = InaccountDAO(this@ProjXInaccountinfo)
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
        arrayAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strInfos)
        lvinfo.setAdapter(arrayAdapter)
    }

    @Override
    protected fun onRestart() {
        super.onRestart()
        ShowInfo(R.id.btnininfo)
    }

    companion object {
        val FLAG = "id"
    }
}
