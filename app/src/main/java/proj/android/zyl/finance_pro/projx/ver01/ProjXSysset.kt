package proj.android.zyl.finance_pro.projx.ver01

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.PwdDAO
import proj.android.zyl.finance_pro.model.Tb_pwd

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ProjXSysset : Activity() {
    internal var now_txtPwd1: EditText
    internal var now_txtPwd2: EditText
    internal var btnSet: Button
    internal var btnsetCancel: Button
    internal var btnClose: Button

    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver01_sysset)

        now_txtPwd1 = findViewById(R.id.now_txtPwd1)
        now_txtPwd2 = findViewById(R.id.now_txtPwd2)
        btnSet = findViewById(R.id.btnSet)
        btnsetCancel = findViewById(R.id.btnsetCancel)
        btnClose = findViewById(R.id.btnClose)


        btnSet.setOnClickListener(object : OnClickListener() {            //设置按钮
            fun onClick(arg0: View) {
                val pwdDAO = PwdDAO(this@ProjXSysset)

                val tb_pwd = Tb_pwd(now_txtPwd1.getText().toString(), now_txtPwd1.getText().toString())

                if (pwdDAO.getCount() === 0) {
                    pwdDAO.add(tb_pwd)
                } else if (now_txtPwd1.getText().toString().equals("") || now_txtPwd2.getText().toString().equals("")) {
                    Toast.makeText(this@ProjXSysset, "输入的密码为空", Toast.LENGTH_SHORT).show()
                } else if (!now_txtPwd1.getText().toString().equals(now_txtPwd2.getText().toString())) {
                    Toast.makeText(this@ProjXSysset, "两次密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show()
                    now_txtPwd1.setText("")
                    now_txtPwd2.setText("")
                } else {
                    pwdDAO.update(tb_pwd)

                    Toast.makeText(this@ProjXSysset, "密码设置成功！", Toast.LENGTH_SHORT)
                            .show()
                }
            }

        })


        btnsetCancel.setOnClickListener(object : OnClickListener() {

            fun onClick(arg0: View) {
                now_txtPwd1.setText("")
                now_txtPwd1.setHint("请输入新密码")
                now_txtPwd2.setText("")
                now_txtPwd2.setHint("再次输入密码")
            }
        })

        btnClose.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View) {
                finish()  //退出
            }
        })

    }
}
