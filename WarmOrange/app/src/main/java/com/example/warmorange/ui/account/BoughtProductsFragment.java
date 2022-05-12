package com.example.warmorange.ui.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.warmorange.applicationData;
import com.example.warmorange.databinding.FragmentBoughtProductsBinding;
import com.example.warmorange.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoughtProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoughtProductsFragment extends Fragment {
    private FragmentBoughtProductsBinding binding;

    public BoughtProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BoughtProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoughtProductsFragment newInstance() {
        return new BoughtProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoughtProductsBinding.inflate(inflater, container, false);

        List<Product> p = new ArrayList<>();
        p.add(applicationData.getInstance().getProductData().getProduct("Apple magic keyboard"));
        p.add(applicationData.getInstance().getProductData().getProduct("OPPO Find X5 Pro"));
        p.add(applicationData.getInstance().getProductData().getProduct("Iphone 13"));
        p.add(applicationData.getInstance().getProductData().getProduct("Samsung QLED 50Q64A (2021)"));
        p.add(applicationData.getInstance().getProductData().getProduct("SteelSeries Apex Pro Gaming"));

        RecyclerView recyclerView = binding.boughtProductsRecyclerView;
//        BoughtProductsAdapter adapter = new BoughtProductsAdapter(applicationData.getInstance().getLoginData().getActiveUser().getOwnedProducts());
        BoughtProductsAdapter adapter = new BoughtProductsAdapter(p);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return binding.getRoot();
    }
}
