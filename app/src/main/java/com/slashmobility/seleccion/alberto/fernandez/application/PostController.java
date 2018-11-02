package com.slashmobility.seleccion.alberto.fernandez.application;

import com.slashmobility.seleccion.alberto.fernandez.domain.Post;
import com.slashmobility.seleccion.alberto.fernandez.persistance.ApiService;
import com.slashmobility.seleccion.alberto.fernandez.utils.MyListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostController {

    private String origin;
    private MyListener ml;
    private Retrofit retrofit;
    private String url = "http://httpbin.org/";
    private ApiService apiService;

    public PostController(MyListener ml) {//crea la conexión en el constructor
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        this.ml = ml;
    }

    public void getData() {
        //mapea el dato recogido en call
        Call <Post> call = apiService.getPost();

        call.enqueue(new Callback<Post>() {//encola los datos recogidos
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    ml.callback("Problema al devolver los datos");
                    return;
                }
                setOrigin(response.body().getOrigin());//recoge el dato origin y lo guarda en la variable origin
                ml.callback("Correcto");
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                ml.callback("Problema con el servidor");
            }
        });
    }

    //invierte el string origin
    public String reverseOrigin() {
        String reverse = "";
        if(origin!=null) {
            reverse="";
            for (int x = origin.length() - 1; x >= 0; x--) {
                reverse = reverse + origin.charAt(x);
            }
        }
        return reverse;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }
}
