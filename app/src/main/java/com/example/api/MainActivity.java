package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput= findViewById(R.id.tvoutput);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Employee> empList = response.body();
                for (Employee employee : empList)
                {
                    String data = "";
                    data += "Employee name:" + employee.getEmployee_name() + "\n";
                    data += "Employee name:" + employee.getEmployee_age() + "\n";
                    data += "Employee name:" + employee.getEmployee_salary() + "\n";
                    data += "----------" + "\n";
                    tvOutput.append(data);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Error", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
