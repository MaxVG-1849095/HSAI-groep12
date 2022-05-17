package com.example.warmorange.ui.account;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.databinding.WishlistItemLayoutBinding;
import com.example.warmorange.model.Account;
import com.example.warmorange.model.Product;
import com.example.warmorange.ui.demo.DemoAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        WishlistItemLayoutBinding binding;

        public ViewHolder(View view) {
            super(view);

            binding = WishlistItemLayoutBinding.bind(view);
        }

        public void setViewData(Product product, Account account, WishlistAdapter adapter) {
            binding.productImage.setImageResource(product.getImageId());
            binding.productNameText.setText(product.getName());
            binding.ratingBar.setRating((float)product.getAverageReviewScore());
            binding.deleteButton.setOnClickListener(v -> {
                int index = account.getWishlist().indexOf(product);
                account.getWishlist().remove(index);
                adapter.notifyItemRemoved(index);

                Snackbar snackbar = Snackbar.make(v, product.getName() + " verwijderd uit wishlist.", Snackbar.LENGTH_LONG);
                snackbar.setAction("Ongedaan maken", s_v -> {
                    account.getWishlist().insertElementAt(product, index);
                    adapter.notifyItemInserted(index);
                });
                snackbar.show();
            });
            binding.bookDemoButton.setOnClickListener(v -> {
                DemoAdapter.moveDemoDialog dialog = new DemoAdapter.moveDemoDialog(v.getContext());
                dialog.showDialog(product);
            });
        }
    }

    private final List<Product> products;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param products Product[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public WishlistAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.wishlist_item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Product product = products.get(position);
        Account account = applicationData.getInstance().getLoginData().getActiveUser();

        viewHolder.setViewData(product, account, this);
        viewHolder.itemView.setOnClickListener(v -> {
            applicationData.getInstance().getProductData().setCurrentProduct(product.getName());
            Navigation.findNavController(v).navigate(R.id.action_wishlistFragment_to_productPageFragment);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
