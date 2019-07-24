package proj.android.zyl.finance_pro.model

class Tb_pwd {
    var username: String? = null
    var password: String? = null

    constructor() : super() {}

    constructor(username: String, password: String) : super() {
        this.username = username
        this.password = password
    }


}
