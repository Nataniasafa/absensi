package com.natania.uas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.natania.uas.R;
import com.natania.uas.adapter.DataAdapter;
import com.natania.uas.model.PersonItem;
import com.natania.uas.remote.APIUtils;
import com.natania.uas.remote.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddUser;
    Button btnGetUser;
    ListView rv;
    DataService dataService;
    List<PersonItem> list = new ArrayList<PersonItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnGetUser = findViewById(R.id.btnGetUserList);
        rv = findViewById(R.id.rv);

        dataService = APIUtils.getDataService();

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        DataActivity.class);
                intent.putExtra("name", "");
                intent.putExtra("date", "");
                intent.putExtra("class", "");
                intent.putExtra("lesson", "");
                intent.putExtra("desc", "");
                startActivity(intent);
            }
        });

        btnGetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserList();
            }
        });

    }

    public void getUserList() {
        Call<List<PersonItem>> call = dataService.getData();
        call.enqueue(new Callback<List<PersonItem>>() {
            @Override
            public void onResponse(Call<List<PersonItem>> call, Response<List<PersonItem>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    rv.setAdapter(new DataAdapter(MainActivity.this,
                            R.layout.list_item, list));
                }
            }

            @Override
            public void onFailure(Call<List<PersonItem>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}