package com.example.examen1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private final Repository repository;

    public ProductViewModel() {
        repository = new Repository();
    }

    public LiveData<List<Product>> getProducts() {
        return repository.getProducts();
    }

    public LiveData<Product> getProductDetails(int productId) {
        return repository.getProductDetails(productId);
    }

    public LiveData<List<Product>> searchProducts(String query) {
        return repository.searchProducts(query);
    }
}
