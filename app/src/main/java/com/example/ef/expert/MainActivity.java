package com.example.ef.expert;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ef.expert.adapter.ScenarioAdapter;
import com.example.ef.expert.fragment.ScenarioFragment;
import com.example.ef.expert.json.JsonPasser;
import com.example.ef.expert.model.Scenario;
import com.example.ef.expert.util.Config;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ScenarioFragment.OnFragmentInteractionListener {

    String server_url = Config.CENARIO_URL;  // call a Scenario URL
    ArrayList<Scenario>items;
    ListView listView;
    private ScenarioAdapter adapter;
    Scenario scenario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkInternetConnection();

        //connect with Fragment class
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.mainContainer, ScenarioFragment.newInstance("", ""), "rageComicList")
                    .commit();
        }

        //Implement a ScenarioFragment.java class

//        items = new ArrayList<Scenario>();
//        listView = (ListView)findViewById(R.id.case_listView);
//        adapter = new ScenarioAdapter(this, items);
//        listView.setAdapter(adapter);  // set Adapter
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//               click(i);
//            }
//        });
//        loadList();
//        adapter.notifyDataSetChanged();
    }

    //Check Internet Connection
    public boolean checkInternetConnection(){
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
            return true;
        }else {
            Toast.makeText(this, "No Network Connection Available", Toast.LENGTH_LONG).show();
            return false;
        }
    }

//implement a ScenarioFragemnt.java class
//     private void click(int i) {
//        Scenario clickedItem = adapter.getItem(i);
//        Intent caseid_01_Intent = new Intent(MainActivity.this, ScenarioActivity.class);
//        caseid_01_Intent.putExtra("Scenario",clickedItem);
//        startActivity(caseid_01_Intent);
//    }

    @Override
    protected void onResume(){
        super.onResume();
    }

//implement a ScenarioFragment.java class
//    private void loadList(){
//        Log.d("response", "response");
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url, new Response.Listener<String>(){
//
//            @Override
//            public void onResponse(String response){
//                ArrayList<Scenario> result = JsonPasser.getSenarios(response);
//                if(result != null){
//                    items.clear();
//                    items.addAll(result);
//                    adapter.notifyDataSetChanged();
//                }else{
//                    Log.d("Error", "result");
//                }
//            }
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error){
//                Log.d("response", "Error");
//            }
//        });
//        MyVolley.getRequestQueue().add(stringRequest);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_setting) {
            return true;
        }

        if(id == R.id.action_help){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
