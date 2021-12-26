package com.aminov.a3ticketekz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner sFlights;
    ListView lvPassengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sFlights = findViewById(R.id.sFlights);
        lvPassengers = findViewById(R.id.lvPassengers);

        InputStream inputStream = getResources().openRawResource(R.raw.passengers_and_flights);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int b;
        try {
            b = inputStream.read();
            while (b != -1) {
                byteArrayOutputStream.write(b);
                b = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(byteArrayOutputStream.toString());
            int number = 0;
            String fio = "";
            String dateBirth = "";
            JSONArray array2;
            ArrayList<Passenger> passengers = new ArrayList<>();
            ArrayList<String> flights = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                number = array.getJSONObject(i).getInt("Number");
                fio = array.getJSONObject(i).getString("Fio");
                dateBirth = array.getJSONObject(i).getString("DateBirth");
                array2 = array.getJSONObject(i).getJSONArray("NamesFlights");
                ArrayList<String> namesFlights = new ArrayList<>();
                for (int j = 0; j < array2.length(); j++) {
                    String nameFlights = array2.getString(j);
                    namesFlights.add(array2.getString(j));
                    if (!flights.contains(nameFlights))
                        flights.add(nameFlights);
                }
                passengers.add(new Passenger(number, fio, dateBirth, namesFlights));
            }
            ArrayAdapter<String> spAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, flights);
            spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sFlights.setAdapter(spAdapter);
            sFlights.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    ArrayList<Passenger> passengers_flight = new ArrayList<>();
                    passengers.forEach(p -> {
                        if (p.getNameFlight().contains(item))
                            passengers_flight.add(p);
                    });
                    MyAdapter myAdapter = new MyAdapter(getApplicationContext(), R.layout.item_list, passengers_flight);
                    lvPassengers.setAdapter(myAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}