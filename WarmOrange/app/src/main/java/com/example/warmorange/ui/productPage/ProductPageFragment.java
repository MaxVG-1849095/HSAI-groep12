package com.example.warmorange.ui.productPage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.warmorange.R;
import com.example.warmorange.databinding.FragmentProductPageBinding;
import com.example.warmorange.model.Account;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.Review;
import com.example.warmorange.model.applicationData;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;

import java.text.DecimalFormat;
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

        if(applicationData.getInstance().getLoginData().getActiveUser() != null){
            if(applicationData.getInstance().getLoginData().getActiveUser().productInWishlist(product.getName())){
                binding.wishlistButton.setEnabled(false);
                binding.wishlistButton.setText(R.string.inWishlist);
            }
        }

        ImageView productImage = (ImageView) binding.imageView;
        productImage.setImageResource(product.getImageId());
        Button wishlistButton = (Button) binding.wishlistButton;
        wishlistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Assumes wishlist doesn't already contain the product
                Account activeUser = appData.getLoginData().getActiveUser();
                if (activeUser == null) {
                    Snackbar.make(getView(), R.string.loginWishlist, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                activeUser.getWishlist().add(product);
                Snackbar mySnackbar = Snackbar.make(getView(), product.getName() + "toegevoegd aan wishlist.", Snackbar.LENGTH_SHORT);
                mySnackbar.setAction(R.string.undo_string,new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        applicationData.getInstance().getLoginData().getActiveUser().removeProduct(product.getName());
                        Snackbar undoSnackbar = Snackbar.make(getView(), R.string.removeWishlist, Snackbar.LENGTH_SHORT);
                        undoSnackbar.show();
                        binding.wishlistButton.setEnabled(true);
                        binding.wishlistButton.setText(R.string.add_to_wishlist);
                        applicationData.getInstance().getLoginData().getActiveUser().removeProduct(product.getName());
                    }});
                binding.wishlistButton.setEnabled(false);
                binding.wishlistButton.setText(R.string.inWishlist);
                mySnackbar.show();
            }
        });
        Button ARButton = (Button) binding.ARButton;
        ARButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(getView(), R.string.arStart, Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });
        ImageButton ARInfoButton = (ImageButton) binding.ARInfoButton;
        ARInfoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView message = new TextView(getContext());
                message.setText(HtmlCompat.fromHtml(getString(R.string.arString), HtmlCompat.FROM_HTML_MODE_COMPACT));
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("AR-info");
                dialog.setView(message);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
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
                Snackbar.make(getView(),R.string.videoString, Snackbar.LENGTH_SHORT).show();
            }
        });

        Button compareButton = (Button) binding.compareButton;
        if(product.isInComparison()){
            compareButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.red));
        }
        else{
            compareButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.green));
        }
        compareButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(product.isInComparison()){
                    applicationData.getInstance().getProductData().removeFromComparison(product);
                    compareButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.green));
                    Snackbar snackbar = Snackbar.make(getView(), product.getName() + " " + getContext().getResources().getString(R.string.removedFromComparison), Snackbar.LENGTH_LONG);
                    snackbar.setAction(R.string.revertChanges, s_v -> {
                        applicationData.getInstance().getProductData().addToComparisonList(product);
                        binding.compareButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.red));
                    });
                    snackbar.show();
                }
                else{
                    if(applicationData.getInstance().getProductData().addToComparisonList(product)){
                        compareButton.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.red));
                        if(applicationData.getInstance().getProductData().getComparisonList().size() == 2){
                            Navigation.findNavController(view).navigate(R.id.action_productPageFragment_to_compareFragment);
                        }
                    }
                    else{
                        if(applicationData.getInstance().getProductData().getComparisonList().size() ==2){
                            Snackbar.make(getView(), R.string.compareFull, Snackbar.LENGTH_SHORT).show();
                        }
                        else{
                            Snackbar.make(getView(), R.string.wrongCompType, Snackbar.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        TextView availableText = (TextView) binding.availableText;
        if(product.isAvailable()){
            availableText.setText(R.string.available);
        }
        else{
            availableText.setText(R.string.notAvailable);
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
                if(applicationData.getInstance().getProductData().getCurrentProduct().getType() == "Televisie"){
                    applicationData.getInstance().getwData().setWizardType(product.getType());
                    Navigation.findNavController(view).navigate(R.id.action_productPageFragment_to_navigation_wizardFragment);
                }
                else{
                    Snackbar.make(getView(), R.string.wizardLimit, Snackbar.LENGTH_SHORT);
                }
            }
        });
        ListView reviewlist = (ListView) binding.reviewList;
        Vector<Review> productreviews = product.getReviews();
        String[] reviewStrings = new String[product.getTextReviewAmount()+1];
        DecimalFormat df = new DecimalFormat("0.00");
        reviewAdapter ra = new reviewAdapter(getContext());
        reviewlist.setAdapter(ra);


        RatingBar ratingBar = (RatingBar) binding.ratingBar3;
        ratingBar.setRating((float) product.getAverageReviewScore());

        DecimalFormat f = new DecimalFormat("##.00");
        TextView ratingText = (TextView) binding.ratingText;
        ratingText.setText("Gemiddelde review score: " + f.format(product.getAverageReviewScore()) + "/5");
        return binding.getRoot();
    }
}