package com.example.warmorange;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.example.warmorange.model.Product;
import com.example.warmorange.model.ProductData;

import org.w3c.dom.Text;

import java.util.Vector;

public class warrantyAdapter extends ArrayAdapter<Product> {
    Vector<Product> products = new Vector<>();
    private Context context;
    public warrantyAdapter(Context c, ProductData p) {
        super(c, 0,applicationData.getInstance().getTestAccount().getOwnedProducts());
        products = applicationData.getInstance().getTestAccount().getOwnedProducts();
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

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applicationData.getInstance().getProductData().setCurrentProduct(product.getName());
                Navigation.findNavController(view)
                        .navigate(R.id.action_warrantyFragment_to_reviewFragment);
            }
        });
        TextView titleText = (TextView) convertView.findViewById(R.id.warrantyTitle);
        titleText.setText(product.getName());

        TextView boughtText = (TextView) convertView.findViewById(R.id.boughtText);
        boughtText.setText(product.getName());

        TextView warrantyText = (TextView) convertView.findViewById(R.id.warrantyText);
        if(product.getTotalWarranty() >= product.getCurrentWarranty())
            warrantyText.setText("Resterende garantie: " + product.getCurrentWarranty() + "/" + product.getTotalWarranty() + " maanden");
        else
            warrantyText.setText(R.string.warrantyExpired);
        ProgressBar pBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
        pBar.setMax(product.getTotalWarranty());
        pBar.setProgress(product.getCurrentWarranty());

        return convertView;
    }
}
