package com.example.aaron.lab7;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Aaron on 09/11/2016.
 */

public class CAFragment extends Fragment {
    public final static String EXTRA_CA_ID = "com.example.aaron.lab7.ca_id";
    private Cas savedCa;
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
            savedCa = CAModel.get(getActivity()).getCa(position);
        }
    }

    @Override
    //configure fragment view
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca, parent, false);
            final Button dueDateButton = (Button) v.findViewById(R.id.ca_due_date);
        if (savedCa != null) {
            dueDateButton.setText(savedCa.getDue_date().toString());
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
                if (savedCa != null) {
                    savedCa.setReport(isChecked);
                }
            }
        });
//finds each element base don id, listens for changes to text, sets the text entry to the field of the savedCa object
            sTitleField = (EditText) v.findViewById(R.id.ca_title);
            sTitleField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (savedCa != null) {
                        savedCa.setTitle(s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (savedCa != null) {
                        CAModel caModel = CAModel.get(getActivity());
                        caModel.updateCa(savedCa);
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
                    if (savedCa != null) {
                        savedCa.setSubject(s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (savedCa != null) {
                        CAModel caModel = CAModel.get(getActivity());
                        caModel.updateCa(savedCa);
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
                    if (savedCa != null) {
                        savedCa.setLecturer(s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (savedCa != null) {
                        CAModel caModel = CAModel.get(getActivity());
                        caModel.updateCa(savedCa);
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
                    if (savedCa != null) {
                        savedCa.setDetails(s.toString());
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (savedCa != null) {
                        CAModel caModel = CAModel.get(getActivity());
                        caModel.updateCa(savedCa);
                    }
                }

            });


            //Button brings the application back one stpoe in the stack, returning to the full list
            final Button saveButton = (Button) v.findViewById(R.id.save_button);
        if (savedCa != null) {
            saveButton.setText("Update");
        }
        else{
            saveButton.setText("Add");
        }
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //return to previous fragment
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().popBackStack();
                    //toast message to twll user that the changes entered before pressing the button were saved
                    if (savedCa != null) {
                        Toast.makeText(getContext(), "Changes Saved", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(), "CA Added", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            //populates fields for editing
            if (savedCa != null) {
                sTitleField.setText(savedCa.getTitle());
                sLecturerField.setText(savedCa.getLecturer());
                sSubjectField.setText(savedCa.getSubject());
                sDetailsField.setText(savedCa.getDetails());
                dueDateButton.setText(savedCa.getDue_date().toString());
            }

        return v;
    }
}
