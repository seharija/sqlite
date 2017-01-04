package com.example.seharija.sqlite;

/**
 * Created by Seharija on 2016-12-29.
 */


public class Patients {
    private int _id;
    private String _patientname;


    public Patients(){

    }
    public Patients(String patientName) {
        this._patientname = patientName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_patientname() {
        return _patientname;
    }

    public void set_patientname(String _patientname) {
        this._patientname = _patientname;
    }
}