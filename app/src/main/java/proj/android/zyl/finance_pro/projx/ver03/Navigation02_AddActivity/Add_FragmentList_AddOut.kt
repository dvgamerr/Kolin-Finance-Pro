package proj.android.zyl.finance_pro.projx.ver03.Navigation02_AddActivity

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

import java.util.Calendar

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.OutaccountDAO
import proj.android.zyl.finance_pro.model.Tb_outaccount
import proj.android.zyl.finance_pro.projx.ver01.ProjXAddOutaccount
import proj.android.zyl.finance_pro.projx.ver03.RecyclerViewAdapter


class Add_FragmentList_AddOut : Fragment() {
    internal var txtMoney: EditText
    internal var txtTime: EditText
    internal var txtAddress: EditText
    internal var txtMark: EditText
    internal var spType: Spinner
    internal var btnSaveButton: Button
    internal var btnCancelButton: Button
    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0

    private val mDateSetListener = object : DatePickerDialog.OnDateSetListener() {
        fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                      dayOfMonth: Int) {
            mYear = year
            mMonth = monthOfYear
            mDay = dayOfMonth
            updateDisplay()
        }
    }

    fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle): View {           // 新增收入页面
        val view = inflater.inflate(R.layout.add_fragmentlist_addout, container, false)


        super.onCreate(savedInstanceState)

        txtMoney = view.findViewById(R.id.txtMoney)
        txtTime = view.findViewById(R.id.txtTime)
        txtAddress = view.findViewById(R.id.txtAddress)
        txtMark = view.findViewById(R.id.txtMark)
        spType = view.findViewById(R.id.spType)
        btnSaveButton = view.findViewById(R.id.btnSave)
        btnCancelButton = view.findViewById(R.id.btnCancel)

        txtTime.setOnClickListener(object : View.OnClickListener() {

            fun onClick(arg0: View) {
                getActivity().showDialog(DATE_DIALOG_ID)
            }
        })

        btnSaveButton.setOnClickListener(object : View.OnClickListener() {

            fun onClick(arg0: View) {
                val strMoney = txtMoney.getText().toString()
                if (!strMoney.isEmpty()) {
                    val outaccountDAO = OutaccountDAO(getActivity())
                    val tb_outaccount = Tb_outaccount(
                            outaccountDAO.getMaxId() + 1, Double
                            .parseDouble(strMoney), txtTime
                            .getText().toString(), spType
                            .getSelectedItem().toString(),
                            txtAddress.getText().toString(), txtMark
                            .getText().toString())
                    outaccountDAO.add(tb_outaccount)
                    Toast.makeText(getActivity(), "新增支出添加成功！",
                            Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(getActivity(), "你忘了输入支出金额！",
                            Toast.LENGTH_SHORT).show()
                }
            }
        })

        btnCancelButton.setOnClickListener(object : View.OnClickListener() {

            fun onClick(arg0: View) {
                txtMoney.setText("")                    // 金额文本框
                txtMoney.setHint("0.00")                // 金额文本框设置提示
                txtTime.setText("")                    // 时间文本框
                txtTime.setHint("2017-01-01")            // 时间文本框设置提示
                txtAddress.setText("")                    // 付款方文本框
                txtMark.setText("")                    // 备注文本框
                spType.setSelection(0)                    // 设置类别下拉列表默认选择第一项
            }
        })

        val c = Calendar.getInstance()                // 获取当前系统日期
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)

        updateDisplay()                                        // 显示当前系统时间
        return view
    }


    protected fun onCreateDialog(id: Int): Dialog? {
        when (id) {
            DATE_DIALOG_ID -> return DatePickerDialog(getActivity(), mDateSetListener, mYear, mMonth,
                    mDay)
        }
        return null
    }

    private fun updateDisplay() {                                // 显示设置的时间
        txtTime.setText(StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay))
    }

    companion object {

        protected val DATE_DIALOG_ID = 0
    }
}

