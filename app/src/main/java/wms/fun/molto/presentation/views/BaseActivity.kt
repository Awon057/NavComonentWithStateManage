package wms.`fun`.molto.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){

    protected abstract fun setView(savedInstanceState: Bundle?)
    protected abstract fun setViewModel()
    protected abstract fun setDataSource()
    protected abstract fun bindViewModel()
    protected abstract fun setActionsToSubView()
    protected abstract fun onActivityResume()
    protected abstract fun onActivityDestroy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(savedInstanceState)
        setViewModel()
        setDataSource()
        bindViewModel()
    }

    override fun onResume() {
        super.onResume()
        onActivityResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        onActivityDestroy()
    }
}