package proj.android.zyl.finance_pro.dao

import java.util.ArrayList

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import proj.android.zyl.finance_pro.model.*

class InaccountDAO(context: Context) {
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
            val cursor = db!!
                    .rawQuery("select count(_id) from tb_inaccount", null)
            return if (cursor.moveToNext()) {
                cursor.getLong(0)
            } else 0
        }

    /**
     * 获取收入最大编号
     *
     * @return
     */
    val maxId: Int
        get() {
            db = helper.getWritableDatabase()
            val cursor = db!!.rawQuery("select max(_id) from tb_inaccount", null)
            while (cursor.moveToLast()) {
                return cursor.getInt(0)
            }
            return 0
        }

    init {
        helper = DBOpenHelper(context)
    }

    /**
     * 添加收入信息
     *
     * @param tb_inaccount
     */
    fun add(tb_inaccount: Tb_inaccount) {
        db = helper.getWritableDatabase()
        val sql = "insert into tb_inaccount (_id,money,time,type,handler,mark) values (?,?,?,?,?,?)"
        db!!.execSQL(sql, arrayOf<Object>(tb_inaccount.getid(), tb_inaccount.getMoney(), tb_inaccount.getTime(), tb_inaccount.getType(), tb_inaccount.getHandler(), tb_inaccount.getMark()))
    }

    /**
     * 更新收入信息
     *
     * @param tb_inaccount
     */
    fun update(tb_inaccount: Tb_inaccount) {
        db = helper.getWritableDatabase()
        val sql = "update tb_inaccount set money = ?,time = ?,type = ?,handler = ?,mark = ? where _id = ?"
        db!!.execSQL(sql, arrayOf<Object>(tb_inaccount.getMoney(), tb_inaccount.getTime(), tb_inaccount.getType(), tb_inaccount.getHandler(), tb_inaccount.getMark(), tb_inaccount.getid()))
    }

    /**
     * 查找收入信息
     *
     * @param id
     * @return
     */


    fun find(id: Int): Tb_inaccount? {
        db = helper.getWritableDatabase()
        val sql = "select _id,money,time,type,handler,mark from tb_inaccount where _id = ?"
        val cursor = db!!.rawQuery(sql, arrayOf<String>(String.valueOf(id)))
        return if (cursor.moveToNext()) {
            Tb_inaccount(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("mark")))
        } else null
    }

    /**
     * 刪除收入信息
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
            db!!.execSQL("delete from tb_inaccount where _id in ($sb)",
                    ids)
        }
    }

    /**
     * 获取收入信息
     *
     * @param start
     * 起始位置
     * @param count
     * 每页显示数量
     * @return
     */
    fun getScrollData(start: Int, count: Int): List<Tb_inaccount> {
        val tb_inaccount = ArrayList<Tb_inaccount>()
        db = helper.getWritableDatabase()
        val sql = "select * from tb_inaccount limit ?,?"
        val cursor = db!!.rawQuery(sql,
                arrayOf<String>(String.valueOf(start), String.valueOf(count)))
        while (cursor.moveToNext()) {
            tb_inaccount.add(Tb_inaccount(cursor.getInt(cursor
                    .getColumnIndex("_id")), cursor.getDouble(cursor
                    .getColumnIndex("money")), cursor.getString(cursor
                    .getColumnIndex("time")), cursor.getString(cursor
                    .getColumnIndex("type")), cursor.getString(cursor
                    .getColumnIndex("handler")), cursor.getString(cursor
                    .getColumnIndex("mark"))))
        }
        return tb_inaccount
    }
}
