package com.example.warmorange.ui.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.BookedDemoLayoutBinding;
import com.example.warmorange.model.Demo;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.ui.home.HomeProductAdapter;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder>  {
    private final List<Demo> demos;

    public DemoAdapter(List<Demo> demos) {
        this.demos = demos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.booked_demo_layout, viewGroup, false);
        return new DemoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Demo demo = demos.get(position);

        viewHolder.setViewData(demo, this);
        viewHolder.itemView.setOnClickListener(v-> {

        });
    }

    @Override
    public int getItemCount() {
        return demos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        BookedDemoLayoutBinding binding;
        Context context;

        public ViewHolder(View view) {
            super(view);
            binding = BookedDemoLayoutBinding.bind(view);
            context = view.getContext();
        }

        public void setViewData(Demo demo, DemoAdapter adapter) {
            String date = demo.getDay() + "/" + demo.getMonth() + "/" + demo.getYear() + context.getResources().getString(R.string.at) + demo.getHour() + ":" + demo.getMinute();
            binding.demoName.setText(demo.getDemoName());
            binding.demoDate.setText(date);
        }
    }
}
