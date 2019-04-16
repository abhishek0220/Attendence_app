package com.attendence.abhis.attendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        final Spinner spap = (Spinner) findViewById(R.id.selsubject2);
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
        ArrayAdapter<String> datasub = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c_sub);
        datasub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spap.setAdapter(datasub);
    }

    String tsub;
    int p=0,a=0,t=0;
    public void onclick(View v){
        Spinner msub = (Spinner) findViewById(R.id.selsubject2);
        tsub = msub.getSelectedItem().toString();
        String FILENAME=tsub+"P.txt";
        String linep="",linea="";
        try{
            FileInputStream fin = openFileInput(FILENAME);
            if (fin != null)
            {
                InputStreamReader inputreader = new InputStreamReader(fin);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                try
                {
                    while ((line = buffreader.readLine()) != null)
                        linep+=line+"\n";
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if(linep.equals("")==false){
            String [] ptemp=linep.split("\n");
            p=ptemp.length;
        }
        FILENAME=tsub+"A.txt";
        try{
            FileInputStream fin = openFileInput(FILENAME);
            if (fin != null)
            {
                InputStreamReader inputreader = new InputStreamReader(fin);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                try
                {
                    while ((line = buffreader.readLine()) != null)
                        linea+=line+"\n";
                    a=a+1;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if(linea.equals("")==false){
            String [] atemp=linea.split("\n");
            a=atemp.length;
        }
        t=a+p;
        TextView tt=findViewById(R.id.atten);
        String sss="Attended "+p+" out of "+t+" classes";
        tt.setText(sss);
        tt=findViewById(R.id.percen);
        int pen;
        if(t==0){
            pen=0;
        }
        else{
            pen=(p*100)/t;
        }

        sss=pen+" %";
        tt.setText(sss);
        tt=findViewById(R.id.textpre);
        sss="Present on \n"+linep;
        tt.setText(sss);
        tt=findViewById(R.id.textab);
        sss="Absent on \n"+linea;
        tt.setText(sss);
        p=0;
        t=0;
        a=0;

    }
}
