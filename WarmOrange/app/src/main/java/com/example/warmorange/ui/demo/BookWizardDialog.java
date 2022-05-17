package com.example.warmorange.ui.demo;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.BookDemoLayoutBinding;
import com.example.warmorange.databinding.BookWizardDialogLayoutBinding;
import com.example.warmorange.model.applicationData;

public class BookWizardDialog extends AlertDialog.Builder {
    private AlertDialog dialog;
    private final View demoView;
    public BookWizardDialog(Context context, View view) {
        super(context);
        demoView = view;
    }

    public void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.book_wizard_dialog_layout, null);
        BookWizardDialogLayoutBinding binding;
        binding = BookWizardDialogLayoutBinding.bind(view);
        RecyclerView recyclerView = binding.wizardRecyclerView;
        setTitle(R.string.bookDemoWizardTitle);
        setView(view);
        dialog = show();
        recyclerView.setAdapter(new BookWizardAdapter(applicationData.getInstance().getwData().getWizardList(), dialog, demoView));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        dialog.getWindow().setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.round_dialog));
    }
}
