package com.example.ef.expert.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ef.expert.R;
import com.example.ef.expert.fragment.ScenarioFragment;
import com.example.ef.expert.model.Scenario;

import java.util.ArrayList;

/**
 * Created by EF on 10/26/2017.
 */

public class ScenarioAdapter extends ArrayAdapter<Scenario>{

    private final Context context;

    public ScenarioAdapter(Context context, ArrayList<Scenario> items) {
        super(context, 0, items);
        this.context = context;
    }

    public static class ViewHolder{
        public TextView titleTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View rowView;
        if(convertView == null){
            rowView = LayoutInflater.from(context).inflate(R.layout.item_scenario, parent, false);  //ID fo List Item Scenario
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) rowView.findViewById(R.id.titleTextView);  // ID of the textView Populate
            rowView.setTag(viewHolder);
            rowView.setTag(viewHolder);
        }else{
            rowView = convertView;
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        Scenario scenario = getItem(position);
        holder.titleTextView.setText(scenario.getText());
        return rowView;
    }
}
