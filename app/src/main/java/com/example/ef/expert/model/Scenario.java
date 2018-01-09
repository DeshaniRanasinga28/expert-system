package com.example.ef.expert.model;

import java.io.Serializable;

/**
 * Created by EF on 10/26/2017.
 */

public class Scenario implements Serializable{
    private String caseid;
    private String text;

    public Scenario(){

    }

    public Scenario(String caseid, String text) {
        this.caseid = caseid;
        this.text = text;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
