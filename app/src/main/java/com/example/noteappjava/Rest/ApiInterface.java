package com.example.noteappjava.Rest;

import com.example.noteappjava.models.MainPgo;
import com.example.noteappjava.models.NoteListPgo;
import com.example.noteappjava.models.SubjectListPogo;
//import com.example.user.questionstestapp.Model.AdvPaperPogo;
//import com.example.user.questionstestapp.Model.MainPogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("category_list")
    Call<List<MainPgo>> getcategory ();

    @GET("subject_list?")
    Call<List<SubjectListPogo>> getSubjectlist (@Query("category_id") String categoryid);

    @GET("notes_list?")
    Call<List<NoteListPgo>> getnotelist (@Query("category_id") String categoryid , @Query("subject_id") String subject_id);



}
