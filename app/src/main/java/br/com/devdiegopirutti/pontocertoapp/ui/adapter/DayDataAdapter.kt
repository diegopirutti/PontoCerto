package br.com.devdiegopirutti.pontocertoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.devdiegopirutti.pontocertoapp.R
import br.com.devdiegopirutti.pontocertoapp.model.DataClasses
import java.util.*

class DayDataAdapter(var register: ArrayList<DataClasses.RegisterDay>) : RecyclerView.Adapter<DayDataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(LayoutInflater
            .from(parent.context).inflate(R.layout.recycler_day_register, parent, false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(register[position])
    }

    override fun getItemCount() = register.size

    fun clear() {
        register.clear()
    }

    fun addAllRegisters() {
        register.addAll(register)
    }

    fun updateList(newRegister: DataClasses.RegisterDay) {
        register.add(newRegister)
        notifyDataSetChanged()
    }



    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var dataView: TextView? = null
        var registerView: TextView? = null

        fun bind(itemView: DataClasses.RegisterDay) {

            dataView?.text = itemView.data
            registerView?.text = itemView.registro
        }

    }
}