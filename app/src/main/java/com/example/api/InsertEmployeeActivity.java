package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.api.api.EmployeeAPI;
import com.example.api.model.Employee;
import com.example.api.model.EmployeeCUD;
import com.example.api.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertEmployeeActivity extends AppCompatActivity {

    private final static String BASE_URL = "http://dummy.restapiexample.com/api/v1/";
    private EditText etName, etAge, etSalary;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_employee);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etSalary = findViewById(R.id.etSalary);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register()
    {
        String name = etName.getText().toString();
        Float salary = Float.parseFloat(etSalary.getText().toString());
        int age = Integer.parseInt(etAge.getText().toString());

        EmployeeCUD employeeCUD = new EmployeeCUD(name, salary, age);
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Void> voidCall = employeeAPI.registerEmployee(employeeCUD);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Toast.makeText(InsertEmployeeActivity.this, "Register" , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Error", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(InsertEmployeeActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
