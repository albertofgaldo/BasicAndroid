package com.slashmobility.seleccion.alberto.fernandez.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.slashmobility.seleccion.alberto.fernandez.R;
import com.slashmobility.seleccion.alberto.fernandez.application.PostController;
import com.slashmobility.seleccion.alberto.fernandez.utils.MyListener;

public class MainActivity extends AppCompatActivity implements MyListener {

    Button buttonCallServ, buttonGoToList;
    TextView textViewResp, textViewRespReve;
    PostController postController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCallServ = (Button)findViewById(R.id.buttonCallServ);
        buttonGoToList = (Button)findViewById(R.id.buttonGoToList);
        textViewResp = (TextView)findViewById(R.id.textViewResp);
        textViewRespReve = (TextView)findViewById(R.id.textViewRespReve);

        postController = new PostController(this);

        buttonCallServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postController.getData(); //llama a la función getData donde se realiza el get
            }
        });

        buttonGoToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListActivity = new Intent(MainActivity.this, ListActivity.class);
                startActivity(ListActivity);
            }
        });
    }

    @Override
    public void callback(String result) {
        if (result.equalsIgnoreCase("Correcto")){ //comprobamos el resutado del response a través del listener
            textViewResp.setText("Response " + postController.getOrigin()); //inserta el valor origin previamente solicitado en el campo correspondiente
            textViewRespReve.setText("Response " + postController.reverseOrigin()); //inserta el valor origin invertido en el campo correspondiente
        }else{
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }

    }
}
