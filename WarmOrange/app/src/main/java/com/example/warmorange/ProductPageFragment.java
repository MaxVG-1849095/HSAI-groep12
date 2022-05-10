package com.example.warmorange;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warmorange.databinding.FragmentProductPageBinding;
import com.example.warmorange.databinding.FragmentQrBinding;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.Review;

import org.w3c.dom.Text;

import java.util.Vector;

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


    private applicationData appData = applicationData.getInstance();
    private Product product = appData.getProductData().getCurrentProduct();

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
        productImage.setImageResource(product.getImageId());
        Button wishlistButton = (Button) binding.wishlistButton;
        wishlistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getActivity(),product.getName() + "toegevoegd aan wishlist", Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
                Navigation.findNavController(view).navigate(R.id.action_productPageFragment_to_mapFragment);
            }
        });
        Button ARButton = (Button) binding.ARButton;
        ARButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getActivity(),"Augmented reality gestart!", Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        });
        ImageButton ARInfoButton = (ImageButton) binding.ARInfoButton;
        ARInfoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getActivity(),"Info over de augmented reality", Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        });
        TextView NameText = (TextView) binding.nameText;
        NameText.setText(product.getName());
        TextView SpecText = (TextView) binding.tagText;

        String specsString ="";
        for(String j:product.getTags()){
            specsString+="|" + j;
        }
        specsString +="|";
        SpecText.setText(specsString);
        Button videoButton = (Button) binding.videoButton;
        videoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getActivity(),"Hier hoort een video met uitleg", Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        });

        TextView availableText = (TextView) binding.availableText;
        if(product.isAvailable()){
            availableText.setText("Beschikbaar in Hasselt");
        }
        else{
            availableText.setText("Niet beschikbaar in Hasselt");
        }

        TextView inclText = (TextView) binding.includedListText;
        String inclString = "";
        for(String i : product.getIncluded()){
            inclString+="-" + i + "\n";
        }
        inclText.setText(inclString);
        TextView wizardText = (TextView) binding.WizardText;
        wizardText.setText("Nog niet zeker welke " + product.getType() + " je wilt? Doe de wizard!");
        Button wizardButton = (Button) binding.wizardButton;
        wizardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                applicationData.getInstance().getwData().setWizardType(product.getType());
                Navigation.findNavController(view).navigate(R.id.action_productPageFragment_to_navigation_wizardFragment);
            }
        });
        ListView reviewlist = (ListView) binding.reviewList;
        Vector<Review> productreviews = product.getReviews();
        String[] reviewStrings = new String[productreviews.size()+1];
        reviewStrings[0] = "Reviews: (Average review score: " + product.getAverageReviewScore() + "/5)";
        int index = 1;
        String reviewString;
        for(Review r:productreviews){
            reviewString = "";
            reviewString+=r.getText();
            reviewString+=" | ";
            reviewString+=r.getRating();
            reviewString+="/5";
            reviewStrings[index] = reviewString;
            index++;
        }
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,reviewStrings);
        reviewlist.setAdapter(arr);
        return binding.getRoot();
    }
}