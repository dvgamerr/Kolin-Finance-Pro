package proj.android.zyl.finance_pro.dao

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import proj.android.zyl.finance_pro.model.*

class PwdDAO(context: Context) {
    private val helper: DBOpenHelper
    private var db: SQLiteDatabase? = null

    val count: Long
        get() {
            db = helper.getWritableDatabase()
            val cursor = db!!.rawQuery("select count(username) from tb_pwd", null)
            return if (cursor.moveToNext()) {
                cursor.getLong(0)
            } else 0
        }

    init {
        helper = DBOpenHelper(context)
    }

    /**
     * 添加密码信息
     *
     * @param tb_pwd
     */

    fun add(tb_pwd: Tb_pwd) {
        db = helper.getWritableDatabase()
        val sql = "insert into tb_pwd (username,password) values (?,?)"
        try {
            db!!.execSQL(sql,
                    arrayOf<Object>(tb_pwd.getUsername(), tb_pwd.getPassword()))
        } catch (e: SQLException) {

        }

    }


    /**
     * 设置密码信息
     *
     * @param tb_pwd
     */
    fun update(tb_pwd: Tb_pwd) {
        db = helper.getWritableDatabase()
        val sql = "update tb_pwd set password = ?"
        db!!.execSQL(sql, arrayOf<Object>(tb_pwd.getPassword()))
    }

    /**
     * 查找密码信息
     *
     * @return
     */
    fun find(username: String): Tb_pwd? {
        db = helper.getWritableDatabase()
        try {
            val cursor = db!!.rawQuery("select password from tb_pwd where username=\"$username\"", null)
            if (cursor.moveToNext()) {
                return Tb_pwd(username, cursor.getString(cursor
                        .getColumnIndex("password")))
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return null
    }

    fun has_username(username: String): Boolean {
        db = helper.getWritableDatabase()
        val cursor = db!!.rawQuery("select username from tb_pwd", null)
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex("username")).equals(username)) {
                return true
            }
        }
        return false
    }
}
	

	







