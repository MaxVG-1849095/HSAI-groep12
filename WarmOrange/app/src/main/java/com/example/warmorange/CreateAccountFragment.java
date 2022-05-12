package com.example.warmorange;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.warmorange.databinding.FragmentCreateAccountBinding;
import com.example.warmorange.databinding.FragmentLoginBinding;
import com.example.warmorange.model.Account;
import com.example.warmorange.model.LoginData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAccountFragment extends Fragment {
    FragmentCreateAccountBinding binding;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CreateAccountFragment.
     */
    public static CreateAccountFragment newInstance() {
        return new CreateAccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false);

        binding.button.setOnClickListener(this::onCreateAccount);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onCreateAccount(View view) {
        String name  = binding.editTextName.getText().toString();
        String email = binding.editTextEmail.getText().toString();
        String pw1   = binding.editTextPassword.getText().toString();
        String pw2   = binding.editTextPasswordRepeat.getText().toString();

        if (validate(name, email, pw1, pw2)) {
            Account newAccount = new Account(name, "", pw1, email);
            LoginData.addAccount(newAccount);
            applicationData.getInstance().getLoginData().setActiveUser(email);

            Navigation.findNavController(view).navigate(R.id.action_createAccountFragment_to_accountFragment);
            Toast.makeText(getContext(),
                    "Welkom bij WarmOrange, " + name + "!",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private boolean validate(String name, String email, String pw1, String pw2) {
        if (!isValidName(name)) {
            binding.editTextName.setError("Voer een geldige naam in a.u.b.");
            return false;
        }

        if (!isValidEmail(email)) {
            binding.editTextEmail.setError("Gebruik een geldig email adres a.u.b.");
            return false;
        }

        if (!isValidPassword(pw1)) {
            binding.editTextPassword.setError("Gebruik een geldig en sterk wachtwoord a.u.b.");
            return false;
        }

        if (!pw1.equals(pw2)) {
            binding.editTextPasswordRepeat.setError("Wachtwoorden komen niet overeen.");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        // TODO: validate email
        return true;
    }

    private boolean isValidName(String name) {
        // TODO: validate name
        return true;
    }

    private boolean isValidPassword(String name) {
        // TODO: validate password
        return true;
    }
}