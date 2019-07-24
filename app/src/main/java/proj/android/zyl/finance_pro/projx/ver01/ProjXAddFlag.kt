package proj.android.zyl.finance_pro.projx.ver01

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.FlagDAO
import proj.android.zyl.finance_pro.model.Tb_flag
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ProjXAddFlag : Activity() {
    private var txt_flag: EditText? = null
    private var btn_save: Button? = null
    private var btn_exit: Button? = null
    @Override
    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.ver01_accountflag)


        txt_flag = findViewById(R.id.txtFlag)
        btn_save = findViewById(R.id.btnflagSave)
        btn_exit = findViewById(R.id.btnflagCancel)

        btn_save!!.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View) {
                val flag_str = txt_flag!!.getText().toString()
                if (!flag_str.trim().isEmpty()) {
                    val flagDAO = FlagDAO(this@ProjXAddFlag)
                    val tb_flag = Tb_flag(flagDAO.getMaxId() + 1, flag_str)
                    flagDAO.add(tb_flag)
                    Toast.makeText(this@ProjXAddFlag, "数据添加成功！", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ProjXAddFlag, "你还没有输入便签信息", Toast.LENGTH_SHORT).show()
                }
            }
        })

        btn_exit!!.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View) {
                finish()
            }
        })
    }
}


