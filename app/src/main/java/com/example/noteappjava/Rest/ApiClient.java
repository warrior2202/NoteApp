package com.example.noteappjava.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL="http://mapi.trycatchtech.com/v1/question_papers_and_notes/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient()
    {
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

