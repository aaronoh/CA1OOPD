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
    Cas newCa;
    EditText sTitleField;
    Button dueDateButton;
    CheckBox reportCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newCa = new Cas();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca, parent, false);

        dueDateButton = (Button) v.findViewById(R.id.ca_due_date);
        dueDateButton.setText(newCa.getDue_date().toString());
        dueDateButton.setEnabled(false);

        reportCheckBox = (CheckBox)v.findViewById(R.id.report_required);
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
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }
}
