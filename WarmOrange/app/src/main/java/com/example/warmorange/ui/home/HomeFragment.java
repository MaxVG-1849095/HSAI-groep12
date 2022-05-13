package com.example.warmorange.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warmorange.CompareFragment;
import com.example.warmorange.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.warmorange.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // temp, tot nu toe gaat ge zo naar vergelijken
        // Kga het zo doen dat ge gewoon moet toevoegen aan productData.comparisonList
        binding.testButton.setOnClickListener(v -> {
            Bundle products = new Bundle();
            products.putStringArray(CompareFragment.ARG_PRODUCT_NAMES,
                    // Hier alle productnamen
                    new String[]{"Samsung QLED 50Q64A (2021)", "LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021"});
            Navigation.findNavController(v)
                .navigate(R.id.action_navigation_home_to_compareFragment, products);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}