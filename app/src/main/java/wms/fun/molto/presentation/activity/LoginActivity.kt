package wms.`fun`.molto.presentation.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.solidarchitecture.presentation.viewmodel.ViewModelBuilder
import com.example.solidarchitecture.viewmodel.LoginViewModel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONException
import org.json.JSONObject
import wms.`fun`.molto.R
import wms.`fun`.molto.presentation.views.BaseActivity
import wms.`fun`.molto.utils.*

class LoginActivity : BaseActivity() {


    lateinit var loginViewModel: LoginViewModel
    lateinit var progressDialog: ProgressDialog

     var useEmail: EditText?=null
     var userPassword:EditText?=null
    var loginButton: Button?=null


    override fun setView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Loading...")

        useEmail = findViewById(R.id.login_emailid)
        userPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)

    }

    override fun setViewModel() {
        loginViewModel = ViewModelBuilder.createLoginViewModel(this)


        loginViewModel.loginResponse.observe(this , Observer {
            Toast.makeText(this, "Success" , Toast.LENGTH_SHORT).show()
            loginState.putAny(IS_LOGIN , true)
            processLoginData(it)
            openNewActivity(Dashboard::class.java)
        })

        loginViewModel.isLoading.observe(this, Observer {
            if (!it){
                progressDialog.dismiss()
            }
        })

        loginViewModel.isError.observe(this, Observer {
            Log.d("RESPONSE" , "$it")
            Toast.makeText(this, "Failed Try Again Later" , Toast.LENGTH_SHORT).show()
        })
    }

    override fun setDataSource() {
        loginButton?.setOnClickListener {
            if (isValid()){
                val jsonObject = JSONObject()
                var gsonObject = JsonObject()
                try {
                    jsonObject.put("email", useEmail?.text.toString())
                    jsonObject.put("password", userPassword?.text.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                val jsonParser = JsonParser()
                gsonObject = jsonParser.parse(jsonObject.toString()) as JsonObject
                progressDialog.show()
                loginViewModel.getLoginApiCall(gsonObject)
            }
        }
    }

    override fun bindViewModel() {

    }

    override fun setActionsToSubView() {
    }

    override fun onActivityResume() {

    }

    override fun onActivityDestroy() {

    }

    private fun isValid(): Boolean {
        var err = false
        err = isValidText(userPassword!!)
        err = err and isValidText(useEmail!!)
        return err
    }

}