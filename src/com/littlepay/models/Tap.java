package com.littlepay.models;

import com.littlepay.enums.TapType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tap {

    private int ID;
    private LocalDateTime dateTime;
    private TapType tapType;
    private String stopId;
    private String companyID;
    private String busID;
    private String PAN; //TODO consider converting to int

    public void setPan(String pan) {
        if(String.valueOf(pan).length() < 13 || String.valueOf(pan).length() > 19) {
            String message = String.format("Invalid PAN number for card provided: %s", pan);
            System.out.println(message);
            PAN = "0";
            return;
        }

        PAN = pan;
    }

    public String getPan() {
        return PAN;
    }

    public void setDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        dateTime = LocalDateTime.from(dateFormatter.parse(date));
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTapType(String tapType) {
        this.tapType = TapType.valueOf(tapType);
    }

    public TapType getTapType() {
        return this.tapType;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId; //TODO: validate passed in format
    }

    public String getStopId() {
        return stopId;
    }

    public String getCompanyId() {
        return companyID;
    }

    public String getBusId() {
        return busID;
    }

    public Tap(){}

    public Tap(String id, String dateTimeLocal, String tapType, String stopId, String companyID, String busID, String PAN){
        ID = Integer.parseInt(id);
        setDate(dateTimeLocal);
        setTapType(tapType);
        setStopId(stopId);
        this.companyID = companyID;
        this.busID = busID;
        setPan(PAN);
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s, %s, %s, %s, %s", ID, dateTime, tapType, stopId, companyID, busID, PAN);
    }


}
