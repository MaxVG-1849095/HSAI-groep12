package com.example.warmorange.ui.demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.warmorange.R;
import com.example.warmorange.databinding.FragmentDemoBinding;
import com.example.warmorange.databinding.FragmentHomeBinding;
import com.example.warmorange.model.Demo;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.ui.home.HomeProductAdapter;
import com.example.warmorange.ui.home.HomeViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemoFragment extends Fragment {
    FragmentDemoBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DemoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DemoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DemoFragment newInstance(String param1, String param2) {
        DemoFragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDemoBinding.inflate(inflater, container, false);

        List<Demo> demos = applicationData.getInstance().getDemoData().getAllDemos();

        DemoAdapter adapter = new DemoAdapter(demos);

        RecyclerView recyclerView = binding.bookingRecyclerView;

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        if (applicationData.getInstance().getDemoData().getAllDemos().size() == 0) {
            binding.noDemos.setVisibility(View.VISIBLE);
        } else {
            binding.noDemos.setVisibility(View.GONE);
        }
        binding.bookProductButton.setOnClickListener(v -> {
            if (applicationData.getInstance().getLoginData().getActiveUser() != null) {
                Navigation.findNavController(v).navigate(R.id.action_demoFragment_to_wishlistFragment);
            } else {
                Toast.makeText(getContext(), R.string.loginDemo, Toast.LENGTH_SHORT).show();
            }
        });
        binding.bookWizzardButton.setOnClickListener(v -> {
            if (applicationData.getInstance().getLoginData().getActiveUser() == null) {
                Toast.makeText(getContext(), R.string.loginDemo, Toast.LENGTH_SHORT).show();
            } else if (applicationData.getInstance().getwData().getWizardList().size() <= 0) {
                Toast.makeText(getContext(), R.string.noWizards, Toast.LENGTH_SHORT).show();
            } else {
                BookWizardDialog dialog = new BookWizardDialog(getContext(), binding.getRoot());
                dialog.showDialog();
            }
        });

        return binding.getRoot();
    }
}