package br.com.devdiegopirutti.pontocertoapp.Historico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.devdiegopirutti.pontocertoapp.Model.Ponto;
import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario;
import br.com.devdiegopirutti.pontocertoapp.R;

import static android.text.format.DateFormat.format;


public class AdapterHistorico extends RecyclerView.Adapter<ViewHolder> {

    public ArrayList<PontoDiario> arrayList = new ArrayList();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_historico,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void adicionarItens(List<PontoDiario> list) {
        arrayList.addAll(list);
        notifyDataSetChanged();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    private TextView datatxt;
    private TextView entradatxt;
    private TextView saidatxt;
    private TextView entradaseg;
    private TextView saidaseg;

    ViewHolder(@NonNull View itemView) {
        super(itemView);
        datatxt = (itemView).findViewById(R.id.datatxt);
        entradatxt = (itemView).findViewById(R.id.entrada1txt);
        saidatxt = (itemView).findViewById(R.id.saida1txt);
        entradaseg = (itemView).findViewById(R.id.entrada2txt);
        saidaseg = (itemView).findViewById(R.id.saida2txt);
    }

    void bind(PontoDiario pontoDiario) {
        datatxt.setText(pontoDiario.getData());
        entradatxt.setText(timeStampConverter(pontoDiario.getPontos().get(0)));
        saidatxt.setText(timeStampConverter(pontoDiario.getPontos().get(1)));
        entradaseg.setText(timeStampConverter(pontoDiario.getPontos().get(2)));
        saidaseg.setText(timeStampConverter(pontoDiario.getPontos().get(3)));
    }

    private String timeStampConverter(Ponto ponto) {
        return (ponto.getEntrada() ? "Entrada " : "Saída ") + format("HH:mm:ss", new Date(new Timestamp(ponto.getData()).getTime())).toString();
    }


}



