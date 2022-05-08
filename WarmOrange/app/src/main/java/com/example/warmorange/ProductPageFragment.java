package com.example.warmorange;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.warmorange.databinding.FragmentProductPageBinding;
import com.example.warmorange.databinding.FragmentQrBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductPageFragment extends Fragment {
    private FragmentProductPageBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductPageFragment newInstance(String param1, String param2) {
        ProductPageFragment fragment = new ProductPageFragment();
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
        binding = FragmentProductPageBinding.inflate(inflater,container,false);
        View view = inflater.inflate(R.layout.fragment_product_page, container,false);

        ImageView productImage = (ImageView) binding.imageView;
        productImage.setImageResource(R.drawable.ic_baseline_account_circle_24);
        Button wishlistButton = (Button) binding.wishlistButton;
        wishlistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("added to wishlist");
            }
        });
        Button ARButton = (Button) binding.ARButton;
        ARButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("AR button");
            }
        });
        ImageButton ARInfoButton = (ImageButton) binding.ARInfoButton;
        ARInfoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("AR info button");
            }
        });
        TextView NameText = (TextView) binding.nameText;
        NameText.setText("Naam van product");
        TextView SpecText = (TextView) binding.tagText;
        SpecText.setText("Specs van product");
        Button videoButton = (Button) binding.videoButton;
        videoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("video button");
            }
        });

        TextView availableText = (TextView) binding.availableText;
        availableText.setText("Beschikbaar in Hasselt");

        TextView inclText = (TextView) binding.includedListText;
        inclText.setText("-ding 1 \n-ding2");
        TextView wizardText = (TextView) binding.WizardText;
        wizardText.setText("Nog niet zeker welke ... je wilt? Doe de wizard!");
        Button wizardButton = (Button) binding.wizardButton;
        wizardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                applicationData.getInstance().getwData().setWizardType("Television");
                Navigation.findNavController(view).navigate(R.id.action_productPageFragment_to_navigation_wizardFragment);
            }
        });

        return binding.getRoot();
    }
}