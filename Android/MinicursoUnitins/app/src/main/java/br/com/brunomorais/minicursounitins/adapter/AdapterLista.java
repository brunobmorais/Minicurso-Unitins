package br.com.brunomorais.minicursounitins.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.brunomorais.minicursounitins.MainActivity;
import br.com.brunomorais.minicursounitins.R;
import br.com.brunomorais.minicursounitins.VagaActivity;
import br.com.brunomorais.minicursounitins.bean.VagaEmprego;


// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class AdapterLista extends RecyclerView.Adapter<AdapterLista.ViewHolder>{
    private ArrayList<VagaEmprego> mDataset;
    private Context context;
    public View view;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvempresa;
        public TextView txtCidade;
        public TextView txtCargo;
        public TextView txtSalario;
        public ImageView imgCargo;

        private int position;

        private VagaEmprego vagaEmprego;

        public ViewHolder(View v) {
            super(v);
            view = v;
            tvempresa = (TextView) v.findViewById(R.id.tvItemListaContratante);
            txtCidade = (TextView) v.findViewById(R.id.tvItemListaNomeCidade);
            txtCargo = (TextView) v.findViewById(R.id.tvItemListaNomeCargo);
            txtSalario = (TextView) v.findViewById(R.id.tvItemListaSalario);
            imgCargo = (ImageView) v.findViewById(R.id.imgCargo);


            //EVENTO ONCLICK
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VagaActivity.class);
                    intent.putExtra("cargo",mDataset.get(position).getCargo());
                    intent.putExtra("empresa",mDataset.get(position).getEmpresa());
                    intent.putExtra("salario",mDataset.get(position).getSalario());
                    intent.putExtra("cidade",mDataset.get(position).getCidade());
                    intent.putExtra("imagem",mDataset.get(position).getImagem());
                    intent.putExtra("datapostagem",mDataset.get(position).getDatapostagem());

                    context.startActivity(intent);

                }
            });

        }
    }

    public void add(int position, VagaEmprego item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void add(VagaEmprego ve) {
        mDataset.add(ve);
        notifyItemInserted(mDataset.size() - 1);
    }

    public void addAll(ArrayList<VagaEmprego> veList) {
        for (VagaEmprego ve : veList) {
            this.add(ve);
        }
        notifyDataSetChanged();
    }

    public void remove(final VagaEmprego item, View viewItem) {
        final int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);

    }

    public void remove(VagaEmprego ve) {
        int position = mDataset.indexOf(ve);
        if (position > -1) {
            mDataset.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        add(new VagaEmprego());
    }

    public VagaEmprego getItem(int position) {
        return mDataset.get(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterLista(Context context2) {
        this.context = context2;
        this.mDataset = new ArrayList<VagaEmprego>();
    }


    @Override
    public AdapterLista.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.position = position;
        //Armazena objeto Vaga de Emprego
        holder.vagaEmprego = mDataset.get(position);
        final VagaEmprego vagaEmprego = mDataset.get(position);
        holder.tvempresa.setText(mDataset.get(position).getEmpresa());
        holder.txtCidade.setText(mDataset.get(position).getCidade());
        holder.txtCargo.setText(mDataset.get(position).getCargo());
        holder.txtSalario.setText(mDataset.get(position).getSalario());

        Picasso.with(context).load("http://brunomorais.com.br/api/minicurso/img/"+mDataset.get(position).getImagem())
                .into(holder.imgCargo);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
