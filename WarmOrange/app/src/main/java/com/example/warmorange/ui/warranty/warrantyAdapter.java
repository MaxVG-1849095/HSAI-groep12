package com.example.warmorange.ui.warranty;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.warmorange.R;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.ProductData;
import com.example.warmorange.model.applicationData;

import java.util.Vector;

public class warrantyAdapter extends ArrayAdapter<Product> {
    Vector<Product> products = new Vector<>();
    private Context context;
    public warrantyAdapter(Context c, ProductData p) {
        super(c, 0, applicationData.getInstance().getLoginData().getActiveUser().getOwnedProducts());
        products = applicationData.getInstance().getLoginData().getActiveUser().getOwnedProducts();
        context = c;
    }

    public View getView(int position, View convertView, ViewGroup Parent){
        Product product = products.elementAt(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_warranty, Parent,false);
        }

        ImageView image = convertView.findViewById(R.id.warrantyImage);
        image.setImageResource(product.getImageId());



        Button testbtn = convertView.findViewById(R.id.reviewButton);
        TextView warrantyThanks = convertView.findViewById(R.id.reviewThanks);
        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applicationData.getInstance().getProductData().setCurrentProduct(product.getName());
                Navigation.findNavController(view)
                        .navigate(R.id.action_warrantyFragment_to_reviewFragment);
            }
        });
        if(product.isReviewed()){
            testbtn.setVisibility(View.GONE);
        }
        else{
            warrantyThanks.setVisibility(View.GONE);
        }
        TextView titleText = (TextView) convertView.findViewById(R.id.warrantyTitle);
        titleText.setText(product.getName());

        TextView boughtText = (TextView) convertView.findViewById(R.id.boughtText);
        boughtText.setText(product.getName());

        TextView warrantyText = (TextView) convertView.findViewById(R.id.warrantyText);
        if(product.getTotalWarranty() >= product.getCurrentWarranty())
            warrantyText.setText("Resterende garantie: " + (product.getTotalWarranty() - product.getCurrentWarranty()) + "/" + product.getTotalWarranty() + " maanden");
        else
            warrantyText.setText(R.string.warrantyExpired);
        ProgressBar pBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
        if(product.getTotalWarranty() < product.getCurrentWarranty()){
            pBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }
        else if((double)product.getCurrentWarranty()/(double)product.getTotalWarranty() <0.3){
            pBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }
        else if((double)product.getCurrentWarranty()/(double)product.getTotalWarranty() < 0.6)
        {
            pBar.setProgressTintList(ColorStateList.valueOf(Color.CYAN));
        }
        else{
            pBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        }
        pBar.setMax(product.getTotalWarranty());
        pBar.setProgress(product.getCurrentWarranty());

        return convertView;
    }
}
