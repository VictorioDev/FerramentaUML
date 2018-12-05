package com.aula.victoriozansavio.umlp5.activity.util;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Victorio Zansavio on 05/12/2018.
 */

public class RetrofitBuilder {

    public static Retrofit build(Converter.Factory converter){
        String BASE_URL = "http://192.168.0.100:3000/api/";
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converter)
                .build();
    }

}
