package wms.`fun`.molto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import wms.`fun`.molto.R

class TestAdapter(var onItem : onClick) :RecyclerView.Adapter<TestAdapter.CustomTestAdapter>(){

    class CustomTestAdapter(view: View):RecyclerView.ViewHolder(view){
        var view = view
        var text = view.findViewById<TextView>(R.id.text_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomTestAdapter {
        return CustomTestAdapter(LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false))
    }

    override fun onBindViewHolder(holder: CustomTestAdapter, position: Int) {
        holder.text.text = "ID: $position"
        holder.view.setOnClickListener {
            onItem.onCl()
        }
    }

    override fun getItemCount(): Int {
        return 100
    }

    interface onClick{
        fun onCl()
    }
}