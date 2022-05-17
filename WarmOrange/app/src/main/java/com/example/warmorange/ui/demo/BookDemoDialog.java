package com.example.warmorange.ui.demo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.warmorange.R;
import com.example.warmorange.databinding.BookDemoLayoutBinding;
import com.example.warmorange.model.Demo;
import com.example.warmorange.model.Product;
import com.example.warmorange.model.Wizard;
import com.example.warmorange.model.applicationData;
import com.example.warmorange.model.WizardInstance;

import java.util.Calendar;

public class BookDemoDialog extends AlertDialog.Builder {
    private boolean isProduct;
    private String name;
    private Product product;
    private String wizardDate;
    private Wizard wizard;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private AlertDialog dialog;

    public BookDemoDialog(Context context) {
        super(context);
    }
    public void showDialog(Product product) {
        this.product = product;
        isProduct = true;
        name = product.getName();
        showDialog();
    }
    public void showDialog(Wizard wizard) {
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
                            month = m+1;
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