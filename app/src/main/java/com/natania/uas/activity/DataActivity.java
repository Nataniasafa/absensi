package com.natania.uas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.natania.uas.R;
import com.natania.uas.model.PersonItem;
import com.natania.uas.remote.APIUtils;
import com.natania.uas.remote.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataActivity extends AppCompatActivity {
    DataService dataService;
    EditText edtName, edtDate,edtClass,edtLesson, edtDesc, edtId;
    Button btnSave, btnDel;
    TextView txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = findViewById(R.id.edt_name);
        edtDate = findViewById(R.id.edt_date);
        edtClass = findViewById(R.id.edt_class);
        edtLesson = findViewById(R.id.edt_lesson);
        edtDesc = findViewById(R.id.edt_desc);
        btnSave = findViewById(R.id.btn_save);
        btnDel = findViewById(R.id.btn_delete);
        edtId = findViewById(R.id.edt_id);
        txtId = findViewById(R.id.txt_id);

        dataService = APIUtils.getDataService();

        Bundle extras = getIntent().getExtras();
        String dataName = extras.getString("name");
        String dataDate = extras.getString("date");
        String dataClass = extras.getString("class");
        String dataLesson = extras.getString("lesson");
        String dataDesc = extras.getString("desc");
        final String dataID = extras.getString("id");

        edtId.setText(dataID);
        edtName.setText(dataName);
        edtDate.setText(dataDate);
        edtClass.setText(dataClass);
        edtLesson.setText(dataLesson);
        edtDesc.setText(dataDesc);

        if (dataID != null && dataID.trim().length() > 0){
            edtId.setFocusable(false);
        } else {
            txtId.setVisibility(View.INVISIBLE);
            edtId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String date = edtDate.getText().toString();
                String kelas = edtClass.getText().toString();
                String lesson = edtLesson.getText().toString();
                String desc = edtDesc.getText().toString();

                if (dataID != null && dataID.trim().length() > 0){
                    updateData(Integer.parseInt(dataID), name, date, kelas,lesson, desc);
                } else {
                    addProduct(name, date, kelas, lesson, desc);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(Integer.parseInt(dataID));
                Intent intent = new Intent(DataActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void addProduct(String name, String date,String kelas,String lesson, String desc) {
        Call<PersonItem> call = dataService.addData(name, date,kelas,lesson, desc);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DataActivity.this, "data added succesfully",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DataActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void updateData(int id, String name, String date, String kelas,String lesson, String desc) {
        Call<PersonItem> call = dataService.updateData(id, name, date, kelas, lesson, desc);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DataActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DataActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void deleteData(int id){
        Call<PersonItem> call = dataService.deleteProduct(id);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DataActivity.this, "Product deleted",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DataActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}