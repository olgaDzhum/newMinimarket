package com.geekbrains.service;

import com.geekbrains.dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductService {
    @GET("market/api/v1/products")
    Call<List<Product>> getAllProducts();

    @GET("market/api/v1/products/{id}")
    Call<Product> getProductById(@Path("id") int id);

    @POST("market/api/v1/products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @DELETE("market/api/v1/products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @PUT("market/api/v1/products")
    Call<Product> modifyProduct(@Body Product createProductRequest);

}
