package com.example.warmorange;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.warmorange.databinding.FragmentWizardBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WizardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WizardFragment extends Fragment {

    private FragmentWizardBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String productType;
    private wizardData wData;
    private wizardInstance wInstance;
    private applicationData appData = applicationData.getInstance();
    public WizardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment WizardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WizardFragment newInstance(String param1) {
        WizardFragment fragment = new WizardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            productType = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        wData = appData.getwData();
//        System.out.println(wData.getWizardType());
        productType = appData.getwData().getWizardType();
        wInstance = wData.getInstance(productType);
        binding = FragmentWizardBinding.inflate(inflater,container,false);
        View view = inflater.inflate(R.layout.fragment_wizard,container,false);
        setData();

        Button nextBtn = (Button) binding.nextButton;

        nextBtn.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View view) {
                                           if(wInstance.nextIndexInBounds() && binding.radiogroup.getCheckedRadioButtonId() != -1){
                                               checkRadio();
                                               binding.radiogroup.clearCheck();
                                               System.out.println(wInstance.getCurrentIndex());
                                               wInstance.incrementIndex();
                                               if(wInstance.lastQuestion()){
                                                   nextBtn.setText("Indienen");
                                               }
                                               else{
                                                   nextBtn.setText("Volgende");
                                               }
                                               setData();
                                           }
                                           else if(wInstance.lastIndex()){
                                               checkRadio();
                                               Toast toast=Toast.makeText(getActivity(),"Your score: " + wInstance.calcResponse(),Toast.LENGTH_SHORT);
                                               toast.show();
                                           }
                                       }
                                   }
        );
        Button prevBtn = (Button) binding.prevButton;
        prevBtn.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View view) {
                                           if(wInstance.prevIndexInBounds()){
                                               binding.radiogroup.clearCheck();
                                               wInstance.decrementIndex();
                                               setData();
                                           }
                                       }
                                   }
        );

        return binding.getRoot();
    }

    /**
     * Function that manually checks radio buttons, automatic check gave wrong ids
     */
    private void checkRadio(){
        if(binding.Vraag1.isChecked()){
            wInstance.addResponse(1);
        }
        else if(binding.Vraag2.isChecked()){
            wInstance.addResponse(2);
        }
        else if(binding.Vraag3.isChecked()){
            wInstance.addResponse(3);
        }
        else if(binding.Vraag4.isChecked()){
            wInstance.addResponse(0);
        }
    }
    private void setData(){
        binding.wizardQuestion.setText(wInstance.getQuestionForIndex());
        ArrayList<String> questions =  wInstance.getAnswersForIndex();

        binding.Vraag1.setText(questions.get(0));
        binding.Vraag2.setText(questions.get(1));
        binding.Vraag3.setText(questions.get(2));
        binding.Vraag4.setText(questions.get(3));

    }
}