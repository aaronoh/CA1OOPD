package com.example.aaron.lab7;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Aaron on 09/11/2016.
 */

public class CAFragment extends Fragment implements View.OnClickListener {
    public final static String EXTRA_CA_ID = "com.example.aaron.lab7.ca_id";
    private Cas savedCa;
    private EditText sTitleField;
    private EditText sSubjectField;
    private EditText sLecturerField;
    private EditText sDetailsField;
    private CheckBox reportCheckBox;
    private Button saveButton;
    Button dueDateButton;

    public static CAFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_CA_ID, position);

        CAFragment fragment = new CAFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt(EXTRA_CA_ID);
        if (position != -1) {
            savedCa = CAModel.get(getActivity()).getCa(position);
        }
    }

    @Override
    //configure fragment view
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca, parent, false);


        //Button brings the application back one stpoe in the stack, returning to the full list
        saveButton = (Button) v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);

        reportCheckBox = (CheckBox) v.findViewById(R.id.report_required);
//finds each element base don id, listens for changes to text, sets the text entry to the field of the savedCa object
        sTitleField = (EditText) v.findViewById(R.id.ca_title);
        sSubjectField = (EditText) v.findViewById(R.id.ca_subject);
        sLecturerField = (EditText) v.findViewById(R.id.ca_Lecturer);
        sDetailsField = (EditText) v.findViewById(R.id.ca_details);

        // TODO: Look here at your layout file
        dueDateButton = (Button) v.findViewById(R.id.ca_due_date);
        dueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePicker dp = new DatePicker(getActivity());
                new AlertDialog.Builder(getActivity())
                        .setView(dp)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dueDateButton.setText(dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear());
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });


        //populates fields for editing
        if (savedCa != null) {
            sTitleField.setText(savedCa.getTitle());
            sLecturerField.setText(savedCa.getLecturer());
            sSubjectField.setText(savedCa.getSubject());
            sDetailsField.setText(savedCa.getDetails());
            dueDateButton.setText("DD/MM/YYYY");
            saveButton.setText("Update CA");
        }
        else {
            dueDateButton.setText(dueDateButton.getText().toString());
            saveButton.setText("Create CA");
        }

        return v;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        CAModel caModel = CAModel.get(getActivity());
        //toast message to twll user that the changes entered before pressing the button were saved
        if (savedCa != null) {
            Toast.makeText(getContext(), "Changes Saved", Toast.LENGTH_SHORT).show();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date d = null;
            try {
                d = dateFormat.parse(dueDateButton.getText().toString());
            } catch (Exception e) {
            }
            savedCa.setDue_date(d);
            savedCa.setTitle(sTitleField.getText().toString());
            savedCa.setReport(reportCheckBox.isChecked());
            savedCa.setDetails(sDetailsField.getText().toString());
            caModel.updateCa(savedCa);
        }

        else {
            Toast.makeText(getContext(), "CA Added", Toast.LENGTH_SHORT).show();
            Cas ca = new Cas();
            ca.setTitle(ca.getTitle());
            ca.setReport(reportCheckBox.isChecked());
            ca.setDetails(sDetailsField.getText().toString());
            caModel.updateCa(ca);
        }
        getActivity().getSupportFragmentManager().popBackStack();
    }
}