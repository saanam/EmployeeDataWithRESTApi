package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.api.EmployeeAPI;
import com.example.api.model.Employee;
import com.example.api.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployeeActivity extends AppCompatActivity {

    private final static String Base_URL = "http://dummy.restapiexample.com/api/v1/";
    EditText etSearch;
    TextView tvOutputsearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etSearch= findViewById(R.id.etSearch);
        btnSearch= findViewById(R.id.btnSearch);
        tvOutputsearch = findViewById(R.id.tvOutputsearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    loadData();

            }
        });
    }

    private void loadData()
    {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Employee> listCall = employeeAPI.getEmployeeById(Integer.parseInt(etSearch.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {


                Toast.makeText(SearchEmployeeActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();


                String data = "";
                data += "Employee id:" + response.body().getId() + "\n";
                data += "Employee name:" + response.body().getEmployee_name() + "\n";
                data += "Employee name:" + response.body().getEmployee_age() + "\n";
                data += "Employee name:" + response.body().getEmployee_salary() + "\n";
                data += "----------" + "\n";
                tvOutputsearch.setText(data);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("Error", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(SearchEmployeeActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
