package proj.android.zyl.finance_pro.model

class Tb_inaccount {
    private var _id: Int = 0
    var money: Double = 0.toDouble()
    var time: String? = null
    var type: String? = null
    var handler: String? = null
    var mark: String? = null

    constructor() : super() {}

    constructor(id: Int, money: Double, time: String, type: String,
                handler: String, mark: String) : super() {
        this._id = id
        this.money = money
        this.time = time
        this.type = type
        this.handler = handler
        this.mark = mark
    }

    fun getid(): Int {
        return _id
    }

    fun setid(id: Int) {
        this._id = id
    }
}
