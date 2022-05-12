package com.example.warmorange;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.warmorange.databinding.FragmentWarrantyBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarrantyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarrantyFragment extends Fragment {
    private FragmentWarrantyBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WarrantyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WarrantyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WarrantyFragment newInstance(String param1, String param2) {
        WarrantyFragment fragment = new WarrantyFragment();
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
        binding = FragmentWarrantyBinding.inflate(inflater, container, false);

        ListView lv = (ListView) binding.warrantyList;
        warrantyAdapter wa = new warrantyAdapter(getContext(), applicationData.getInstance().getProductData());
        lv.setAdapter(wa);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}