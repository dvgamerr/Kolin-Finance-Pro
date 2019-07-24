package proj.android.zyl.finance_pro.projx.ver01

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.OutaccountDAO
import proj.android.zyl.finance_pro.model.Tb_outaccount
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

class ProjXOutaccountinfo : Activity() {        // 支出管理界面
    internal var lvinfo: ListView
    internal var strType = ""
    internal var btnClose: Button

    @Override
    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver01_outaccountinfo)
        lvinfo = findViewById(R.id.lvoutaccountinfo)

        ShowInfo(R.id.btnoutinfo)

        btnClose = findViewById(R.id.btnClose)

        lvinfo.setOnItemClickListener(object : OnItemClickListener() {
            fun onItemClick(parent: AdapterView<*>, view: View,
                            position: Int, id: Long) {
                val strInfo = String.valueOf((view as TextView).getText())
                val strid = strInfo.substring(0, strInfo.indexOf('-'))
                val intent = Intent(this@ProjXOutaccountinfo,
                        ProjXInfoManage::class.java)
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
        strType = "btnoutinfo"
        val outaccountinfo = OutaccountDAO(this@ProjXOutaccountinfo)
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
        arrayAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strInfos)
        lvinfo.setAdapter(arrayAdapter)
    }

    protected fun onRestart() {
        super.onRestart()
        ShowInfo(R.id.btnoutinfo)
    }

    companion object {
        val FLAG = "id"
    }
}