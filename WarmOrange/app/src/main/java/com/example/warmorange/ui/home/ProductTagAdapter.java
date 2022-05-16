package com.example.warmorange.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.HomeSuggestionLayoutBinding;
import com.example.warmorange.databinding.ProductTagLayoutBinding;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.applicationData;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ProductTagAdapter extends RecyclerView.Adapter<ProductTagAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ProductTagLayoutBinding binding;
        Context context;

        public ViewHolder(View view) {
            super(view);
            binding = ProductTagLayoutBinding.bind(view);
            context = view.getContext();
        }

        public void setViewData(String tag, ProductTagAdapter adapter) {
            binding.tag.setText(tag);
        }
    }
    private final List<String> tags;

    public ProductTagAdapter(List<String> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_tag_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String tag = tags.get(position);

        viewHolder.setViewData(tag, this);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }
}
