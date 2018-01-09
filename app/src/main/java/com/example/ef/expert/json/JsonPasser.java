package com.example.ef.expert.json;

import android.util.Log;

import com.example.ef.expert.model.Case;
import com.example.ef.expert.model.Radio;
import com.example.ef.expert.model.Scenario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by EF on 10/26/2017.
 */

public class JsonPasser {
    private static JSONObject object;

    //JSON pass Scenario call object
    public static ArrayList<Scenario> getSenarios(String response){
        //create Scenario class Array object
        ArrayList<Scenario> items = new ArrayList<Scenario>();
        try{
            JSONArray jsonArray = new JSONArray(response);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject sce = jsonArray.getJSONObject(i);
                String caseid = sce.getString("caseid");
                String text = sce.getString("text");
                items.add(new Scenario(caseid, text));
            }
            return items;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    //JSON pass call case object
    public static Case getCases(String response){
        try{
            JSONArray jObject = new JSONArray(response);
            String id = jObject.getJSONObject(0).getString("id");
            String text  = jObject.getJSONObject(0).getString("text");
            String image = "";
            if(jObject.getJSONObject(0).has("image")){
                image = jObject.getJSONObject(0).getString("image");
            }
            JSONArray jsonArray = jObject.getJSONObject(0).getJSONArray("answers");
            ArrayList<Radio> answer = new ArrayList<Radio>();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String textId = jsonObject.getString("text");
                String caseId = jsonObject.getString("caseid");
                answer.add(new Radio(textId, caseId));
            }
            return new Case(id, text, image, answer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
