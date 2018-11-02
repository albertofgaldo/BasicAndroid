package com.slashmobility.seleccion.alberto.fernandez.view;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slashmobility.seleccion.alberto.fernandez.R;

import java.util.List;

public class NumsAdapter extends RecyclerView.Adapter<NumsAdapter.ViewHolderNums> implements View.OnClickListener {

    List<Integer> listNums;
    private View.OnClickListener listener;

    public NumsAdapter(List<Integer> listNums){
        this.listNums = listNums;
    }

    @Override
    public ViewHolderNums onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.num_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderNums(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderNums viewHolderNums, int i) {
        if(i%2==0){ //controla el cambio de colores de la lista
            viewHolderNums.textNumber.setBackgroundColor(Color.MAGENTA);
        } else {
            viewHolderNums.textNumber.setBackgroundColor(Color.BLUE);
        }
        viewHolderNums.textNumber.setText(listNums.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return listNums.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderNums extends RecyclerView.ViewHolder {

        TextView textNumber;

        public ViewHolderNums(View itemView) {
            super(itemView);
            textNumber = (TextView)itemView.findViewById(R.id.textViewNumber);
        }
    }
}
