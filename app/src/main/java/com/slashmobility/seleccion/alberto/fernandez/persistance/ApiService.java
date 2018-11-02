package com.slashmobility.seleccion.alberto.fernandez.persistance;
import com.slashmobility.seleccion.alberto.fernandez.domain.Post;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("get")
    Call <Post> getPost();
}
