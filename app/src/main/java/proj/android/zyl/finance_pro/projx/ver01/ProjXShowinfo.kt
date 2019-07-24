package proj.android.zyl.finance_pro.projx.ver01

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.FlagDAO
import proj.android.zyl.finance_pro.dao.InaccountDAO
import proj.android.zyl.finance_pro.dao.OutaccountDAO
import proj.android.zyl.finance_pro.model.Tb_flag
import proj.android.zyl.finance_pro.model.Tb_inaccount
import proj.android.zyl.finance_pro.model.Tb_outaccount
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

class ProjXShowinfo : Activity() {                    // 数据管理界面
    internal var btnoutinfo: Button
    internal var btnininfo: Button
    internal var btnflaginfo: Button
    internal var btnClose: Button
    internal var lvinfo: ListView
    internal var strType = ""

    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver01_showinfo)

        btnClose = findViewById(R.id.btnClose)
        lvinfo = findViewById(R.id.lvinfo)
        btnoutinfo = findViewById(R.id.btnoutinfo)
        btnininfo = findViewById(R.id.btnininfo)
        btnflaginfo = findViewById(R.id.btnflaginfo)

        ShowInfo(R.id.btnoutinfo)

        btnoutinfo.setOnClickListener(object : OnClickListener() {
            fun onClick(arg0: View) {
                ShowInfo(R.id.btnoutinfo)                // 显示支出信息
            }
        })

        btnininfo.setOnClickListener(object : OnClickListener() {
            fun onClick(arg0: View) {
                ShowInfo(R.id.btnininfo)                // 显示收入信息
            }
        })
        btnflaginfo.setOnClickListener(object : OnClickListener() {
            fun onClick(arg0: View) {
                ShowInfo(R.id.btnflaginfo)                // 显示便签信息
            }
        })

        btnClose.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View) {
                finish()  //退出
            }
        })



        lvinfo.setOnItemClickListener(object : OnItemClickListener() {

            fun onItemClick(parent: AdapterView<*>, view: View,
                            position: Int, id: Long) {
                val strInfo = String.valueOf((view as TextView).getText())        // 记录信息
                val strid = strInfo.substring(0, strInfo.indexOf('-'))            // 取编号
                var intent: Intent? = null
                if ((strType === "btnoutinfo") or (strType === "btnininfo")) {                // 判断支出还是收入
                    intent = Intent(this@ProjXShowinfo, ProjXInfoManage::class.java)
                    intent!!.putExtra(FLAG, arrayOf(strid, strType))            // 设置传值
                } else if (strType === "btnflaginfo") {                                // 判断便签信息
                    intent = Intent(this@ProjXShowinfo, ProjXFlagManage::class.java)
                    intent!!.putExtra(FLAG, strid)                                    // 设置传值
                }
                startActivity(intent)
            }
        })
    }

    private fun ShowInfo(intType: Int) {
        var strInfos: Array<String>? = null
        var arrayAdapter: ArrayAdapter<String>? = null
        when (intType) {
            R.id.btnoutinfo -> {
                strType = "btnoutinfo"
                val outaccountinfo = OutaccountDAO(this@ProjXShowinfo)
                val listoutinfos = outaccountinfo.getScrollData(0,
                        outaccountinfo.getCount() as Int)
                strInfos = arrayOfNulls(listoutinfos.size())
                var i = 0
                for (tb_outaccount in listoutinfos) {                        // 遍历List泛型集合
                    // 将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[i] = (tb_outaccount.getid() + "-"
                            + tb_outaccount.getType() + " "
                            + String.valueOf(tb_outaccount.getMoney()) + "元     "
                            + tb_outaccount.getTime())
                    i++
                }
            }
            R.id.btnininfo -> {
                strType = "btnininfo"
                val inaccountinfo = InaccountDAO(this@ProjXShowinfo)
                val listinfos = inaccountinfo.getScrollData(0,
                        inaccountinfo.getCount() as Int)
                strInfos = arrayOfNulls(listinfos.size())
                var m = 0
                for (tb_inaccount in listinfos) {                            // 遍历List泛型集合
                    // 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[m] = (tb_inaccount.getid() + "-"
                            + tb_inaccount.getType() + " "
                            + String.valueOf(tb_inaccount.getMoney()) + "元     "
                            + tb_inaccount.getTime())
                    m++
                }
            }
            R.id.btnflaginfo -> {
                strType = "btnflaginfo"
                val flaginfo = FlagDAO(this@ProjXShowinfo)
                val listFlags = flaginfo.getScrollData(0,
                        flaginfo.getCount() as Int)
                strInfos = arrayOfNulls(listFlags.size())
                var n = 0
                for (tb_flag in listFlags) {                                        // 遍历List泛型集合
                    // 将便签相关信息组合成一个字符串

                    // 存储到字符串数组的相应位置
                    strInfos[n] = tb_flag.getid() + "-" + tb_flag.getFlag()
                    if (strInfos[n].length() > 15)
                        strInfos[n] = strInfos[n].substring(0, 15) + "……"
                    n++
                }
            }
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
