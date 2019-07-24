package proj.android.zyl.finance_pro.projx.ver01

import java.util.Calendar

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.InaccountDAO
import proj.android.zyl.finance_pro.dao.OutaccountDAO
import proj.android.zyl.finance_pro.model.Tb_inaccount
import proj.android.zyl.finance_pro.model.Tb_outaccount
import proj.android.zyl.finance_pro.projx.ver01.ProjXShowinfo

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class ProjXInfoManage : Activity() {
    internal var tvtitle: TextView
    internal var textView: TextView
    internal var txtMoney: EditText
    internal var txtTime: EditText
    internal var txtHA: EditText
    internal var txtMark: EditText
    internal var spType: Spinner
    internal var btnEdit: Button
    internal var btnDel: Button
    internal var strInfos: Array<String>
    internal var strid: String
    internal var strType: String

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0

    internal var outaccountDAO = OutaccountDAO(this@ProjXInfoManage)
    internal var inaccountDAO = InaccountDAO(this@ProjXInfoManage)

    private val mDateSetListener = object : DatePickerDialog.OnDateSetListener() {
        fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                      dayOfMonth: Int) {
            mYear = year
            mMonth = monthOfYear
            mDay = dayOfMonth
            updateDisplay()
        }
    }

    @Override
    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver01_infomanage)
        tvtitle = findViewById(R.id.inouttitle)
        textView = findViewById(R.id.tvInOut)
        txtMoney = findViewById(R.id.txtInOutMoney)
        txtTime = findViewById(R.id.txtInOutTime)
        spType = findViewById(R.id.spInOutType)
        txtHA = findViewById(R.id.txtInOut)
        txtMark = findViewById(R.id.txtInOutMark)
        btnEdit = findViewById(R.id.btnInOutEdit)
        btnDel = findViewById(R.id.btnInOutDelete)

        val intent = getIntent()
        val bundle = intent.getExtras()
        strInfos = bundle.getStringArray(ProjXShowinfo.FLAG)
        strid = strInfos[0]
        strType = strInfos[1]
        if (strType.equals("btnoutinfo")) {
            tvtitle.setText("支出管理")
            textView.setText("地  点：")
            val tb_outaccount = outaccountDAO.find(Integer
                    .parseInt(strid))
            txtMoney.setText(String.valueOf(tb_outaccount.getMoney()))
            txtTime.setText(tb_outaccount.getTime())
            spType.setPrompt(tb_outaccount.getType())
            txtHA.setText(tb_outaccount.getAddress())
            txtMark.setText(tb_outaccount.getMark())
        } else if (strType.equals("btnininfo")) {
            tvtitle.setText("收入管理")
            textView.setText("付款方：")
            val tb_inaccount = inaccountDAO.find(Integer
                    .parseInt(strid))
            txtMoney.setText(String.valueOf(tb_inaccount.getMoney()))
            txtTime.setText(tb_inaccount.getTime())
            spType.setPrompt(tb_inaccount.getType())
            txtHA.setText(tb_inaccount.getHandler())
            txtMark.setText(tb_inaccount.getMark())
        }

        txtTime.setOnClickListener(object : OnClickListener() {

            fun onClick(arg0: View) {
                showDialog(DATE_DIALOG_ID)
            }
        })

        btnEdit.setOnClickListener(object : OnClickListener() {

            fun onClick(arg0: View) {
                if (strType.equals("btnoutinfo")) {
                    val tb_outaccount = Tb_outaccount()
                    tb_outaccount.setid(Integer.parseInt(strid))
                    tb_outaccount.setMoney(Double.parseDouble(txtMoney
                            .getText().toString()))
                    tb_outaccount.setTime(txtTime.getText().toString())
                    tb_outaccount.setType(spType.getSelectedItem().toString())
                    tb_outaccount.setAddress(txtHA.getText().toString())
                    tb_outaccount.setMark(txtMark.getText().toString())
                    outaccountDAO.update(tb_outaccount)
                } else if (strType.equals("btnininfo")) {
                    val tb_inaccount = Tb_inaccount()
                    tb_inaccount.setid(Integer.parseInt(strid))
                    tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText()
                            .toString()))
                    tb_inaccount.setTime(txtTime.getText().toString())
                    tb_inaccount.setType(spType.getSelectedItem().toString())
                    tb_inaccount.setHandler(txtHA.getText().toString())
                    tb_inaccount.setMark(txtMark.getText().toString())
                    inaccountDAO.update(tb_inaccount)
                }
                Toast.makeText(this@ProjXInfoManage, "数据修改成功！",
                        Toast.LENGTH_SHORT).show()
            }
        })

        btnDel.setOnClickListener(object : OnClickListener() {
            fun onClick(arg0: View) {
                if (strType.equals("btnoutinfo")) {
                    outaccountDAO.detele(Integer.parseInt(strid))
                } else if (strType.equals("btnininfo")) {
                    inaccountDAO.detele(Integer.parseInt(strid))
                }
                Toast.makeText(this@ProjXInfoManage, "数据删除成功！",
                        Toast.LENGTH_SHORT).show()
            }
        })

        val c = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)
        updateDisplay()
    }

    protected fun onCreateDialog(id: Int): Dialog? {
        when (id) {
            DATE_DIALOG_ID -> return DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                    mDay)
        }
        return null
    }

    private fun updateDisplay() {
        txtTime.setText(StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay))
    }

    companion object {
        protected val DATE_DIALOG_ID = 0
    }
}
