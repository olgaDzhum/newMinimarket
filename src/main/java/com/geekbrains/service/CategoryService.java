package com.geekbrains.service;

import com.geekbrains.dto.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("market/api/v1/categories/{id}")
    Call<Category> getCategory(@Path("id") int id);
}
