package br.com.devdiegopirutti.pontocertoapp.Historico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.devdiegopirutti.pontocertoapp.Model.HoraEData;
import br.com.devdiegopirutti.pontocertoapp.Model.PontoModel;
//import br.com.devdiegopirutti.pontocertoapp.Model.RegisterDay;
import br.com.devdiegopirutti.pontocertoapp.Model.User;
import br.com.devdiegopirutti.pontocertoapp.R;


public class AdapterHistorico extends RecyclerView.Adapter<AdapterHistorico.ViewHolder> {

    private ArrayList<PontoModel> arrayList = new ArrayList();

    public AdapterHistorico(ArrayList<PontoModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_historico, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dataTxt, entradaTxt, saidaTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dataTxt = itemView.findViewById(R.id.data_txt);
            entradaTxt = itemView.findViewById(R.id.entrada_txt);
            saidaTxt = itemView.findViewById(R.id.saida_txt);
        }


        void bind(HoraEData horaEData) {

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
            Date date = new Date();

            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            Date dateHour = new Date();

            String data = dateFormat.format(date);
            String dataH = hourFormat.format(dateHour);

            dataTxt.setText(data);
            entradaTxt.setText(dataH);
            saidaTxt.setText(dataH);
//            if (entrada) {
//                horaEData.entrada = entradatxt.setText(dataH);
//            } else {
//                horaEData.entrada = saidatxt.setText(dataH);
//            }
        }
    }
}

