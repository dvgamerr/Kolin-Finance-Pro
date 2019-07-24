package proj.android.zyl.finance_pro.projx.ver03.Navigation04_SettingsActivity

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.PwdDAO
import proj.android.zyl.finance_pro.dao.SyssetDAO
import proj.android.zyl.finance_pro.projx.ver01.ProjXLogin


class Settings_FragmentList_Settings : Fragment() {


    internal var btnDelete: Button
    internal var btnBackup: Button
    internal var btnRecovery: Button

    @Override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle): View {
        val view = inflater.inflate(R.layout.settings_fragmentlist_settings, container, false)


        btnDelete = view.findViewById(R.id.btnDelete)

        btnBackup = view.findViewById(R.id.btnBackup)

        btnRecovery = view.findViewById(R.id.btnRecovery)



        btnDelete.setOnClickListener(object : View.OnClickListener() {
            fun onClick(arg0: View) {

                Toast.makeText(getActivity(), "这是一步危险操作！", Toast.LENGTH_SHORT).show()

                val builder = AlertDialog.Builder(getActivity())
                builder.setTitle("警告")
                builder.setMessage("你确定要进行初始化吗？")
                builder.setPositiveButton("确定", object : DialogInterface.OnClickListener() {

                    fun onClick(dialog: DialogInterface, which: Int) {
                        val SyssetDAO = SyssetDAO(getActivity())
                        SyssetDAO.deleteAllUserDate()
                        Toast.makeText(getActivity(), "初始化数据成功！", Toast.LENGTH_SHORT).show()
                    }
                })
                builder.create().show()
            }
        })


        btnBackup.setOnClickListener(object : View.OnClickListener() {
            fun onClick(arg0: View) {
                val SyssetDAO = SyssetDAO(getActivity())
                SyssetDAO.saveDate()
                Toast.makeText(getActivity(), "数据备份成功！", Toast.LENGTH_SHORT).show()

            }
        })

        btnRecovery.setOnClickListener(object : View.OnClickListener() {
            fun onClick(arg0: View) {


                val builder = AlertDialog.Builder(getActivity())
                builder.setTitle("警告")
                builder.setMessage("你确定要恢复数据吗？")
                builder.setPositiveButton("确定", object : DialogInterface.OnClickListener() {

                    fun onClick(dialog: DialogInterface, which: Int) {
                        val SyssetDAO = SyssetDAO(getActivity())
                        SyssetDAO.getDate()
                        Toast.makeText(getActivity(), "数据恢复成功！", Toast.LENGTH_SHORT).show()
                    }
                })
                builder.create().show()
            }
        })

        return view
    }

}
