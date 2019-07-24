package proj.android.zyl.finance_pro.dao

import java.util.ArrayList

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import proj.android.zyl.finance_pro.model.*

class FlagDAO(context: Context) {
    private val helper: DBOpenHelper
    private var db: SQLiteDatabase? = null

    /**
     * 获取总记录数
     *
     * @return
     */
    val count: Long
        get() {
            db = helper.getWritableDatabase()
            val cursor = db!!.rawQuery("select count(_id) from tb_flag", null)
            return if (cursor.moveToNext()) {
                cursor.getLong(0)
            } else 0
        }

    /**
     * 获取便签最大编号
     *
     * @return
     */
    val maxId: Int
        get() {
            db = helper.getWritableDatabase()
            val cursor = db!!.rawQuery("select max(_id) from tb_flag", null)
            while (cursor.moveToLast()) {
                return cursor.getInt(0)
            }
            return 0
        }

    init {
        helper = DBOpenHelper(context)
    }

    /**
     * 添加便签信息
     *
     * @param tb_flag
     */
    fun add(tb_flag: Tb_flag) {
        db = helper.getWritableDatabase()
        val sql = "insert into tb_flag (_id,flag) values (?,?)"
        db!!.execSQL(sql, arrayOf<Object>(tb_flag.getid(), tb_flag.getFlag()))
    }

    fun update(tb_flag: Tb_flag) {
        db = helper.getWritableDatabase()
        val sql = "update tb_flag set flag = ? where _id = ?"
        db!!.execSQL(sql, arrayOf<Object>(tb_flag.getFlag(), tb_flag.getid()))
    }

    /**
     * 更新便签信息
     *
     * @param tb_flag
     */
    /**
     * 查找便签信息
     *
     * @param id
     * @return
     */
    fun find(id: Int): Tb_flag? {
        db = helper.getWritableDatabase()
        val sql = "select _id,flag from tb_flag where _id = ?"
        val cursor = db!!.rawQuery(sql, arrayOf<String>(String.valueOf(id)))
        return if (cursor.moveToNext()) {
            Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("flag")))
        } else null
    }

    /**
     * 刪除便签信息
     *
     * @param ids
     */
    fun detele(vararg ids: Integer) {
        if (ids.size > 0) {
            val sb = StringBuffer()
            for (i in ids.indices) {
                sb.append('?').append(',')
            }
            sb.deleteCharAt(sb.length() - 1)
            db = helper.getWritableDatabase()
            db!!.execSQL("delete from tb_flag where _id in ($sb)",
                    ids)
        }
    }

    /**
     * 获取便签信息
     *
     * @param start
     * 起始位置
     * @param count
     * 每页显示数量
     * @return
     */
    fun getScrollData(start: Int, count: Int): List<Tb_flag> {
        val lisTb_flags = ArrayList<Tb_flag>()
        db = helper.getWritableDatabase()
        val sql = "select * from tb_flag limit ?,?"
        val cursor = db!!.rawQuery(sql,
                arrayOf<String>(String.valueOf(start), String.valueOf(count)))
        while (cursor.moveToNext()) {
            lisTb_flags.add(Tb_flag(cursor.getInt(cursor
                    .getColumnIndex("_id")), cursor.getString(cursor
                    .getColumnIndex("flag"))))
        }
        return lisTb_flags
    }
}
