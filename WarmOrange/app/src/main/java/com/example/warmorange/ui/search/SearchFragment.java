package com.example.warmorange.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.warmorange.R;
import com.example.warmorange.databinding.FragmentSearchBinding;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.applicationData;

import java.util.HashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        binding.searchCategoriesList.setAdapter(new SearchAdapter(applicationData.getInstance().getProductData().getCategories()));
        binding.searchCategoriesList.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle b = new Bundle();
                b.putString(SearchListFragment.ARG_SEARCHFIELD, query);
                Navigation.findNavController(container).navigate(R.id.action_searchFragment_to_searchListFragment, b);
                Log.d("ja", "zeker");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return binding.getRoot();
    }

    private String[] getAutoCompleteSuggestions() {
        Set<String> suggestions = new HashSet<>();
        for (Product p : applicationData.getInstance().getProductData().getAllProducts()) {
            suggestions.add(p.getType());
            suggestions.add(p.getName());
        }

        return suggestions.toArray(new String[0]);
    }
}