package com.example.root.yiplchallenge;

import com.example.root.yiplchallenge.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 7/25/17.
 */
public interface ApiInterface {
    @GET("/posts")
    Call<List<User>> getList();
}