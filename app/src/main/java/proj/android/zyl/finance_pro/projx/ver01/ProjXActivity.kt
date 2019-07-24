package proj.android.zyl.finance_pro.projx.ver01

import android.app.Activity
import java.util.ArrayList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

import proj.android.zyl.finance_pro.R


class ProjXActivity : Activity() {
    /** Called when the activity is first created.  */

    internal var gvInfo: GridView
    internal var titles = arrayOf("新增支出", "新增收入", "我的支出", "我的收入", "数据管理", "系统设置", "收支便签", "退出")
    internal var images = intArrayOf(R.drawable.img01xl, R.drawable.img02xl, R.drawable.img03xl, R.drawable.img04xl, R.drawable.img05xl, R.drawable.img06xl, R.drawable.img07xl, R.drawable.img08xl)


    fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver01_main)

        gvInfo = findViewById(R.id.gvInfo)
        val adapter = pictureAdapter(titles, images, this)
        gvInfo.setAdapter(adapter)
        gvInfo.setOnItemClickListener(object : OnItemClickListener() {
            fun onItemClick(arg0: AdapterView<*>, arg1: View, arg2: Int,
                            arg3: Long) {
                var intent: Intent? = null
                when (arg2) {

                    0 -> {
                        intent = Intent(this@ProjXActivity, ProjXAddOutaccount::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        intent = Intent(this@ProjXActivity, ProjXAddInaccount::class.java)
                        startActivity(intent)
                    }
                    2 -> {
                        intent = Intent(this@ProjXActivity, ProjXOutaccountinfo::class.java)
                        startActivity(intent)
                    }
                    3 -> {
                        intent = Intent(this@ProjXActivity, ProjXInaccountinfo::class.java)
                        startActivity(intent)
                    }
                    4 -> {
                        intent = Intent(this@ProjXActivity, ProjXShowinfo::class.java)
                        startActivity(intent)
                    }
                    5 -> {
                        intent = Intent(this@ProjXActivity, ProjXSysset::class.java)
                        startActivity(intent)
                    }
                    6 -> {
                        intent = Intent(this@ProjXActivity, ProjXAddFlag::class.java)
                        startActivity(intent)
                    }
                    7 -> finish()
                }
            }
        })
    }
}

internal class ViewHolder {
    var title: TextView? = null
    var image: ImageView? = null
}

internal class Picture {
    var title: String? = null
    var imageId: Int = 0
        private set

    constructor() : super() {}
    constructor(title: String, imageId: Int) : super() {
        this.title = title
        this.imageId = imageId
    }

    fun setimageId(imageId: Int) {
        this.imageId = imageId
    }

}

internal class pictureAdapter(titles: Array<String>, images: IntArray, context: Context) : BaseAdapter() {
    private val inflater: LayoutInflater
    private val pictures: List<Picture>?

    val count: Int
        get() = pictures?.size() ?: 0


    init {
        pictures = ArrayList<Picture>()
        inflater = LayoutInflater.from(context)
        for (i in images.indices) {
            val picture = Picture(titles[i], images[i])
            pictures!!.add(picture)
        }
    }

    fun getItem(arg0: Int): Object {
        return pictures!![arg0]
    }


    fun getItemId(arg0: Int): Long {
        return arg0.toLong()
    }


    fun getView(arg0: Int, arg1: View?, arg2: ViewGroup): View {
        var arg1 = arg1
        val viewHolder: ViewHolder
        if (arg1 == null) {
            arg1 = inflater.inflate(R.layout.ver01_gvitem, null)
            viewHolder = ViewHolder()
            viewHolder.title = arg1!!.findViewById(R.id.ItemTitle)
            viewHolder.image = arg1!!.findViewById(R.id.ItemImage)
            arg1!!.setTag(viewHolder)
        } else {
            viewHolder = arg1!!.getTag()
        }
        viewHolder.title!!.setText(pictures!![arg0].getTitle())
        viewHolder.image!!.setImageResource(pictures[arg0].getImageId())
        return arg1
    }
}
