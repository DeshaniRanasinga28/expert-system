package com.example.ef.expert.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by EF on 10/27/2017.
 */

public class Case implements Serializable{
    private String id;
    private String text;
    private String image;
    private ArrayList<Radio> answer;

    public Case(){

    }

    public Case(String id, String text, String image, ArrayList<Radio> answer) {
        this.id = id;
        this.text = text;
        this.image = image;
        this.answer = answer;
    }


    public Case(String textid, String caseid) {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Radio> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Radio> answer) {
        this.answer = answer;
    }
}
