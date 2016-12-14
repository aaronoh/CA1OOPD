package com.example.aaron.lab7;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import static android.R.attr.button;
import static com.example.aaron.lab7.R.styleable.CompoundButton;

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
    Button deleteButton;

    public static CAFragment newInstance(int position) {
        //allows for passing of position to new fragment
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
        //if the ca exists, find it
        if (position != -1) {
            savedCa = CAModel.get(getActivity()).getCa(position);
        }
    }

    @Override
    //configure fragment view
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca, parent, false);

        //Button brings the application back one step in the stack, returning to the full list
        saveButton = (Button) v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
        //finds each element based on id, listens for changes to text, sets the text entry to the field of the savedCa object
        sTitleField = (EditText) v.findViewById(R.id.ca_title);
        sSubjectField = (EditText) v.findViewById(R.id.ca_subject);
        sLecturerField = (EditText) v.findViewById(R.id.ca_Lecturer);
        sDetailsField = (EditText) v.findViewById(R.id.ca_details);
        dueDateButton = (Button) v.findViewById(R.id.ca_due_date);
        reportCheckBox = (CheckBox) v.findViewById(R.id.report_required);


        //Date picker contained within an Alert Dialouge
        dueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePicker dp = new DatePicker(getActivity());
                new AlertDialog.Builder(getActivity())
                        .setView(dp)
                        //Confirmtaion button on date picker
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            //sets chosen date as text on button

                            // *************************************************************************************//
                            //This is the only place that the added/updated date can be seen, it is not (currently)//
                            // being displayed in the full list view/being added to the daatabase                  //
                            //************************************************************************************//

                            public void onClick(DialogInterface dialog, int which) {
                                //default month count starts at 0, first month is 1, not 0
                                dueDateButton.setText(dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + "/" + dp.getYear());
                                dialog.dismiss();
                            }
                        })
                        //creating & displaying the alert dialouge
                        .create()
                        .show();


            }
        });


        //if savedca exists, populates fields for editing
        if (savedCa != null) {
            sTitleField.setText(savedCa.getTitle());
            sLecturerField.setText(savedCa.getLecturer());
            sSubjectField.setText(savedCa.getSubject());
            sDetailsField.setText(savedCa.getDetails());
            dueDateButton.setText(savedCa.getDue_date().toString());
            reportCheckBox.setChecked(savedCa.getReport());
            saveButton.setText("Update CA");
        }
        //otherwise it sets a blank date format indicator on the date picker button & sets the text on the 'Update CA' button to 'Create CA', as it is a new CA
        else {
            dueDateButton.setText("DD/MM/YYYY");
            saveButton.setText("Create CA");
        }

        deleteButton = (Button) v.findViewById(R.id.delete_Button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CAModel caModel = CAModel.get(getActivity());
                //if the ca exists, delete it
                if(savedCa != null) {
                    caModel.deleteCa(savedCa);
                }
                //otherwise tell user there is nothing to delete
                else{
                    Toast.makeText(getContext(), "No CA to Delete", Toast.LENGTH_SHORT).show();
                }
                //return to main list
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return v;
    }



    //date parsing method requires this build on android
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    //on click for Update/Create button
    public void onClick(View v) {
        CAModel caModel = CAModel.get(getActivity());
        //***********//
        //  Update  //
        //**********//
        if (savedCa != null) {
            Toast.makeText(getContext(), "Changes Saved", Toast.LENGTH_SHORT).show();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //date parsing
            Date d = null;
            try {
                d = dateFormat.parse(dueDateButton.getText().toString());
            } catch (Exception e) {
                Log.e("CaList", "exception", e);

            }
            //if a date was entered...
            if (d != null) {
                savedCa.setDue_date(d);
            }
            //set values of variables to values entered by user
            savedCa.setTitle(sTitleField.getText().toString());
            savedCa.setSubject(sSubjectField.getText().toString());
            savedCa.setLecturer(sLecturerField.getText().toString());
            savedCa.setReport(reportCheckBox.isChecked());
            savedCa.setDetails(sDetailsField.getText().toString());
            //update method for database
            caModel.updateCa(savedCa);


        }
        //***********//
        //  Create  //
        //**********//
        else {
            Toast.makeText(getContext(), "CA Added", Toast.LENGTH_SHORT).show();
            //since its a new ca, not an update, make a new cas object
            Cas ca = new Cas();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            //date parsing
            Date d = null;
            try {
                d = dateFormat.parse(dueDateButton.getText().toString());
            } catch (Exception e) {
                Log.e("CaList", "exception", e);

            }
            //if a date was entered...
            if (d != null) {
                ca.setDue_date(d);
            }
            //set values of variables to values entered by user
            ca.setTitle(sTitleField.getText().toString());
            ca.setSubject(sSubjectField.getText().toString());
            ca.setLecturer(sLecturerField.getText().toString());
            ca.setReport(reportCheckBox.isChecked());
            //************************************************//
            //Checkbox values are being saved in the arraylist, if I had time I would write code that would set a variable
            // to the value 0 or 1 based on the true/false value of isChecked, I would then save those ints to the database.
            // When reading from the database I'd reverse that if statement, if x = 0 checked = false.
            //***********************************************//
            ca.setDetails(sDetailsField.getText().toString());
            //add method to add to database & arraylist
            caModel.createCas(ca);

        }
        //return to full list
        getActivity().getSupportFragmentManager().popBackStack();
    }

}