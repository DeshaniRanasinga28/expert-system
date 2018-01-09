package com.example.ef.expert.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ef.expert.MainActivity;
import com.example.ef.expert.MyVolley;
import com.example.ef.expert.R;
import com.example.ef.expert.ScenarioActivity;
import com.example.ef.expert.adapter.ScenarioAdapter;
import com.example.ef.expert.json.JsonPasser;
import com.example.ef.expert.model.Scenario;
import com.example.ef.expert.util.Config;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScenarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScenarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScenarioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    String server_url = Config.CENARIO_URL;
    ArrayList<Scenario>items;
    ListView listView;
    private ScenarioAdapter adapter;
    Scenario scenario;
    private View rootView;

    public ScenarioFragment() {
        // Required empty public constructor
    }

    /**
     *
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScenarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScenarioFragment newInstance(String param1, String param2) {
        ScenarioFragment fragment = new ScenarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_scenario, container, false); //call Layout(Fragment_Scenario)
        setUpView();
        return rootView;
    }

    private void setUpView() {
        items = new ArrayList<Scenario>();
        listView = (ListView)rootView.findViewById(R.id.case_listView);
        adapter = new ScenarioAdapter(getActivity(),items);
        listView.setAdapter(adapter);  // set Adapter

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                click(i);
            }
        });
        loadList();
        adapter.notifyDataSetChanged();
    }

    private void click(int i) {
        Scenario clickedItem = adapter.getItem(i);
        Intent caseid_01_Intent = new Intent(getActivity(), ScenarioActivity.class);
        caseid_01_Intent.putExtra("Scenario",clickedItem);
        startActivity(caseid_01_Intent);
    }

    private void loadList(){
        Log.d("response", "response");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response){
                ArrayList<Scenario> result = JsonPasser.getSenarios(response);
                if(result != null){
                    items.clear();
                    items.addAll(result);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.d("Error", "result");
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("response", "Error");
            }
        });
        MyVolley.getRequestQueue().add(stringRequest);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
