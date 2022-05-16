package com.example.warmorange;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.warmorange.databinding.FragmentQrBinding;
import com.example.warmorange.model.applicationData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QrFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QrFragment extends Fragment {

    private FragmentQrBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QrFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QrFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QrFragment newInstance(String param1, String param2) {
        QrFragment fragment = new QrFragment();
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
        // Inflate the layout for this fragment

        binding = FragmentQrBinding.inflate(inflater,container,false);
        View view = inflater.inflate(R.layout.fragment_qr, container,false);

            Button btnTest = (Button) binding.testButtonQr;
        btnTest.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            applicationData.getInstance().getwData().setWizardType("Television");
            Navigation.findNavController(view).navigate(R.id.action_qrFragment_to_productPageFragment);
        }
    }
        );
        System.out.println("Qr created");
        return binding.getRoot();
    }
}