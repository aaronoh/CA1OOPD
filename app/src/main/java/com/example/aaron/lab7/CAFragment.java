package com.example.aaron.lab7;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Aaron on 09/11/2016.
 */

public class CAFragment extends Fragment {
    public final static String EXTRA_CA_ID = "com.example.aaron.lab7.ca_id";
    private Cas newCa;
    private EditText sTitleField;
    private EditText sSubjectField;
    private EditText sLecturerField;
    private EditText sDetailsField;
    private CheckBox reportCheckBox;
    private Button saveButton;

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
            newCa = CAModel.get(getActivity()).getCa(position);
        }
    }

    @Override
    //configure fragment view
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca, parent, false);
            final Button dueDateButton = (Button) v.findViewById(R.id.ca_due_date);
        if (newCa != null) {
            dueDateButton.setText(newCa.getDue_date().toString());
            dueDateButton.setEnabled(true);
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
        }

        reportCheckBox = (CheckBox) v.findViewById(R.id.report_required);
        reportCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (newCa != null) {
                    newCa.setReport(isChecked);
                }
            }
        });
//finds each element base don id, listens for changes to text, sets the text entry to the field of the newCa object
            sTitleField = (EditText) v.findViewById(R.id.ca_title);
            sTitleField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (newCa != null) {
                        newCa.setTitle(s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (newCa != null) {
                        CAModel caModel = CAModel.get(getActivity());
                        caModel.updateCa(newCa);
                    }
                    }

            });
            sSubjectField = (EditText) v.findViewById(R.id.ca_subject);
            sSubjectField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (newCa != null) {
                        newCa.setSubject(s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (newCa != null) {
                        CAModel caModel = CAModel.get(getActivity());
                        caModel.updateCa(newCa);
                    }
                }

            });

            sLecturerField = (EditText) v.findViewById(R.id.ca_Lecturer);
            sLecturerField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (newCa != null) {
                        newCa.setLecturer(s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (newCa != null) {
                        CAModel caModel = CAModel.get(getActivity());
                        caModel.updateCa(newCa);
                    }
                    }

            });

            sDetailsField = (EditText) v.findViewById(R.id.ca_details);
            sDetailsField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (newCa != null) {
                        newCa.setDetails(s.toString());
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (newCa != null) {
                        CAModel caModel = CAModel.get(getActivity());
                        caModel.updateCa(newCa);
                    }
                }

            });


            //Button brings the application back one stpoe in the stack, returning to the full list
            Button saveButton = (Button) v.findViewById(R.id.save_button);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //return to previous fragment
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().popBackStack();
                    //toast message to twll user that the changes entered before pressing the button were saved
                    Toast.makeText(getContext(), "Changes Saved", Toast.LENGTH_SHORT).show();

                }
            });

            //populates fields for editing
            if (newCa != null) {
                sTitleField.setText(newCa.getTitle());
                sLecturerField.setText(newCa.getLecturer());
                sSubjectField.setText(newCa.getSubject());
                sDetailsField.setText(newCa.getDetails());
                dueDateButton.setText(newCa.getDue_date().toString());
            }

        return v;
    }
}
