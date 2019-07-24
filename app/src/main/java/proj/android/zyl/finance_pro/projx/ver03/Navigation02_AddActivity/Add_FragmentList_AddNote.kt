package proj.android.zyl.finance_pro.projx.ver03.Navigation02_AddActivity

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import proj.android.zyl.finance_pro.R
import proj.android.zyl.finance_pro.dao.FlagDAO
import proj.android.zyl.finance_pro.model.Tb_flag
import proj.android.zyl.finance_pro.projx.ver01.ProjXAddFlag

class Add_FragmentList_AddNote : Fragment() {

    private var txt_flag: EditText? = null
    private var btn_save: Button? = null


    fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle): View {
        val view = inflater.inflate(R.layout.add_fragmentlist_addnote, container, false)

        super.onCreate(savedInstanceState)

        //getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);

        txt_flag = view.findViewById(R.id.txtFlag)
        btn_save = view.findViewById(R.id.btnflagSave)

        btn_save!!.setOnClickListener(object : View.OnClickListener() {
            fun onClick(v: View) {
                val flag_str = txt_flag!!.getText().toString()
                if (!flag_str.trim().isEmpty()) {
                    val flagDAO = FlagDAO(getActivity())
                    val tb_flag = Tb_flag(flagDAO.getMaxId() + 1, flag_str)
                    flagDAO.add(tb_flag)
                    Toast.makeText(getActivity(), "数据添加成功！", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(getActivity(), "你还没有输入便签信息", Toast.LENGTH_SHORT).show()
                }
            }
        })


        return view
    }
}