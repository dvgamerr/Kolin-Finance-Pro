package proj.android.zyl.finance_pro.projx.ver01

import java.util.Calendar

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.InaccountDAO
import proj.android.zyl.finance_pro.model.Tb_inaccount
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class ProjXAddInaccount : Activity() {
    internal var txtInMoney: EditText
    internal var txtInTime: EditText
    internal var txtInHandler: EditText
    internal var txtInMark: EditText
    internal var spInType: Spinner
    internal var btnInSaveButton: Button
    internal var btnInCancelButton: Button

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0

    private val mDateSetListener = object : DatePickerDialog.OnDateSetListener() {
        fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                      dayOfMonth: Int) {
            mYear = year
            mMonth = monthOfYear
            mDay = dayOfMonth
            updateDisplay()                                    // 显示设置的日期
        }
    }

    protected fun onCreate(savedInstanceState: Bundle) {            // 新增收入页面
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver01_addinaccount)
        txtInMoney = findViewById(R.id.txtInMoney)
        txtInTime = findViewById(R.id.txtInTime)
        txtInHandler = findViewById(R.id.txtInHandler)
        txtInMark = findViewById(R.id.txtInMark)
        spInType = findViewById(R.id.spInType)
        btnInSaveButton = findViewById(R.id.btnInSave)
        btnInCancelButton = findViewById(R.id.btnInCancel)

        txtInTime.setOnClickListener(object : OnClickListener() {

            fun onClick(arg0: View) {
                showDialog(DATE_DIALOG_ID)
            }
        })

        btnInSaveButton.setOnClickListener(object : OnClickListener() {

            fun onClick(arg0: View) {
                val strInMoney = txtInMoney.getText().toString()
                if (!strInMoney.isEmpty()) {
                    val inaccountDAO = InaccountDAO(
                            this@ProjXAddInaccount)
                    val tb_inaccount = Tb_inaccount(
                            inaccountDAO.getMaxId() + 1, Double
                            .parseDouble(strInMoney), txtInTime
                            .getText().toString(), spInType
                            .getSelectedItem().toString(),
                            txtInHandler.getText().toString(),
                            txtInMark.getText().toString())
                    inaccountDAO.add(tb_inaccount)
                    Toast.makeText(this@ProjXAddInaccount, "新增收入添加成功！",
                            Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ProjXAddInaccount, "你忘了输入收入金额！",
                            Toast.LENGTH_SHORT).show()
                }
            }
        })

        btnInCancelButton.setOnClickListener(object : OnClickListener() {
            fun onClick(arg0: View) {
                txtInMoney.setText("")                    // 金额文本框
                txtInMoney.setHint("0.00")                // 金额文本框设置提示
                txtInTime.setText("")                    // 时间文本框
                txtInTime.setHint("2017-01-01")        // 时间文本框设置提示
                txtInHandler.setText("")                // 付款方文本框
                txtInMark.setText("")                    // 备注文本框
                spInType.setSelection(0)                // 设置类别下拉列表默认选择第一项
            }
        })

        val c = Calendar.getInstance()                // 自动获取当前系统日期
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

    private fun updateDisplay() {                                // 显示设置的时间
        txtInTime.setText(StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay))
    }

    companion object {
        protected val DATE_DIALOG_ID = 0
    }
}
