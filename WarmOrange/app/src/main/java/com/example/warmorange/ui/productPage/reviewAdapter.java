package com.example.warmorange.ui.productPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.warmorange.R;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.model.Review;

import java.util.Vector;

public class reviewAdapter extends ArrayAdapter<Review> {
    private Vector<Review> reviews = new Vector<>();
    private Context context;
    public reviewAdapter(Context c){
        super(c, 0, applicationData.getInstance().getProductData().getCurrentProduct().getReviews());
        reviews = applicationData.getInstance().getProductData().getCurrentProduct().getReviews();
        context = c;
    }

    public View getView(int position, View convertView, ViewGroup Parent){
        Review r= reviews.elementAt(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_review, Parent,false);
        }
        TextView reviewText = (TextView) convertView.findViewById(R.id.textView3);
        reviewText.setText(r.getText());

        RatingBar ratingbar = (RatingBar) convertView.findViewById(R.id.ratingBar2);
        ratingbar.setMax(5);
        ratingbar.setRating(r.getRating());

        return convertView;
    }
}
