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

import com.example.warmorange.R;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.databinding.FragmentWishlistBinding;
import com.example.warmorange.model.Product;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends Fragment {
    private FragmentWishlistBinding binding;

    public WishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BoughtProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishlistFragment newInstance() {
        return new WishlistFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Product> wishlist = applicationData.getInstance().getLoginData().getActiveUser().getWishlist();

        if (wishlist.isEmpty())
            return inflater.inflate(R.layout.fragment_wishlist_empty, container, false);

        binding = FragmentWishlistBinding.inflate(inflater, container, false);

        WishlistAdapter adapter = new WishlistAdapter(wishlist);

        RecyclerView recyclerView = binding.boughtProductsRecyclerView;

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return binding.getRoot();
    }
}
