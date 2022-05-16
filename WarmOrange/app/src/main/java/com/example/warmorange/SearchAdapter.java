package com.example.warmorange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.SearchCategoryLayoutBinding;
import com.example.warmorange.model.Category;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.databinding.WishlistItemLayoutBinding;
import com.example.warmorange.model.Account;
import com.example.warmorange.model.Product;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SearchCategoryLayoutBinding binding;

        public ViewHolder(View view) {
            super(view);

            binding = SearchCategoryLayoutBinding.bind(view);
        }

        public void setViewData(SearchAdapter adapter, Category category) {
            binding.categoryName.setText(category.getDisplayName());
            binding.categoryImage.setImageResource(category.getImageId());
        }
    }

    private final List<Category> categories;

    /**
     * Initialize the dataset of the Adapter.
     *
     * by RecyclerView.
     */
    public SearchAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_category_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Category cat = categories.get(position);

        viewHolder.setViewData(this, cat);
        viewHolder.itemView.setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.action_wishlistFragment_to_productPageFragment);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
