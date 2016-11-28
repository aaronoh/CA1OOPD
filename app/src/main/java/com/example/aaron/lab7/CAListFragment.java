package com.example.aaron.lab7;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aaron on 23/11/2016.
 */

public class CAListFragment extends ListFragment {
    private static final String TAG = "CAListFragment";
    private ArrayList<Cas> mCas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//returns host activity
        getActivity().setTitle(R.string.ca_title_label);
//return list of cas
        mCas = CAModel.get(getActivity()).getCas();

//Using CAAdapter to retrieve content from ca objects and place in list items
        CaAdapter adapter = new CaAdapter(mCas);
        setListAdapter(adapter);
    }//only used within this class, inner class

    private class CaAdapter extends ArrayAdapter<Cas> {
        public CaAdapter(ArrayList<Cas> cas) {
            super(getActivity(), 0, cas);
        }

        //called when listview needs a view object, ovverrides getview method
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_ca, null);
            }
            //configuring view for individual ca
            Cas c = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.ca_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.ca_list_item_dateTextView);
            dateTextView.setText(c.getDue_date().toString());

            CheckBox reportChecBox = (CheckBox) convertView.findViewById(R.id.ca_list_item_reportCheckbox);
            reportChecBox.setChecked(c.getReport());

            return convertView;
        }
    }

    //logs last clicked
    public void onListItemClick(ListView l, View v, int position, long id) {
        Cas c = (Cas) (getListAdapter()).getItem(position);
        Log.d(TAG, c.getTitle() + " was clicked");
    }
}
