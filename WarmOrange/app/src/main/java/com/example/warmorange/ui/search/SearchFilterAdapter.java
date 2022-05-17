package com.example.warmorange.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.loader.ResourcesProvider;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.SearchCategoryLayoutBinding;
import com.example.warmorange.databinding.SearchFilterGroupLayoutBinding;
import com.example.warmorange.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class SearchFilterAdapter extends RecyclerView.Adapter<SearchFilterAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SearchFilterGroupLayoutBinding binding;
        SearchListFragment.FilterUpdateCallback callback;

        public ViewHolder(View view, SearchListFragment.FilterUpdateCallback callback) {
            super(view);
            this.callback = callback;
            binding = SearchFilterGroupLayoutBinding.bind(view);
        }

        public void setViewData(String groupName, List<String> groupFilters) {
            binding.filterGroupTitle.setText(groupName);
            for (String filterName : groupFilters) {
                AppCompatCheckBox checkBox = buildCheckBox(itemView.getContext(), filterName, groupName);
                binding.filterGroupVerticalLayout.addView(checkBox);
            }
        }

        @SuppressLint("RestrictedApi")
        public AppCompatCheckBox buildCheckBox(Context context, String filterName, String filterGroup) {
            AppCompatCheckBox checkBox = new AppCompatCheckBox(context);
            checkBox.setText(filterName);
            //https://stackoverflow.com/a/40016860
            ColorStateList colors = new ColorStateList(
                    new int[][] {
                            new int[] { -android.R.attr.state_checked }, // unchecked
                            new int[] {  android.R.attr.state_checked }  // checked
                    },
                    new int[] {
                            ContextCompat.getColor(context, R.color.darkgrey),
                            ContextCompat.getColor(context, R.color.orange1)
                    }
            );
            checkBox.setSupportButtonTintList(colors);
            checkBox.setOnCheckedChangeListener((compoundButton, b) ->
                    callback.updateFilter(filterName, filterGroup, b)
            );
            return checkBox;
        }
    }

    private final List<Map.Entry<String, List<String>>> filters;
    private final SearchListFragment.FilterUpdateCallback callback;

    /**
     * Initialize the dataset of the Adapter.
     *
     * by RecyclerView.
     */
    public SearchFilterAdapter(Map<String, List<String>> filters,
                               SearchListFragment.FilterUpdateCallback callback) {
        this.filters = new ArrayList<>();
        if (filters != null)
            this.filters.addAll(filters.entrySet());
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_filter_group_layout, viewGroup, false);

        return new ViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        String groupName = filters.get(position).getKey();
        List<String> groupFilters = filters.get(position).getValue();

        viewHolder.setViewData(groupName, groupFilters);
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }
}
