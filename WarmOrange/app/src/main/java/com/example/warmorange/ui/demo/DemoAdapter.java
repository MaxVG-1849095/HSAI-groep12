package com.example.warmorange.ui.demo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        AlertDialog.Builder alertDialog;

        public ViewHolder(View view) {
            super(view);
            binding = BookedDemoLayoutBinding.bind(view);
            context = view.getContext();
        }

        public void setViewData(Demo demo, DemoAdapter adapter) {
            showLayout(demo);
            binding.cancelDemoButton.setOnClickListener(view -> {
                createAlertDialog(demo);
                alertDialog.show();
            });
        }
        private void createAlertDialog(Demo demo) {
            alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(context.getResources().getString(R.string.scan_qr_dialogtitle));
            String message;
            if (demo.isForProduct()) {
                message = context.getResources().getString(R.string.cancelDemoProductMessage, demo.getDemoName());
            } else {
                message = context.getResources().getString(R.string.cancelDemoWizzardMessage, demo.getDemoName());
            }
            alertDialog.setMessage(message);
            alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    demo.cancel();
                    showLayout(demo);
                }
            });
            alertDialog.setNegativeButton(R.string.no, null);
        }
        private void showLayout(Demo demo) {
            String date = demo.getDay() + "/" + demo.getMonth() + "/" + demo.getYear();
            String hour = "" + demo.getHour();
            if (hour.length() < 2)
                hour = 0 + hour;
            String minute = "" + demo.getMinute();
            if (minute.length() < 2)
                minute = 0 + minute;
            String time = hour + ":" + minute;
            binding.demoName.setText(demo.getDemoName());

            Button cancelButton = binding.cancelDemoButton;
            Button moveButton = binding.moveDemoButton;
            int textColor, iconColor, cancelButtonColor, moveButtonColor;
            if (demo.isCancelled()) {
                textColor = R.color.darkgrey;
                iconColor = R.color.darkgrey;
                cancelButtonColor = R.color.grey;
                moveButtonColor = R.color.grey;
                cancelButton.setEnabled(false);
                moveButton.setEnabled(false);
                binding.demoDate.setText("");
                binding.demoTime.setText(R.string.cancelled);
            } else {
                textColor = R.color.white;
                iconColor = R.color.white;
                cancelButtonColor = R.color.red;
                moveButtonColor = R.color.yellow;
                cancelButton.setEnabled(true);
                moveButton.setEnabled(true);
                binding.demoDate.setText(date);
                binding.demoTime.setText(time);
            }
            cancelButton.setTextColor(context.getResources().getColor(textColor));
            cancelButton.setCompoundDrawableTintList(context.getResources().getColorStateList(iconColor));
            cancelButton.setBackgroundTintList(context.getResources().getColorStateList(cancelButtonColor));
            moveButton.setTextColor(context.getResources().getColor(textColor));
            moveButton.setCompoundDrawableTintList(context.getResources().getColorStateList(iconColor));
            moveButton.setBackgroundTintList(context.getResources().getColorStateList(moveButtonColor));
        }
    }
}
