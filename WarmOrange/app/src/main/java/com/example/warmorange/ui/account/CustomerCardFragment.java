package com.example.warmorange.ui.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.warmorange.databinding.FragmentCustomerCardBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerCardFragment extends Fragment {
    private FragmentCustomerCardBinding binding;

    public static final String ARG_CUSTOMER_ID = "customerId";

    private long customerId = -1;

    public CustomerCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CustomerCardFragment.
     */
    public static CustomerCardFragment newInstance(long param1) {
        CustomerCardFragment fragment = new CustomerCardFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_CUSTOMER_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customerId = getArguments().getLong(ARG_CUSTOMER_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomerCardBinding.inflate(inflater, container, false);

        if (customerId != -1)
            binding.customerNumberText.setText(Long.toString(customerId));
        else
            binding.customerNumberText.setText("Niet gevonden...");

        return binding.getRoot();
    }
}