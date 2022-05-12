package com.example.warmorange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.databinding.BoughtProductLayoutBinding;
import com.example.warmorange.model.Account;
import com.example.warmorange.model.Product;

import java.util.List;


public class BoughtProductsAdapter extends RecyclerView.Adapter<BoughtProductsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        BoughtProductLayoutBinding binding;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            binding = BoughtProductLayoutBinding.bind(view);
        }

        public void setViewData(Product product, Account account) {
            binding.productImage.setImageResource(product.getImageId());
            binding.productNameText.setText(product.getName());

            reviewSetup(product);
        }

        @SuppressWarnings("ConstantConditions")
        private void reviewSetup(Product product) {
            // TODO: Determine whether user reviewed product
            boolean reviewed = false;
            if (reviewed)
                binding.reviewThanksText.setVisibility(View.VISIBLE);
            else {
                Button button = binding.leaveReviewButton;
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(v -> {
                    applicationData.getInstance().getProductData().setCurrentProduct(product.getName());
                    // TODO: redirect to review page instead of product page
                    Navigation.findNavController(v).navigate(R.id.action_boughtProductsFragment_to_productPageFragment);
                });
            }
        }
    }

    private final List<Product> products;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param products Product[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public BoughtProductsAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bought_product_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Product product = products.get(position);
        Account account = applicationData.getInstance().getLoginData().getActiveUser();

        viewHolder.setViewData(product, account);
        viewHolder.itemView.setOnClickListener(v -> {
            applicationData.getInstance().getProductData().setCurrentProduct(product.getName());
            Navigation.findNavController(v).navigate(R.id.action_boughtProductsFragment_to_productPageFragment);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
