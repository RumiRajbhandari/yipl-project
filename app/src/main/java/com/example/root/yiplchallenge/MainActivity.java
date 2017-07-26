package com.example.root.yiplchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.root.yiplchallenge.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = "https://jsonplaceholder.typicode.com";
    String TAG = "TAG";
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = new ArrayList<>();

        if (API_KEY.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter api", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiInterface apiService = ClientApi.getClient().create(ApiInterface.class);
        Call<List<User>> call = apiService.getList();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList.addAll(response.body());
                //Log.e(TAG, "onResponse: ");
                for (User user : userList
                        ) {
                    Log.e(TAG, "onCreate: " + user);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });


    }
}
