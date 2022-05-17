package com.example.warmorange.ui.demo;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmorange.R;
import com.example.warmorange.databinding.BookWizardDialogLayoutBinding;
import com.example.warmorange.databinding.WizardLayoutBinding;
import com.example.warmorange.model.Wizard;
import com.example.warmorange.model.WizardInstance;

import java.util.List;

public class BookWizardAdapter extends RecyclerView.Adapter<BookWizardAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        WizardLayoutBinding binding;
        Context context;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            binding = WizardLayoutBinding.bind(view);
        }

        public void setViewData(Wizard wizard, AlertDialog bookWizardDialog, View demoView) {
            binding.wizardName.setText(wizard.getResult());
            binding.wizardDate.setText(context.getResources().getString(R.string.wizardOf, wizard.getDate()));
            binding.bookWizardButton.setOnClickListener(v -> {
                BookDemoDialog dialog = new BookDemoDialog(context);
                dialog.showDialog(wizard);
                bookWizardDialog.dismiss();
                Navigation.findNavController(demoView).navigate(R.id.action_demoFragment_self);
            });
        }
    }

    private final List<Wizard> wizards;
    private final AlertDialog dialog;
    private final View demoView;

    public BookWizardAdapter(List<Wizard> wizards, AlertDialog dialog, View view) {
        this.wizards = wizards;
        this.dialog = dialog;
        demoView = view;
    }

    @NonNull
    @Override
    public BookWizardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.wizard_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookWizardAdapter.ViewHolder viewHolder, final int position) {
        Wizard wizard = wizards.get(position);

        viewHolder.setViewData(wizard, dialog, demoView);
    }

    @Override
    public int getItemCount() {
        return wizards.size();
    }
}
