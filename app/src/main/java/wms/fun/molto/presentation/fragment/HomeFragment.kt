package wms.`fun`.molto.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.view.*
import wms.`fun`.molto.R

class HomeFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home , container , false)

        view.button_packging.setOnClickListener {
            open("packeging")
        }

        view.button_shipment.setOnClickListener {
            open("shipment")
        }

        return view
    }

    private fun open(type:String){
        val navController = findNavController()
        val bundle = bundleOf("type" to type)
        if (navController.currentDestination?.id == R.id.homeFragment) {
            navController.navigate(R.id.action_Home_to_ProcessFragment, bundle
            )
        }
    }


}