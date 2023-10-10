package com.example.examen1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;

public class ProductDetails extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewPrice;
    private TextView textViewDiscount;
    private TextView textViewRating;
    private TextView textViewStock;
    private TextView textViewBrand;
    private TextView textViewCategory;
    private ImageView imageViewThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewDiscount = findViewById(R.id.textViewDiscount);
        textViewRating = findViewById(R.id.textViewRating);
        textViewStock = findViewById(R.id.textViewStock);
        textViewBrand = findViewById(R.id.textViewBrand);
        textViewCategory = findViewById(R.id.textViewCategory);
        imageViewThumbnail = findViewById(R.id.imageViewThumbnail);

        int productId = getIntent().getIntExtra("productId", -1);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productViewModel.getProductDetails(productId).observe(this, this::updateUi);
    }

    private void updateUi(Product product) {
        if (product != null) {
            textViewTitle.setText(product.getTitle());
            textViewDescription.setText(product.getDescription());
            textViewPrice.setText(String.format("Price: $%.2f", product.getPrice()));
            textViewDiscount.setText(String.format("Discount: %.2f%%", product.getDiscountPercentage()));
            textViewRating.setText(String.format("Rating: %.2f★", product.getRating()));
            textViewStock.setText(String.format("Stock: %d", product.getStock()));
            textViewBrand.setText(String.format("Brand: %s", product.getBrand()));
            textViewCategory.setText(String.format("Category: %s", product.getCategory()));
            Glide.with(this).load(product.getThumbnail()).into(imageViewThumbnail);
        }
    }
}
