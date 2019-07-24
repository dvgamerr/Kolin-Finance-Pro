package proj.android.zyl.finance_pro.projx.ver01

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.FlagDAO
import proj.android.zyl.finance_pro.model.Tb_flag
import proj.android.zyl.finance_pro.projx.ver01.ProjXShowinfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ProjXFlagManage : Activity() {
    internal var txtFlag: EditText
    internal var btnEdit: Button
    internal var btnDel: Button
    internal var strid: String

    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver01_flagmanage)
        txtFlag = findViewById(R.id.txtFlagManage)
        btnEdit = findViewById(R.id.btnFlagManageEdit)
        btnDel = findViewById(R.id.btnFlagManageDelete)

        val intent = getIntent()
        val bundle = intent.getExtras()
        strid = bundle.getString(ProjXShowinfo.FLAG)
        val flagDAO = FlagDAO(this@ProjXFlagManage)
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag())
        btnEdit.setOnClickListener(object : OnClickListener() {
            fun onClick(arg0: View) {
                val tb_flag = Tb_flag()
                tb_flag.setid(Integer.parseInt(strid))
                tb_flag.setFlag(txtFlag.getText().toString())
                flagDAO.update(tb_flag)
                Toast.makeText(this@ProjXFlagManage, "便签已修改！", Toast.LENGTH_SHORT).show()
            }
        })

        btnDel.setOnClickListener(object : OnClickListener() {
            fun onClick(arg0: View) {
                flagDAO.detele(Integer.parseInt(strid))
                Toast.makeText(this@ProjXFlagManage, "便签已删除！",
                        Toast.LENGTH_SHORT).show()
            }
        })
    }

}
