package com.example.warmorange.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.CompareFragment;
import com.example.warmorange.R;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.databinding.HomeSuggestionLayoutBinding;
import com.example.warmorange.model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        HomeSuggestionLayoutBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = HomeSuggestionLayoutBinding.bind(view);
        }

        public void setViewData(Product product, HomeProductAdapter adapter) {
            binding.productName.setText(product.getName());
            binding.productImage.setImageResource(product.getImageId());
            binding.ratingBar.setRating((float)product.getAverageReviewScore());
            binding.compareButton.setOnClickListener(v -> {
//                System.out.print("comparing ");
//                System.out.println(product.getName());
                Bundle products = new Bundle();
                products.putStringArray(CompareFragment.ARG_PRODUCT_NAMES,
                        new String[]{product.getName()});
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_compareFragment, products);
            });
        }
    }
    private final List<Product> products;

    public HomeProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_suggestion_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Product product = products.get(position);

        viewHolder.setViewData(product, this);
        viewHolder.itemView.setOnClickListener(v-> {
            applicationData.getInstance().getProductData().setCurrentProduct(product.getName());
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_productPageFragment);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
