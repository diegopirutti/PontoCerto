package br.com.devdiegopirutti.pontocertoapp.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.devdiegopirutti.pontocertoapp.Model.Ponto
import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario
import br.com.devdiegopirutti.pontocertoapp.R
import java.util.*

class DayDataAdapter(var ponto: ArrayList<Ponto>) : RecyclerView.Adapter<DayDataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(LayoutInflater
            .from(parent.context).inflate(R.layout.recycler_day_register, parent, false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(ponto[position])
    }

    override fun getItemCount() = ponto.size

    fun clear() {
        ponto.clear()
    }

    fun addAllRegisters() {
        ponto.addAll(ponto)
    }

    fun updateList(newPonto: Ponto) {
        ponto.add(newPonto)
        var pontoDiario: PontoDiario?
        notifyDataSetChanged()
    }

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var dataView: TextView? = null
        var registerView: TextView? = null

        fun bind(itemView: Ponto) {

            //   dataView?.text = itemView.data
            //   registerView?.text = itemView.entrada


        }
    }
}