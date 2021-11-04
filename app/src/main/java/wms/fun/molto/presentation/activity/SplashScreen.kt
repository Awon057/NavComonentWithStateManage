package wms.`fun`.molto.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import wms.`fun`.molto.R
import wms.`fun`.molto.utils.IS_LOGIN
import wms.`fun`.molto.utils.isNetworkAvailable
import wms.`fun`.molto.utils.loginState
import wms.`fun`.molto.utils.openNewActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        if (!isNetworkAvailable(applicationContext)){
            //connectionErrorDailogShow()
        }else{
            Handler().postDelayed({
                if (loginState.getBoolean(IS_LOGIN, false)) {
                    openNewActivity(Dashboard::class.java)
                } else {
                    openNewActivity(LoginActivity::class.java)
                }
                finish()
            }, 2 * 1000.toLong())
        }
    }

}