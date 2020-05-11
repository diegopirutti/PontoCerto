package br.com.devdiegopirutti.pontocertoapp.Historico;

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

import br.com.devdiegopirutti.pontocertoapp.R;
import br.com.devdiegopirutti.pontocertoapp.Model.DataClasses;

public class AdapterHistorico extends RecyclerView.Adapter<ViewHolder> {

    public ArrayList<DataClasses.PontoModel> arrayList = new ArrayList();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_historico,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.bind(arrayList.get(position));
//
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void adicionarItens(List<DataClasses.PontoModel> list){
        list.addAll(list);
        notifyDataSetChanged();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    TextView datatxt, entradatxt, saidatxt;

    ViewHolder(@NonNull View itemView) {
        super(itemView);
        datatxt = (itemView).findViewById(R.id.data_txt);
        entradatxt = (itemView).findViewById(R.id.txtEntValorRetornado);
        saidatxt = (itemView).findViewById(R.id.txtSaidaValorRetornado);
    }

    void bind(DataClasses.HoraEData horaEData) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        Date date = new Date();

        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date dateHour = new Date();

        String data = dateFormat.format(date);
        String dataH = hourFormat.format(dateHour);

        //datatxt.setText(data);
//        if (entrada) {
//            horaEData.entrada = entradatxt.setText(dataH);
//        } else {
//            horaEData.entrada = saidatxt.setText(dataH);
        }
    }
//}


