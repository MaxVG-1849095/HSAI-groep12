package com.example.warmorange.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.warmorange.CompareFragment;
import com.example.warmorange.R;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.databinding.HomeSuggestionLayoutBinding;
import com.example.warmorange.model.Product;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        HomeSuggestionLayoutBinding binding;
        Context context;
        View v;
        public ViewHolder(View view) {
            super(view);
            v = view;
            binding = HomeSuggestionLayoutBinding.bind(view);
            context = view.getContext();
        }

        public void setViewData(Product product, HomeProductAdapter adapter) {
            binding.productName.setText(product.getName());
            binding.productImage.setImageResource(product.getImageId());
            binding.ratingBar.setRating((float)product.getAverageReviewScore());
            List<String> tags = new ArrayList<>();
            for(int i = 0; i<3;i++){
                tags.add(product.getTagList().get(i));
            }

            ProductTagAdapter tagAdapter = new ProductTagAdapter(tags);

            RecyclerView recyclerView = binding.productTags;

            recyclerView.setAdapter(tagAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

            if (product.isInComparison()) {
                binding.compareButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.red));
            }
            else {
                binding.compareButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.green));
            }
            binding.compareButton.setOnClickListener(v -> {
                if (product.isInComparison()) {
                    applicationData.getInstance().getProductData().removeFromComparison(product);
                    binding.compareButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.green));
                    Snackbar snackbar = Snackbar.make(v, product.getName() + " " +  context.getResources().getString(R.string.removedFromComparison), Snackbar.LENGTH_LONG);
                    snackbar.setAction(context.getResources().getString(R.string.revertChanges), s_v -> {
                        applicationData.getInstance().getProductData().addToComparisonList(product);
                        binding.compareButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.red));
                    });
                    snackbar.show();
                } else {
                    if(applicationData.getInstance().getProductData().addToComparisonList(product)){
                        binding.compareButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.red));
                        if(applicationData.getInstance().getProductData().getComparisonList().size() == 2){
                            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_compareFragment);
                        }

                    }
                    else{
                        if(applicationData.getInstance().getProductData().getComparisonList().size() ==2){
                            Snackbar.make(v, R.string.compareFull, Snackbar.LENGTH_SHORT).show();
                        }
                        else{
                            Snackbar.make(v, R.string.wrongCompType, Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
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
