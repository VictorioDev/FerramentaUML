package com.aula.victoriozansavio.umlp5.util;

import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Victorio Zansavio on 05/12/2018.
 */

public class RetrofitBuilder {

    public static Retrofit build(Converter.Factory converter){
        String BASE_URL = "http://192.168.0.108:3000/api/";
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converter)
                .build();
    }

}
