package com.hvr.dronedelievery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DropDown1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Create Spinner
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down);

        //find spinner's view
        mSpinner = (Spinner) findViewById(R.id.spinner);
        //add on item selected listerners to spinner
        mSpinner.setOnItemSelectedListener(this);

        //create adapter
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.labels_array,
                        android.R.layout.simple_spinner_item);

        //how the spinner will look when it drop downs on click
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //setting adapter to spinner
        mSpinner.setAdapter(adapter);
    }

    //Do something when the item is selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        //getting label name of the selected spinner
        String message = adapterView.getItemAtPosition(i).toString();

        //showing in Toast selected item name
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //may keep blank
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}