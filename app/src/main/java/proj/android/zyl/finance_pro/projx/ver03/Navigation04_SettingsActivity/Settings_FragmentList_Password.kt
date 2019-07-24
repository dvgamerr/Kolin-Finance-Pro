package proj.android.zyl.finance_pro.projx.ver03.Navigation04_SettingsActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.PwdDAO
import proj.android.zyl.finance_pro.model.Tb_pwd


class Settings_FragmentList_Password : Fragment() {


    internal var now_txtPwd1: EditText
    internal var now_txtPwd2: EditText
    internal var btnSet: Button
    internal var btnsetCancel: Button
    internal var btnClose: Button


    @Override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View {

        val view = inflater.inflate(R.layout.settings_fragmentlist_password, container, false)



        now_txtPwd1 = view.findViewById(R.id.now_txtPwd1)
        now_txtPwd2 = view.findViewById(R.id.now_txtPwd2)
        btnSet = view.findViewById(R.id.btnSet)
        btnsetCancel = view.findViewById(R.id.btnsetCancel)
        btnClose = view.findViewById(R.id.btnClose)


        btnSet.setOnClickListener(object : View.OnClickListener() {            //设置按钮
            fun onClick(arg0: View) {
                val pwdDAO = PwdDAO(getActivity())

                val tb_pwd = Tb_pwd(now_txtPwd1.getText().toString(), now_txtPwd1.getText().toString())

                if (pwdDAO.getCount() === 0) {
                    pwdDAO.add(tb_pwd)
                } else if (now_txtPwd1.getText().toString().equals("") || now_txtPwd2.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "输入的密码为空", Toast.LENGTH_SHORT).show()
                } else if (!now_txtPwd1.getText().toString().equals(now_txtPwd2.getText().toString())) {
                    Toast.makeText(getActivity(), "两次密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show()
                    now_txtPwd1.setText("")
                    now_txtPwd2.setText("")
                } else {
                    pwdDAO.update(tb_pwd)

                    Toast.makeText(getActivity(), "密码设置成功！", Toast.LENGTH_SHORT)
                            .show()
                }
            }

        })


        btnsetCancel.setOnClickListener(object : View.OnClickListener() {

            fun onClick(arg0: View) {
                now_txtPwd1.setText("")
                now_txtPwd1.setHint("请输入新密码")
                now_txtPwd2.setText("")
                now_txtPwd2.setHint("再次输入密码")
            }
        })


        return view
    }


}
