package com.slashmobility.seleccion.alberto.fernandez.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.slashmobility.seleccion.alberto.fernandez.R;
import com.slashmobility.seleccion.alberto.fernandez.application.ListController;

public class ListActivity extends AppCompatActivity {

    Button buttonAdd, buttonRemo, buttonSortAsce;
    EditText editTextNum;
    RecyclerView recyclerViewNum;
    ListController listController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonRemo = (Button)findViewById(R.id.buttonRemo);
        buttonSortAsce = (Button)findViewById(R.id.buttonSortAsce);
        editTextNum = (EditText)findViewById(R.id.editTextNum);
        recyclerViewNum = (RecyclerView)findViewById(R.id.recyclerViewNum);
        recyclerViewNum.setLayoutManager(new LinearLayoutManager(this));
        changeButtons(); //cambiamos el estado de los butones al inicio

        listController = new ListController();
        final NumsAdapter numsAdapter = new NumsAdapter(listController.getListNums());
        recyclerViewNum.setAdapter(numsAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verifica que el campo sea correcto
                if(editTextNum.getText().toString().isEmpty() || Integer.parseInt(editTextNum.getText().toString())==0){
                    Toast.makeText(ListActivity.this,"Inserta un valor mayor a cero",Toast.LENGTH_SHORT).show();
                }else {
                    if(listController.getListNums().isEmpty()){changeButtons();}//modifica los botones para poder trabajar con los datos
                    listController.addNumToList(Integer.parseInt(editTextNum.getText().toString())); //genera el número de valores aleatorios indicado
                    numsAdapter.notifyDataSetChanged(); //indica al recyclerview que ha habido un cambio
                }
            }
        });

        //limpia la lista
        buttonRemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listController.clearList();
                numsAdapter.notifyDataSetChanged();
                changeButtons();
            }
        });

        //ordena la lista
        buttonSortAsce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listController.getSortAscList();
               numsAdapter.notifyDataSetChanged();
            }
        });

        //borra el elemento seleccionado
        numsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listController.delNumList(recyclerViewNum.getChildAdapterPosition(v));
                numsAdapter.notifyItemRemoved(recyclerViewNum.getChildAdapterPosition(v));
            }
        });
    }

    //función habilita/deshabilita el estado de los botones remover y ordenar
    public void changeButtons(){
        buttonRemo.setEnabled(!buttonRemo.isEnabled());
        buttonSortAsce.setEnabled(!buttonSortAsce.isEnabled());
    }
}
