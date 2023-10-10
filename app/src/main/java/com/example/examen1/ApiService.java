package com.example.examen1;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("products")
    Call<ProductResponse> getProducts();

    @GET("products/{id}")
    Call<Product> getProductDetails(@Path("id") int productId);

    @GET("products/search")
    Call<ProductResponse> searchProducts(@Query("q") String query);
}
