package com.example.warmorange.ui.review;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.warmorange.R;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.databinding.FragmentReviewBinding;
import com.example.warmorange.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {
    private FragmentReviewBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Product product;

    public ReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
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
        binding = FragmentReviewBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        product = applicationData.getInstance().getProductData().getCurrentProduct();

        TextView productname = (TextView) binding.productText;
        productname.setText(product.getName());


        EditText reviewText = (EditText) binding.editTextTextMultiLine;

        ImageView image = (ImageView) binding.imageView3;
        image.setImageResource(product.getImageId());


        Button submitButton = (Button) binding.submitButton;

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(reviewText.getText()) && checkRadio()) {
                    TextView message = new TextView(getContext());
                    message.setText(HtmlCompat.fromHtml("Wilt u een review achterlaten zonder uitleg?", HtmlCompat.FROM_HTML_MODE_COMPACT));
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setTitle("Review achterlaten");
                    dialog.setView(message);
                    dialog.setPositiveButton("Verder", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            product.addReview("", getRadio());
                            product.setReviewed(true);
                            Navigation.findNavController(view)
                                    .navigate(R.id.action_reviewFragment_to_warrantyFragment);
                        }
                    });
                    dialog.setNegativeButton("Uitleg achterlaten", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    dialog.show();
                }
                else if(!checkRadio()
                ){
                    Toast toast=Toast.makeText(getActivity(),"Je vulde geen score in!",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    product.addReview(reviewText.getText().toString(), getRadio());
                    product.setReviewed(true);
                    Navigation.findNavController(view)
                            .navigate(R.id.action_reviewFragment_to_warrantyFragment);
                }
            }
        });
        return binding.getRoot();
    }
    private boolean checkRadio(){
        return binding.Score1.isChecked() ||binding.Score2.isChecked() ||binding.Score3.isChecked() ||binding.Score4.isChecked() ||binding.Score5.isChecked();
    }
    private int getRadio(){
        if(binding.Score1.isChecked()){
            return 1;
        }
        else if(binding.Score2.isChecked()){
            return 2;
        }
        else if(binding.Score3.isChecked()){
            return 3;
        }
        else if(binding.Score4.isChecked()){
            return 4;
        }
        else if(binding.Score5.isChecked()){
            return 5;
        }
        else{
            return 0;
        }
    }
}