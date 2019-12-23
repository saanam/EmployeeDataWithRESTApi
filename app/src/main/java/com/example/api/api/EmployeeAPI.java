package com.example.api.api;

import com.example.api.model.Employee;
import com.example.api.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @GET("employee/{empid}")
    Call<Employee> getEmployeeById(@Path("empid") int empid);

    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);

}
