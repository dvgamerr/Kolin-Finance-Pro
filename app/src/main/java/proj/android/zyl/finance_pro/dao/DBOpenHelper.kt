package proj.android.zyl.finance_pro.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, DBNAME, null, VERSION) {


    fun onCreate(db: SQLiteDatabase) {

        db.execSQL("create table tb_outaccount (_id integer primary key,money decimal,time varchar(10)," + "type varchar(10),address varchar(100),mark varchar(200))")
        db.execSQL("create table tb_inaccount (_id integer primary key,money decimal,time varchar(10)," + "type varchar(10),handler varchar(100),mark varchar(200))")
        db.execSQL("create table tb_pwd (username varchaer(20),password varchar(20))")
        db.execSQL("create table tb_flag (_id integer primary key,flag varchar(200))")

        db.execSQL("create table tb_cp_outaccount (_id integer primary key,money decimal,time varchar(10)," + "type varchar(10),address varchar(100),mark varchar(200))")
        db.execSQL("create table tb_cp_inaccount (_id integer primary key,money decimal,time varchar(10)," + "type varchar(10),handler varchar(100),mark varchar(200))")
        db.execSQL("create table tb_cp_pwd (username varchaer(20),password varchar(20))")
        db.execSQL("create table tb_cp_flag (_id integer primary key,flag varchar(200))")
    }

    fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private val VERSION = 1
        private val DBNAME = "account.db"
    }
}
