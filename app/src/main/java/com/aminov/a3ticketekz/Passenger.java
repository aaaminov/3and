package com.aminov.a3ticketekz;

import java.util.ArrayList;

public class Passenger {
    private int number;
    private String fio;
    private String dateBirth;
    private ArrayList<String> namesFlight;

    public Passenger(int number, String fio, String dateBirth, ArrayList<String> namesFlight) {
        this.number = number;
        this.fio = fio;
        this.dateBirth = dateBirth;
        this.namesFlight = namesFlight;
    }

    public int getNumber() {
        return number;
    }

    public String getFio() {
        return fio;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public ArrayList<String> getNameFlight() {
        return namesFlight;
    }
}
