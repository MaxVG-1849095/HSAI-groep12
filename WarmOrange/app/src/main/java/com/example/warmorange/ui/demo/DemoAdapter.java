package com.example.warmorange.ui.demo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.BookDemoLayoutBinding;
import com.example.warmorange.databinding.BookedDemoLayoutBinding;
import com.example.warmorange.model.Demo;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.model.wizardInstance;

import java.util.Calendar;
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
            binding.moveDemoButton.setOnClickListener(view -> {
                moveDemoDialog move = new moveDemoDialog(context);
                move.showDialog(applicationData.getInstance().getProductData().getCurrentProduct());
//                AlertDialog moveDialog = move.show();
                //moveDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.round_dialog));
            });
        }
        private void createAlertDialog(Demo demo) {
            alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(context.getResources().getString(R.string.cancelDemo));
            String message;
            if (demo.isForProduct()) {
                message = context.getResources().getString(R.string.cancelDemoProductMessage, demo.getDemoName());
            } else {
                message = context.getResources().getString(R.string.cancelDemoWizardMessage, demo.getDemoName());
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

    public static class moveDemoDialog extends AlertDialog.Builder {
        private boolean isProduct;
        private String name;
        private Product product;
        private String wizardDate;
        private wizardInstance wizard;
        int day;
        int month;
        int year;
        int hour;
        int minute;
        AlertDialog dialog;

        protected moveDemoDialog(Context context) {
            super(context);
        }
        private void showDialog(Product product) {
            this.product = product;
            isProduct = true;
            name = product.getName();
            showDialog();
        }
        private void showDialog(wizardInstance wizard) {
            this.wizard = wizard;
            isProduct = false;
            name = wizard.getResult();
            wizardDate = wizard.getDate();
            showDialog();
        }
        private void showDialog() {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.book_demo_layout, null);
            BookDemoLayoutBinding binding;
            binding = BookDemoLayoutBinding.bind(view);
            binding.bookDemoName.setText(name);
            binding.bookDemoChooseDatetime.setText(R.string.chooseDateTime);
            binding.bookDemoDate.setText(R.string.date);
            binding.bookDemoTime.setText(R.string.time);

            if (isProduct) {
                setTitle(R.string.bookDemoProductTitle);
                binding.wizardOf.setText("");
                binding.wizardResult.setText("");
            } else {
                setTitle(R.string.bookDemoWizardTitle);
                binding.wizardOf.setText(getContext().getResources().getString(R.string.wizardOf, wizardDate));
                binding.wizardResult.setText(R.string.result);
            }

            EditText dateText = binding.editWizardDate;
            dateText.setOnClickListener(v ->{
                DatePickerDialog datePicker;
                final Calendar calendar = Calendar.getInstance();
                int currDay = calendar.get(Calendar.DAY_OF_MONTH);
                int currMonth = calendar.get(Calendar.MONTH);
                int currYear = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                dateText.setText(d + "/" + (m + 1) + "/" + y);
                                day = d;
                                month = m;
                                year = y;
                            }
                        }, currYear, currMonth, currDay);
                datePicker.show();
            });

            EditText timeText = binding.editWizardTime;
            timeText.setOnClickListener(v -> {
                TimePickerDialog timePicker;
                final Calendar calendar = Calendar.getInstance();
                int currHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currMinute = calendar.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int h, int m) {
                                timeText.setText(h + ":" + m);
                                hour = h;
                                minute = m;
                            }
                        }, currHour, currMinute, true);
                timePicker.show();
            });

            binding.bookButton.setOnClickListener(v->{
                saveDemo();
                dialog.dismiss();
            });
            setView(view);
            dialog = show();
            dialog.getWindow().setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.round_dialog));
        }

        private void saveDemo() {
            Demo demo;
            if (isProduct) {
                demo = new Demo(product, day, month, year, hour, minute);
            } else {
                demo = new Demo(wizard, day, month, year, hour, minute);
            }
            applicationData.getInstance().getDemoData().addDemo(demo);
        }
    }
}
