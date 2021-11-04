package wms.`fun`.molto.presentation.activity

import android.os.Bundle
import android.os.Handler
import android.os.Parcel
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.content_main.*
import wms.`fun`.molto.R


class Dashboard : AppCompatActivity(), NavController.OnDestinationChangedListener{

    private var listOfBottomBar =
        setOf(
            R.id.homeFragment,
            R.id.historyFragment)

    lateinit var navController: NavController
    private var backPressedOnce = false



    override fun onBackPressed() {
        if (navController.graph.startDestination == navController.currentDestination?.id) {
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }

            backPressedOnce = true
            Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(2000) {
                backPressedOnce = false
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)

        var navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottom_navigatin_view, navHostFragment.navController)
        navController.addOnDestinationChangedListener(this)

        toolbar?.setNavigationOnClickListener(View.OnClickListener { navController.popBackStack() })

    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

        if (destination.id in listOfBottomBar){
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        val title = when(destination.id){

            R.id.homeFragment -> resources.getString(R.string.home)
            R.id.profileFragment -> resources.getString(R.string.profile)
            R.id.historyFragment -> resources.getString(R.string.history)
            R.id.processFragment -> resources.getString(R.string.product_pick)
            else -> ""

        }
        supportActionBar?.title = title
    }
}