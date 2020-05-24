package br.com.devdiegopirutti.pontocertoapp.Gestor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.devdiegopirutti.pontocertoapp.Model.UsersToGestor
import br.com.devdiegopirutti.pontocertoapp.R


class AdapterColaboradores(var arrayList: ArrayList<UsersToGestor>, var context: Context) : RecyclerView.Adapter<AdapterColaboradores.ViewHolderColaboradores>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderColaboradores {
         val view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return ViewHolderColaboradores(view)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: ViewHolderColaboradores, position: Int) {

    }

    class ViewHolderColaboradores(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}