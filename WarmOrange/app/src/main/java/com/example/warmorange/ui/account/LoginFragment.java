package com.example.warmorange.ui.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.warmorange.R;
import com.example.warmorange.applicationData;
import com.example.warmorange.databinding.FragmentLoginBinding;
import com.example.warmorange.model.LoginData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private LoginData loginData;
    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginData = applicationData.getInstance().getLoginData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        if (loginData.getActiveUser() != null) {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_loginFragment_to_accountFragment);
        }

        binding.loginButton.setOnClickListener(this::login);
        binding.makeAccountButton.setOnClickListener(view ->
                Navigation.findNavController(view)
                        .navigate(R.id.action_loginFragment_to_createAccountFragment)
        );
        return binding.getRoot();
    }

    public void login(View view) {
        String email = binding.editTextTextEmailAddress.getText().toString();
        String password = binding.editTextTextPassword.getText().toString();

        if (!loginData.isAccount(email)) {
            Toast.makeText(getContext(),
                    "Het ingevoerd email adres is nog niet aan een account gebonden.",
                    Toast.LENGTH_SHORT)
                    .show();
        } else if (!loginData.matchingPassword(email, password)) {
            Toast.makeText(getContext(),
                    "Het ingevoerd wachtwoord komt niet overeen met het ingevoerd email adres.",
                    Toast.LENGTH_SHORT)
                    .show();
        } else {
            loginData.setActiveUser(email);
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_accountFragment);
        }
    }
}