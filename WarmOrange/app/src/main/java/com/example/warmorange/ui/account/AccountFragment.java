package com.example.warmorange.ui.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.warmorange.R;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.databinding.FragmentAccountBinding;
import com.example.warmorange.model.LoginData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;

    private LoginData loginData;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginData = applicationData.getInstance().getLoginData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hideBackButton();
        binding = FragmentAccountBinding.inflate(inflater, container, false);

        binding.boughtProductsButton.setOnClickListener(v -> Navigation.findNavController(v)
                        .navigate(R.id.action_accountFragment_to_warrantyFragment)
        );

        binding.customerCardButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putLong(CustomerCardFragment.ARG_CUSTOMER_ID, loginData.getActiveUser().getAccountId());
            Navigation.findNavController(v)
                    .navigate(R.id.action_accountFragment_to_customerCardFragment, bundle);
        });

        binding.wishlistButton.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_accountFragment_to_wishlistFragment));

        binding.logoutButton.setOnClickListener(v -> {
            loginData.clearActiveUser();
            Navigation.findNavController(v)
                    .navigate(R.id.action_accountFragment_to_navigation_home);
            Toast.makeText(getContext(),
                    "Succesvol uitgelogd",
                    Toast.LENGTH_SHORT)
                    .show();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void hideBackButton() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity == null) return;
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setDisplayHomeAsUpEnabled(false);
    }
}