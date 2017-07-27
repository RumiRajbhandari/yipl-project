package com.example.root.yiplchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);


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
                userAdapter=new UserAdapter(userList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });


    }
}
