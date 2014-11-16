package com.example.xander.fappybird;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginFragment extends Fragment {

    private UserData userData = new UserData();
    private Button loginButton;
    private EditText userName;
    private EditText email;
    private Spinner comboBox;
    private View myParentView;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myParentView = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) myParentView.findViewById(R.id.loginButton);
        userName = (EditText) myParentView.findViewById(R.id.userName);
        email = (EditText) myParentView.findViewById(R.id.email);
        comboBox = (Spinner) myParentView.findViewById(R.id.comboBox);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userData.setUserName(userName.getText().toString());
                userData.setEmail(email.getText().toString());
                userData.setScore(125);
                userData.setUniversity(comboBox.getSelectedItem().toString());

                SubmitTask task = new SubmitTask();
                task.execute(userData);

            }
        });

        return myParentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class SubmitTask extends AsyncTask<UserData, Void, String> {
        @Override
        protected String doInBackground(UserData... params) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPut putRequest = new HttpPut("http://95.111.103.224:8080/Flappy/scores");
            putRequest.addHeader("Content-Type", "application/json");
            JSONObject loginObject = new JSONObject();
            try {
                loginObject.put("name", params[0].getUserName());
                loginObject.put("mail", params[0].getEmail());
                loginObject.put("whereFrom", params[0].getUniversity());
                loginObject.put("score", params[0].getScore());

                putRequest.setEntity(new StringEntity(loginObject.toString()));

                HttpResponse response = httpClient.execute(putRequest);

                StatusLine statusLine = response.getStatusLine();

                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    return "Score sent!";
                } else {
                    return "Error sending!";
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Error sending!";
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(myParentView.getContext(), s, Toast.LENGTH_SHORT);
        }
    }
}
