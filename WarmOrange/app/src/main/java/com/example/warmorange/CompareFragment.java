package com.example.warmorange;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.warmorange.databinding.FragmentCompareBinding;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.ProductData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompareFragment extends Fragment {
    private FragmentCompareBinding binding;

    public static final String ARG_PRODUCT_NAMES = "productNames";

    // TODO: Rename and change types of parameters
    private Product[] products;

    public CompareFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param productNames names of the products to be compared.
     * @return A new instance of fragment CompareFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompareFragment newInstance(String[] productNames) {
        CompareFragment fragment = new CompareFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_PRODUCT_NAMES, productNames);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductData productData = applicationData.getInstance().getProductData();
        if (getArguments() != null) {
            String[] productNames = getArguments().getStringArray(ARG_PRODUCT_NAMES);
            products = new Product[productNames.length];
            for (int i = 0; i < productNames.length; ++i)
                products[i] = productData.getProduct(productNames[i]);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (products == null || products.length == 0)
            return inflater.inflate(R.layout.fragment_compare_empty, container, false);

        binding = FragmentCompareBinding.inflate(inflater, container, false);

        setHeader();
        String[] attributes = getAttributes();
        View[] views = createAttributeViews(inflater, container, attributes.length);
        fillAttributeViews(attributes, views);

        return binding.getRoot();
    }

    private void fillAttributeViews(String[] attributes, View[] views) {
        if (products.length == 0) return;
        // product 1
        Map<String, String> values1 = products[0].getAttributes();
        Map<String, String> values2 = new HashMap<>();
        if (products.length >= 2)
            values2 = products[1].getAttributes();

        for (int i = 0; i < attributes.length; ++i) {
            String a = attributes[i];
            View v = views[i];
            v.findViewById(R.id.attributeRow).setBackgroundResource(i % 2 == 0 ? R.color.table_accent1 : R.color.table_accent2);
            ((TextView)v.findViewById(R.id.attributeName)).setText(a);
            ((TextView)v.findViewById(R.id.product1Attribute)).setText(values1.getOrDefault(a, "-"));
            if (products.length >= 2)
                ((TextView)v.findViewById(R.id.product2Attribute)).setText(values2.getOrDefault(a, "-"));
        }
    }

    private void setHeader() {
        if (products.length > 0) {
            binding.product1Image.setImageResource(products[0].getImageId());
            binding.product1NameTextView.setText(products[0].getName());
            binding.product1Image.setOnClickListener(v -> onProductClick(products[0], v));
            binding.product1NameTextView.setOnClickListener(v -> onProductClick(products[0], v));
            binding.deleteButton1.setOnClickListener(v -> onProductDeleted(products[0], v));
        }
        if (products.length > 1) {
            binding.product2Image.setImageResource(products[1].getImageId());
            binding.product2NameTextView.setText(products[1].getName());
            binding.product2Image.setOnClickListener(v -> onProductClick(products[1], v));
            binding.product2NameTextView.setOnClickListener(v -> onProductClick(products[1], v));
            binding.deleteButton2.setOnClickListener(v -> onProductDeleted(products[1], v));
        } else {
            binding.product2Image.setImageResource(R.drawable.ic_baseline_library_add_24);
            binding.product2Image.getLayoutParams().height = 100;
            binding.product2Image.getLayoutParams().width = 100;
            binding.product2Image.requestLayout();
            TextView title = binding.product2NameTextView;
            title.setTypeface(null, Typeface.NORMAL);
            title.setText(R.string.addSecondItem);
            title.setTextSize(12.0f);
            title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            binding.deleteButton2.setVisibility(View.INVISIBLE);
        }
    }

    private void onProductDeleted(Product p, View v) {

    }

    private void onProductClick(Product p, View v) {
        applicationData.getInstance().getProductData().setCurrentProduct(p.getName());
        Navigation.findNavController(v).navigate(R.id.action_compareFragment_to_productPageFragment);
    }

    private View[] createAttributeViews(LayoutInflater inflater, ViewGroup container, int amount) {
        View[] views = new View[amount];
        for (int i = 0; i < amount; ++i) {
            View attr = inflater.inflate(R.layout.compare_segment_layout, container, false);
            views[i] = attr;
            binding.compareTable.addView(attr);
        }
        return views;
    }

    private String[] getAttributes() {
        if (products.length == 0) return new String[0];

        Map<String, String> map1 = products[0].getAttributes();
        ArrayList<String> attributes = new ArrayList<>(map1.keySet());
        if (products.length == 1)
            return attributes.toArray(new String[0]);

        Map<String, String> map2 = products[1].getAttributes();

        for (String k : map2.keySet()) {
            if (!attributes.contains(k))
                attributes.add(k);
        }

        return attributes.toArray(new String[0]);
    }
}