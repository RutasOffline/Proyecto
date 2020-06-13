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

public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.UsuarioViewHolder> {

    Context context;
    List<Usuario> listaUsuarios;

    public AdaptadorUsuarios(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_usuario, viewGroup, false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder usuarioViewHolder, int i) {
        usuarioViewHolder.tvId.setText(listaUsuarios.get(i).getId_reporte());
        usuarioViewHolder.tvCliente.setText(listaUsuarios.get(i).getCliente());
        usuarioViewHolder.tvReporte.setText(listaUsuarios.get(i).getReporte());
        usuarioViewHolder.tvTecnico.setText(listaUsuarios.get(i).getTecnico());
        usuarioViewHolder.tvCoordenadas.setText(listaUsuarios.get(i).getCoordenadas());
        usuarioViewHolder.tvEstado.setText(listaUsuarios.get(i).getEstado());
        usuarioViewHolder.tvComentarios.setText(listaUsuarios.get(i).getComentarios());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvCliente, tvReporte, tvTecnico, tvCoordenadas, tvEstado, tvComentarios;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvReporte = itemView.findViewById(R.id.tvReporte);
            tvTecnico = itemView.findViewById(R.id.tvTelefono);
            tvCoordenadas = itemView.findViewById(R.id.tvCoordenadas);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvComentarios = itemView.findViewById(R.id.tvComentarios);
        }
    }

    public void filtrar(ArrayList<Usuario> filtroUsuarios){
        this.listaUsuarios = filtroUsuarios;
        notifyDataSetChanged();

    }
}
