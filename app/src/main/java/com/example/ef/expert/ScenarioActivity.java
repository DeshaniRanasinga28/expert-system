package com.example.ef.expert;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ef.expert.json.JsonPasser;
import com.example.ef.expert.model.Case;
import com.example.ef.expert.model.Radio;
import com.example.ef.expert.model.Scenario;
import com.example.ef.expert.util.Config;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Stack;

public class ScenarioActivity extends AppCompatActivity {
    String server_url = Config.CASE_URL;
    private Case aCase;
    TextView textViewId;
    TextView textViewCase;
    ImageView imageView;
    RadioButton yesRadioButton;
    RadioButton noRadioButton;
    private static final String KEY_TEXT_ID_VALUE = "textIdValue";
    private static final String KEY_TEXT_CASE_VALUE = "textCaseValue";
    private static final String KEY_IMAGE_VIEW_VALUE = "imageViewValue";
    private static final String KEY_YES_RADIO_BUTTON_VALUE = "yesRadioButtonValue";
    private static final String KEY_NO_RADIO_BUTTON_VALUE = "noRadioButtonValue";
    ArrayList<Radio> answers = new ArrayList<Radio>();
    //create Stack object
    ArrayList<Case> stack = new ArrayList<Case>();
    public ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);

        Scenario scenario = (Scenario)getIntent().getSerializableExtra("Scenario");
        setUpView(scenario);

        textViewId = (TextView)findViewById(R.id.id_textView);
        textViewCase = (TextView)findViewById(R.id.case_textView);
        imageView = (ImageView)findViewById(R.id.case_imageView);
        yesRadioButton = (RadioButton)findViewById(R.id.yes_radioButton);
        noRadioButton = (RadioButton)findViewById(R.id.no_radioButton);

        if (savedInstanceState != null) {
            CharSequence savedTextID = savedInstanceState.getString(KEY_TEXT_ID_VALUE);
            textViewId.setText(savedTextID);

            CharSequence savedTextCase = savedInstanceState.getString(KEY_TEXT_CASE_VALUE);
            textViewCase.setText(savedTextCase);
            /*
            CharSequence savedImage = savedInstanceState.getCharSequence(KEY_IMAGE_VIEW_VALUE);
            imageView.setImageResource(savedImage);
            */
            CharSequence savedYesRadioButtonTrxt = savedInstanceState.getString(KEY_YES_RADIO_BUTTON_VALUE);
            yesRadioButton.setText(savedYesRadioButtonTrxt);

            CharSequence savedNoRadioButtonTrxt = savedInstanceState.getString(KEY_NO_RADIO_BUTTON_VALUE);
            noRadioButton.setText(savedNoRadioButtonTrxt);
        }
//         loadCase(aCase.getId());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TEXT_ID_VALUE, textViewId.getText().toString());
        outState.putString(KEY_TEXT_CASE_VALUE, textViewCase.getText().toString());
        //image
        outState.putString(KEY_YES_RADIO_BUTTON_VALUE, yesRadioButton.getText().toString());
        outState.putString(KEY_NO_RADIO_BUTTON_VALUE, noRadioButton.getText().toString());
    }

    private void setUpView(Scenario scenario) {
        textViewId = (TextView)findViewById(R.id.id_textView);
        textViewCase = (TextView)findViewById(R.id.case_textView);
        imageView = (ImageView) findViewById(R.id.case_imageView);
        yesRadioButton = (RadioButton)findViewById(R.id.yes_radioButton);
        noRadioButton = (RadioButton)findViewById(R.id.no_radioButton);

        yesRadioButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Case aCase;
                String caseid = answers.get(0).getCaseid();
                if(caseid != null){
                    showProgressDialog();
                    loadCase(caseid);
                    Log.d("erre", "caseid" + caseid);
                } else {
                    Log.d("Error", "caseid" + caseid);
                }
                answers.clear();
            }
        });
        loadCase(scenario.getCaseid());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loadCase(final String caseid) {
        //progress.dismiss();
        stack.add(aCase);  // add values for stacks

        String new_server_url = server_url + caseid;
        StringRequest stringrequest = new StringRequest(Request.Method.GET, new_server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ScenarioActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                Log.d("response", response);
                Case caseResponse = JsonPasser.getCases(response);
                if (caseResponse != null) {
                    aCase = caseResponse;
                    showView(aCase);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewId.setText("somthing went wroong...!!");
                error.printStackTrace();
            }
        });

        MyVolley.getRequestQueue().add(stringrequest);
    }


    private void showView(Case aCase) {
        Stack ca = new Stack();  //create Stacks object
        yesRadioButton.setChecked(false);
        noRadioButton.setChecked(false);

        textViewId = (TextView)findViewById(R.id.id_textView);
        if (aCase != null) {
            if (aCase.getId() != null) {
                textViewId.setText(aCase.getId());
            }

            textViewCase = (TextView)findViewById(R.id.case_textView);
            if (aCase.getText() != null) {
                textViewCase.setText(aCase.getText());
            }

            if (aCase.getId().equals("1")) {
                String image_url = "http://ws.net23.net/images/power.png";//"http://ws.net23.net//images//power.png";
                Picasso.with(imageView.getContext())
                        .load(image_url)
                        .resize(250, 250)
                        .centerCrop()
                        .into(imageView);
                imageView = (ImageView)findViewById(R.id.case_imageView);
            } else if (aCase.getId().equals("31")) {
                String image_url = "http://ws.net23.net/images/slowapps.png";//"http://ws.net23.net//images//power.png";
                Picasso.with(imageView.getContext())
                        .load(image_url)
                        .resize(250, 250)
                        .centerCrop()
                        .into(imageView);
                imageView = (ImageView)findViewById(R.id.case_imageView);
            } else if (aCase.getId().equals("4")) {
                String image_url = "http://ws.net23.net/images/mouse-key.png";//"http://ws.net23.net//images//power.png";
                Picasso.with(imageView.getContext())
                        .load(image_url)
                        .resize(300, 300)
                        .centerCrop()
                        .into(imageView);
                imageView = (ImageView)findViewById(R.id.case_imageView);
            }

            if (aCase.getAnswer() != null) {
                if (aCase.getAnswer().size() == 2) {
                    answers = aCase.getAnswer();
                    //call to Json answer Object
                    yesRadioButton = (RadioButton)findViewById(R.id.yes_radioButton);
                    yesRadioButton.setText(answers.get(0).getText());
                    yesRadioButton.setTag(answers.get(0).getCaseid());
                    Radio answer1 = aCase.getAnswer().get(0);
                    //call to Json answer Object
                    noRadioButton = (RadioButton)findViewById(R.id.no_radioButton);
                    noRadioButton.setText(answers.get(1).getText());
                    noRadioButton.setTag(answers.get(1).getCaseid());
                    Radio answer2 = aCase.getAnswer().get(1);
                    showRadio(View.VISIBLE);
                } else {
                    showRadio(View.INVISIBLE);
                }
            }
        }
    }

    private void showRadio(int invisible) {
        yesRadioButton.setVisibility(invisible);
        noRadioButton.setVisibility(invisible);
    }

    @Override
    public void onBackPressed() {

        if (stack.isEmpty()) {
            super.onBackPressed();

        } else {
            //going to back activity
            int index = stack.size() - 1;

            Case ss = stack.get(index);
            showView(ss);
        }

    }
    private void showProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.setMessage("Logging in. Please wait.");
        progress.show();

        new Thread(){
            @Override
            public void run() {
                //super.run();
                try{
                    sleep(200);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress.dismiss();
            }
        }.start();
    }

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
}

