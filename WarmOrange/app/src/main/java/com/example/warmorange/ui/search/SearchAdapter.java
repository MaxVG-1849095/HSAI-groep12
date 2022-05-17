package com.example.warmorange.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.SearchCategoryLayoutBinding;
import com.example.warmorange.model.Category;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SearchCategoryLayoutBinding binding;

        public ViewHolder(View view) {
            super(view);

            binding = SearchCategoryLayoutBinding.bind(view);
        }

        public void setViewData(Category category) {
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

        viewHolder.setViewData(cat);
        viewHolder.itemView.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putString(SearchListFragment.ARG_CATEGORY, cat.getName());
            Navigation.findNavController(v).navigate(R.id.action_searchFragment_to_searchListFragment, b);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
