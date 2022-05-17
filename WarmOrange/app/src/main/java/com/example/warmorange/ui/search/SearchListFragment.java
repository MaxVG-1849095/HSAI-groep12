package com.example.warmorange.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.warmorange.R;
import com.example.warmorange.databinding.FragmentSearchBinding;
import com.example.warmorange.databinding.FragmentSearchListBinding;
import com.example.warmorange.model.Category;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.applicationData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchListFragment extends Fragment {
    private FragmentSearchListBinding binding;

    public static final String ARG_CATEGORY = "category";

    private Category category = null;
    private String[] tags;

    public SearchListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchListFragment newInstance(String category) {
        SearchListFragment fragment = new SearchListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
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
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchListBinding.inflate(inflater, container, false);

        List<Product> products = getFilteredProducts();
        if (category != null)
            binding.searchTitle.setText(String.format(getContext().getString(R.string.produts_list_category_title), products.size(), category.getDisplayName()));
        else
            binding.searchTitle.setText(String.format(getContext().getString(R.string.produts_list_title), products.size()));
        binding.searchItemList.setAdapter(new SearchListAdapter(products));
        binding.searchItemList.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    private List<Product> getFilteredProducts() {
        List<Product> products = applicationData.getInstance().getProductData().getAllProducts();

        // same category
        if (category != null)
            products.removeIf(p -> !p.getType().equals(category.getName()));
        // contains tags
//        if (tags  != null)
//            products.removeIf(p -> {
//                for (String tag : p.getTags())
//                    if (tag)
//            })

        return products;
    }
}