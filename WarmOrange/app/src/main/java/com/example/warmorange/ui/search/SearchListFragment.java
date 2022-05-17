package com.example.warmorange.ui.search;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.FragmentSearchListBinding;
import com.example.warmorange.model.Category;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.applicationData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchListFragment extends Fragment {
    public interface FilterUpdateCallback {
        void updateFilter(String filterTag, String filterGroup, boolean enabled);
    }

    private FragmentSearchListBinding binding;

    public static final String ARG_CATEGORY = "category";
    public static final String ARG_SEARCHFIELD = "searchfield";

    SearchListAdapter adapter;
    private Category category = null;
    private String searchField;
    private List<Product> categoryProducts;
    private List<Product> adapterList;
    private Map<String, List<String>> filterOptions;
    private final Map<String, List<String>> filters = new HashMap<>();

    public SearchListFragment() {
        // Required empty public constructor

        //temp, TODO: remove
        filterOptions = new HashMap<>();
        filterOptions.put("Diagonaal", new ArrayList<String>(){{
            add("45 inch");
            add("55 inch");
            add("56 inch");
        }});
        filterOptions.put("Grootte", new ArrayList<String>(){{
            add("Groot");
            add("Klein");
        }});
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchListFragment.
     */
    public static SearchListFragment newInstance(String category, String searchField) {
        SearchListFragment fragment = new SearchListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        args.putString(ARG_SEARCHFIELD, searchField);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            applicationData.getInstance().getProductData().getCategories().stream()
                    .filter(c -> c.getName().equals(getArguments().getString(ARG_CATEGORY)))
                    .findFirst()
                    .ifPresent(c -> category = c);
            searchField = getArguments().getString(ARG_SEARCHFIELD);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchListBinding.inflate(inflater, container, false);

        categoryProducts = getCategoryProducts();

        adapterList = new ArrayList<>();
        adapter = new SearchListAdapter(adapterList);
        binding.searchItemList.setAdapter(adapter);
        binding.searchItemList.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.listSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchField = s;
                updateWithFilters();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        binding.filterDrawerTitle.setText(getContext().getResources()
                .getString(
                        R.string.filter_category,
                        category == null ? "" : category.getDisplayName()
                ));

        binding.filtersList.setAdapter(new SearchFilterAdapter(filterOptions, this::onUpdateFilter));
        binding.filtersList.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.showFiltersButton.setOnClickListener(v -> {
            int visibility = binding.filterDrawer.getVisibility() == View.VISIBLE
                    ? View.GONE
                    : View.VISIBLE;
            binding.filterDrawer.setVisibility(visibility);
            binding.greyOverlayView.setVisibility(visibility);
        });

        binding.greyOverlayView.setOnClickListener(v -> {
            binding.filterDrawer.setVisibility(View.GONE);
            binding.greyOverlayView.setVisibility(View.GONE);
        });

        updateWithFilters();

        return binding.getRoot();
    }

    private void onUpdateFilter(String filterTag, String filterGroup, boolean enabled) {
        if (!filters.containsKey(filterGroup))
            filters.put(filterGroup, new ArrayList<>());
        if (enabled)
            filters.get(filterGroup).add(filterTag);
        else
            filters.get(filterGroup).remove(filterTag);
        updateWithFilters();
    }

    private void updateHeader() {
        if (category != null)
            binding.searchTitle.setText(getContext().getResources()
                    .getQuantityString(
                            R.plurals.produts_list_category_title,
                            adapterList.size(),
                            adapterList.size(),
                            category.getDisplayName()
                    ));
        else
            binding.searchTitle.setText(getContext().getResources()
                    .getQuantityString(
                            R.plurals.produts_list_title,
                            adapterList.size(),
                            adapterList.size()
                    ));
        binding.listSearchView.setQuery(searchField, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateWithFilters() {
        adapterList.clear();
        adapterList.addAll(categoryProducts);
        // searchfield
        if (searchField != null)
            adapterList.removeIf(p ->
                    !p.getName().toLowerCase(Locale.ROOT)
                            .contains(searchField.toLowerCase(Locale.ROOT))
            );

        // contains attributes
        for (Map.Entry<String, List<String>> tagGroup : filters.entrySet()) {
            if (!tagGroup.getValue().isEmpty())
                adapterList.removeIf(p ->
                        !p.getAttributes().containsKey(tagGroup.getKey())
                                || !tagGroup.getValue().contains(p.getAttributes().get(tagGroup.getKey()))
                );
        }

        // sort
        adapterList.sort((product, t1) -> 0);

        adapter.notifyDataSetChanged();

        if (filterOptions != null && filterOptions.size() > 0) {
            binding.flexboxLayout.setVisibility(View.VISIBLE);
        }
        else
            binding.flexboxLayout.setVisibility(View.GONE);
        updateHeader();
    }

    private List<Product> getCategoryProducts() {
        List<Product> products = applicationData.getInstance().getProductData().getAllProducts();

        // same category
        if (category != null)
            products.removeIf(p -> !p.getType().equals(category.getName()));

        return products;
    }
}