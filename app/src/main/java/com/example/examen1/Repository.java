package com.example.examen1;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private final ApiService apiService;

    public Repository() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public LiveData<List<Product>> getProducts() {
        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        apiService.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e("Repository", "Error loading products", t);
            }
        });
        return products;
    }

    public LiveData<Product> getProductDetails(int productId) {
        MutableLiveData<Product> product = new MutableLiveData<>();
        apiService.getProductDetails(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    product.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("Repository", "Error loading product details", t);
            }
        });
        return product;
    }

    public LiveData<List<Product>> searchProducts(String query) {
        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        apiService.searchProducts(query).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e("Repository", "Error searching products", t);
            }
        });
        return products;
    }
}
