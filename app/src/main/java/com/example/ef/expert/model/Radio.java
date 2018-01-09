package com.example.ef.expert.model;

import java.io.Serializable;

/**
 * Created by EF on 10/27/2017.
 */

public class Radio implements Serializable{
    private String text;
    private String caseid;

    public Radio(){

    }

    public Radio(String text,String caseid) {
        this.text = text;
        this.caseid = caseid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseId(String caseid) {
        this.caseid = caseid;
    }
}
