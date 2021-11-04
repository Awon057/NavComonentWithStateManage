package wms.`fun`.molto.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.view.View
import android.widget.EditText
import wms.`fun`.molto.domain.entities.LoginResponse

const val BASE_URL = "http://54.95.79.53"

const val ACCESS_TOKEN = "token"
const val BEARER = "Bearer "
const val IS_LOGIN = "is_login"
const val NAME = "name"
const val EMAIL = "email"
const val PHONE = "phone"
const val ROLE_ID = "role_id"



fun isValidText(e: EditText): Boolean {
    var ret = false
    if (e.text.toString().trim { it <= ' ' }.isNotEmpty()) {
        e.error = null
        ret = true
    } else {
        e.error = "Please fill up this field"
    }
    return ret
}


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun <T> Context.openNewActivity(it: Class<T>) {
    startActivity(Intent(this, it))
}

fun SharedPreferences.putAny(tag: String, any: Any) {
    when (any) {
        is Boolean -> edit().putBoolean(tag, any).apply()
        is String -> edit().putString(tag, any).apply()
        is Int -> edit().putInt(tag, any).apply()
    }
}

fun SharedPreferences.remove(tag: String) {
    edit().remove(tag).apply()
}

fun SharedPreferences.clearAll() {
    edit().clear().apply()
}



lateinit var loginState: SharedPreferences
const val APP_SHAREDPREFERANCE = "anobikhr"

fun Context.isNetworkAvailable(ctxContext: Context): Boolean {
    val connectivityManager = ctxContext
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!
        .isConnected || connectivityManager.getNetworkInfo(
        ConnectivityManager.TYPE_MOBILE
    )!!.isConnected
}

fun processLoginData(loginResponse: LoginResponse){
    loginState.putAny(NAME , loginResponse.response.user.name)
    loginState.putAny(EMAIL , loginResponse.response.user.email)
    loginState.putAny(PHONE , loginResponse.response.user.phone)
    loginState.putAny(ROLE_ID , loginResponse.response.user.role_id)
    loginState.putAny(ACCESS_TOKEN , loginResponse.response.token)
}

