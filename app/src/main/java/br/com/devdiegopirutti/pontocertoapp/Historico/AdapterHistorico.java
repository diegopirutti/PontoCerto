package br.com.devdiegopirutti.pontocertoapp.Historico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario;
import br.com.devdiegopirutti.pontocertoapp.R;


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
        list.addAll(list);
        notifyDataSetChanged();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    TextView datatxt;
    TextView entradatxt;
    TextView saidatxt;
    TextView entradaseg;
    TextView saidaseg;

    ViewHolder(@NonNull View itemView) {
        super(itemView);
        datatxt = (itemView).findViewById(R.id.data_txt);
        entradatxt = (itemView).findViewById(R.id.txtEntValorRetornado);
        saidatxt = (itemView).findViewById(R.id.txtSaidaValorRetornado);
        entradaseg = (itemView).findViewById(R.id.txtEntValorRetornadoSeg);
        saidaseg = (itemView).findViewById(R.id.txtSaidaValorRetornadoSeg);
    }

    void bind(PontoDiario pontoDiario) {

        entradatxt.setText(String.valueOf(pontoDiario.getPontos().get(0).getData()));
        saidatxt.setText(String.valueOf(pontoDiario.getPontos().get(1).getData()));
        entradaseg.setText(String.valueOf(pontoDiario.getPontos().get(2).getData()));
        saidaseg.setText(String.valueOf(pontoDiario.getPontos().get(3).getData()));

    }


}



