package com.example.warmorange.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.warmorange.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.model.applicationData;
import com.example.warmorange.databinding.FragmentHomeBinding;
import com.example.warmorange.model.Product;
import com.example.warmorange.ui.search.SearchListFragment;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        List<Product> products = applicationData.getInstance().getProductData().getAllProducts();

        HomeProductAdapter adapter = new HomeProductAdapter(products);

        RecyclerView recyclerView = binding.homeSuggestionsRecyclerView;

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        binding.searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle b = new Bundle();
                b.putString(SearchListFragment.ARG_SEARCHFIELD, query);
                    Navigation.findNavController(container).navigate(R.id.action_navigation_home_to_searchListFragment, b);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        binding.gotoCategoriesButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_searchFragment));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}