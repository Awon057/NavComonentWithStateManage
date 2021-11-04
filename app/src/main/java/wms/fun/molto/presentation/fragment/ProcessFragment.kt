package wms.`fun`.molto.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wms.`fun`.molto.R
import wms.`fun`.molto.adapter.HomeViewModel
import wms.`fun`.molto.adapter.TestAdapter

class ProcessFragment : Fragment() , TestAdapter.onClick {


    private val viewModel by navGraphViewModels<HomeViewModel>(R.id.processFragment)

    var recycleview: RecyclerView?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_process , container , false)

        recycleview = view.findViewById<RecyclerView>(R.id.recycleview)

        recycleview?.layoutManager = LinearLayoutManager(activity)
        val adapter = TestAdapter(this)
        recycleview?.adapter = adapter

        if (viewModel.listState != null) {
            recycleview?.layoutManager?.onRestoreInstanceState(viewModel.listState)
            viewModel.listState = null
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listState = recycleview?.layoutManager?.onSaveInstanceState()
    }

    override fun onCl() {

        Log.d("ITEM" , "ITEM")

        val navController = findNavController()
        val bundle = bundleOf("type" to "")
        if (navController.currentDestination?.id == R.id.processFragment) {
            navController.navigate(R.id.action_Home_to_Profile, bundle
            )
        }
    }
}