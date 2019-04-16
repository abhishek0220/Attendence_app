package com.attendence.abhis.attendence;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spsub = (Spinner) findViewById(R.id.selsubject);
        final Spinner spap = (Spinner) findViewById(R.id.ap);


        // Spinner click listener

        // Spinner Drop down elements

        List<String> c_sub = new ArrayList<String>();
        c_sub.add("AML100");
        c_sub.add("CYP100");
        c_sub.add("EEP100");
        c_sub.add("EVP102");
        c_sub.add("HUP103");
        c_sub.add("MAL101");
        c_sub.add("MEP100");
        c_sub.add("MEP101");
        c_sub.add("PHL100");

        List<String> c_ap = new ArrayList<String>();
        c_ap.add("Present");
        c_ap.add("Absent");

        // Creating adapter for spinner
        ArrayAdapter<String> datasub = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c_sub);
        ArrayAdapter<String> dataap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c_ap);

        // Drop down layout style - list view with radio button
        datasub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spsub.setAdapter(datasub);
        spap.setAdapter(dataap);
    }
    public void selectdate(View v){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        EditText eda=findViewById(R.id.feddate);
                        eda.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    String tsub,tda,tap;

    public void onclick(View v){


        Spinner msub = (Spinner) findViewById(R.id.selsubject);
        Spinner map = (Spinner) findViewById(R.id.ap);
        EditText mda = findViewById(R.id.feddate);
        tsub = msub.getSelectedItem().toString();
        tap = map.getSelectedItem().toString();
        tda = mda.getText().toString();

        TextView err=findViewById(R.id.error);
        if(tda.equals("")){
            err.setText("*Enter Date");
            return;
        }

        String [] par = tda.split("-");
        if((par[2].equals("2019"))&&((par[1].equals("1"))||(par[1].equals("2"))||(par[1].equals("3"))||(par[1].equals("4"))||(par[1].equals("5")))){
            err.setText(" ");
            wfile(v);
        }
        else{
            err.setText("*invalid input(date should be between 1-1-2019 to 31-5-2019)");
        }
    }
    public void wfile(View v){
        String as;
        if(tap.equals("Absent")){
            as="A.txt";
        }
        else{
            as="P.txt";
        }
        String FILENAME = tsub+as;
        try {
            FileOutputStream fos = openFileOutput(FILENAME, getApplicationContext().MODE_APPEND);
            fos.write(tda.getBytes());
            fos.write("\n".getBytes());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void act(View v){
        Intent intent = new Intent(this, Show.class);
        startActivity(intent);

    }
}
