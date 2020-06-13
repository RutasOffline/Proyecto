package com.example.proyectoreal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorClientes extends RecyclerView.Adapter<AdaptadorClientes.ClienteViewHolder> {


    Context context;
    List<Cliente> listaClientes;

    public AdaptadorClientes(Context context, List<Cliente> listaClientes) {
        this.context = context;
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_cliente, viewGroup, false);
        return new ClienteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder clienteViewHolder, int i) {
        clienteViewHolder.tvId.setText(listaClientes.get(i).getId_cliente());
        clienteViewHolder.tvCliente.setText(listaClientes.get(i).getNombre());
        clienteViewHolder.tvTelefono.setText(listaClientes.get(i).getTelefono());
        clienteViewHolder.tvCoordenadas.setText(listaClientes.get(i).getCoordenadas());


    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvCliente, tvTelefono, tvCoordenadas;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCoordenadas = itemView.findViewById(R.id.tvCoordenadas);

        }
    }

    public void filtrar(ArrayList<Cliente> filtroClientes){
        this.listaClientes = filtroClientes;
        notifyDataSetChanged();

    }
}
