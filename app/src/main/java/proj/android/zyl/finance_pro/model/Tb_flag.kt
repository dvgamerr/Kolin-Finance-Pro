package proj.android.zyl.finance_pro.model

class Tb_flag {
    private var _id: Int = 0
    var flag: String? = null

    constructor() : super() {}

    constructor(id: Int, flag: String) : super() {
        this._id = id
        this.flag = flag
    }

    fun getid(): Int {
        return _id
    }

    fun setid(id: Int) {
        this._id = id
    }
}
