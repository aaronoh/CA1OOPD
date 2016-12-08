package com.example.aaron.lab7;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by Aaron on 09/11/2016.
 */

public class CAFragment extends Fragment {
    public final static String EXTRA_CA_ID = "com.example.aaron.lab7.ca_id";
    private Cas newCa;
    private EditText sTitleField;
    private Button dueDateButton;
    private CheckBox reportCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getActivity().getIntent().getIntExtra(EXTRA_CA_ID, -1);
        if (position != -1) {
            newCa = CAModel.get(getActivity()).getCa(position);
        }
        newCa = CAModel.get(getActivity()).getCa(position);
    }

    @Override
    //configure fragment view
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca, parent, false);

        dueDateButton = (Button) v.findViewById(R.id.ca_due_date);
        dueDateButton.setText(newCa.getDue_date().toString());
        dueDateButton.setEnabled(true);

        reportCheckBox = (CheckBox) v.findViewById(R.id.report_required);
        reportCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                newCa.setReport(isChecked);
            }
        });

        sTitleField = (EditText) v.findViewById(R.id.ca_title);
        sTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newCa.setTitle(s.toString());
                CAModel caModel = CAModel.get(getActivity());
                caModel.updateCa(newCa);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        if (newCa != null) {
            sTitleField.setText(newCa.getTitle());
            dueDateButton.setText(newCa.getDue_date().toString());
        }

        return v;
    }
}
