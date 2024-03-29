package com.example.warmorange.ui.search;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.SearchCategoryLayoutBinding;
import com.example.warmorange.databinding.SearchItemLayoutBinding;
import com.example.warmorange.databinding.SearchListLayoutBinding;
import com.example.warmorange.model.Account;
import com.example.warmorange.model.Category;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.applicationData;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SearchListLayoutBinding binding;

        View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
            binding = SearchListLayoutBinding.bind(view);
        }

        public void setViewData(Product product) {
            binding.searchItemImage.setImageResource(product.getImageId());
            binding.searchItemTitle.setText(product.getName());
            binding.searchItemRating.setRating((float)product.getAverageReviewScore());

            ImageButton wishlistButton = binding.searchWishlistButton;
            Account activeUser = applicationData.getInstance().getLoginData().getActiveUser();
            if (activeUser == null) {
                wishlistButton.setOnClickListener(v ->
                        Toast.makeText(v.getContext(),
                                R.string.log_in_to_add_to_wishlist,
                                Toast.LENGTH_LONG).show()
                );
            }
            else if (activeUser.productInWishlist(product.getName())) {
                wishlistButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                wishlistButton.setOnClickListener(v -> onDeleteWishlist((ImageButton) v, product));
            }
            else {
                wishlistButton.setOnClickListener(v -> onAddWishlist((ImageButton) v, product));
            }

            Button compareButton = binding.searchItemCompare;
            if(product.isInComparison()){
                compareButton.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.red));
            }
            else{
                compareButton.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.green));
            }
            compareButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(product.isInComparison()){
                        applicationData.getInstance().getProductData().removeFromComparison(product);
                        compareButton.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.green));
                        Snackbar snackbar = Snackbar.make(view, product.getName() + " " + view.getContext().getResources().getString(R.string.removedFromComparison), Snackbar.LENGTH_LONG);
                        snackbar.setAction(R.string.revertChanges, s_v -> {
                            applicationData.getInstance().getProductData().addToComparisonList(product);
                            compareButton.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.red));
                        });
                        snackbar.show();
                    }
                    else{
                        if(applicationData.getInstance().getProductData().addToComparisonList(product)){
                            compareButton.setBackgroundTintList(view.getContext().getResources().getColorStateList(R.color.red));
                            if(applicationData.getInstance().getProductData().getComparisonList().size() == 2){
                                Navigation.findNavController(view).navigate(R.id.action_searchListFragment_to_compareFragment);
                            }
                        }
                        else{
                            if(applicationData.getInstance().getProductData().getComparisonList().size() ==2){
                                Snackbar.make(view, R.string.compareFull, Snackbar.LENGTH_SHORT).show();
                            }
                            else{
                                Snackbar.make(view, R.string.wrongCompType, Snackbar.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            });
        }

        private void onAddWishlist(ImageButton b, Product p) {
            applicationData.getInstance().getLoginData().getActiveUser().getWishlist().add(p);
            String toastString = p.getName() + itemView.getContext().getResources().getString(R.string.added_to_wishlist);
            Toast.makeText(itemView.getContext(), toastString, Toast.LENGTH_SHORT).show();
            b.setImageResource(R.drawable.ic_baseline_favorite_24);
            b.setOnClickListener(vw -> onDeleteWishlist((ImageButton)vw, p));
        }

        private void onDeleteWishlist(ImageButton b, Product p) {
            applicationData.getInstance().getLoginData().getActiveUser().getWishlist().remove(p);
            String toastString = p.getName() + itemView.getContext().getResources().getString(R.string.removed_from_wishlist);
            Toast.makeText(itemView.getContext(), toastString, Toast.LENGTH_SHORT).show();
            b.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            b.setOnClickListener(vw -> onAddWishlist((ImageButton)vw, p));
        }

        private void onAddComparison(Button b, Product p) {
            applicationData.getInstance().getProductData().getComparisonList().add(p);
            b.setText(R.string.in_comparison);
            b.setBackgroundColor(ContextCompat.getColor(b.getContext(), R.color.orange1));
            b.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            b.setOnClickListener(vw -> onDeleteComparison((Button)vw, p));
            Log.d("blub", "added to comparison");
        }

        private void onDeleteComparison(Button b, Product p) {
            applicationData.getInstance().getProductData().getComparisonList().remove(p);
            b.setBackgroundColor(ContextCompat.getColor(b.getContext(), R.color.green));
            b.setText(R.string.compare);
            b.setOnClickListener(vw -> onAddComparison((Button)vw, p));
            b.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_add_24, 0, 0, 0);
            Log.d("blub", "removed from comparison");
        }
    }

    private final List<Product> products;

    /**
     * Initialize the dataset of the Adapter.
     *
     * by RecyclerView.
     */
    public SearchListAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_list_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Product product = products.get(position);

        viewHolder.setViewData(product);
        applicationData.getInstance().getProductData().setCurrentProduct(product.getName());
        viewHolder.itemView.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_searchListFragment_to_productPageFragment)
        );
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
