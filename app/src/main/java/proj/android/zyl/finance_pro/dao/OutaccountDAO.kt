package proj.android.zyl.finance_pro.dao

import java.util.ArrayList

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import proj.android.zyl.finance_pro.model.*

class OutaccountDAO(context: Context) {
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
            val cursor = db!!.rawQuery("select count(_id) from tb_outaccount", null)
            return if (cursor.moveToNext()) {
                cursor.getLong(0)
            } else 0
        }

    /**
     * 获取支出最大编号
     *
     * @return
     */
    val maxId: Int
        get() {
            db = helper.getWritableDatabase()
            val cursor = db!!.rawQuery("select max(_id) from tb_outaccount", null)
            while (cursor.moveToLast()) {
                return cursor.getInt(0)
            }
            return 0
        }

    init {
        helper = DBOpenHelper(context)
    }

    /**
     * 添加支出信息
     *
     * @param tb_outaccount
     */
    fun add(tb_outaccount: Tb_outaccount) {
        db = helper.getWritableDatabase()
        val sql = "insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?,?,?)"
        db!!.execSQL(sql, arrayOf<Object>(tb_outaccount.getid(), tb_outaccount.getMoney(), tb_outaccount.getTime(), tb_outaccount.getType(), tb_outaccount.getAddress(), tb_outaccount.getMark()))
    }

    /**
     * 更新支出信息
     *
     * @param tb_outaccount
     */
    fun update(tb_outaccount: Tb_outaccount) {
        db = helper.getWritableDatabase()
        val sql = "update tb_outaccount set money = ?,time = ?,type = ?,address = ?,mark = ? where _id = ?"
        db!!.execSQL(sql, arrayOf<Object>(tb_outaccount.getMoney(), tb_outaccount.getTime(), tb_outaccount.getType(), tb_outaccount.getAddress(), tb_outaccount.getMark(), tb_outaccount.getid()))
    }

    /**
     * 查找支出信息
     *
     * @param id
     * @return
     */
    fun find(id: Int): Tb_outaccount? {
        db = helper.getWritableDatabase()
        val sql = "select _id,money,time,type,address,mark from tb_outaccount where _id = ?"
        val cursor = db!!
                .rawQuery(sql, arrayOf<String>(String.valueOf(id)))
        return if (cursor.moveToNext()) {
            Tb_outaccount(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("mark")))
        } else null
    }

    /**
     * 刪除支出信息
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
            db!!.execSQL("delete from tb_outaccount where _id in ($sb)",
                    ids)
        }
    }

    /**
     * 获取支出信息
     *
     * @param start
     * 起始位置
     * @param count
     * 每页显示数量
     * @return
     */
    fun getScrollData(start: Int, count: Int): List<Tb_outaccount> {
        val tb_outaccount = ArrayList<Tb_outaccount>()
        db = helper.getWritableDatabase()
        val sql = "select * from tb_outaccount limit ?,?"
        val cursor = db!!.rawQuery(sql,
                arrayOf<String>(String.valueOf(start), String.valueOf(count)))
        while (cursor.moveToNext()) {
            tb_outaccount.add(Tb_outaccount(cursor.getInt(cursor
                    .getColumnIndex("_id")), cursor.getDouble(cursor
                    .getColumnIndex("money")), cursor.getString(cursor
                    .getColumnIndex("time")), cursor.getString(cursor
                    .getColumnIndex("type")), cursor.getString(cursor
                    .getColumnIndex("address")), cursor.getString(cursor
                    .getColumnIndex("mark"))))
        }
        return tb_outaccount
    }
}
