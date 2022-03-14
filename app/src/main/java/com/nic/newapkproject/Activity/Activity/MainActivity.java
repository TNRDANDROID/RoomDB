package com.nic.newapkproject.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nic.newapkproject.Activity.Activity.Adapter.PersonRecyclerAdapter;
import com.nic.newapkproject.Activity.Activity.DataBase.DatabaseClient;
import com.nic.newapkproject.Activity.Activity.Fragment.Entity.PersonDetails;
import com.nic.newapkproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button go;
    CardView download_layout;
    ProgressDialog pd;
    int total_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = findViewById(R.id.go);
        download_layout = findViewById(R.id.download_layout);
        go.setVisibility(View.GONE);

        getPersonDetails(1);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,BootmNavigationBarActivity.class);
                intent.putExtra("total_page",total_page);
                startActivity(intent);
            }
        });
    }

    private class JsonTask extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
          /*  pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();*/
        }

        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject meta = jsonObject.getJSONObject("meta");
                JSONObject pagination = meta.getJSONObject("pagination");
                JSONObject links = pagination.getJSONObject("links");
                JSONArray data = jsonObject.getJSONArray("data");

                int  total =0;
                int pages=0;
                int page=0;
                int limit=0;

                String previous="";
                String current="";
                String next="";

                int id=0;
                String name="";
                String email="";
                String gender="";
                String status="";

                ///getLinks
                if(links.isNull("previous")){
                    previous="";
                }
                else {
                    previous = links.getString("previous");
                }
                current = links.getString("current");
                if(links.isNull("next")){
                    next = "";
                }
                else {
                    next = links.getString("next");
                }

                total = pagination.getInt("total");
                pages = pagination.getInt("pages");
                total_page = pages;
                page = pagination.getInt("page");
                limit = pagination.getInt("limit");

                for (int i=0;i<data.length();i++){
                    PersonDetails data_values = new PersonDetails();
                    id = data.getJSONObject(i).getInt("id");
                    name = data.getJSONObject(i).getString("name");
                    email = data.getJSONObject(i).getString("email");
                    gender = data.getJSONObject(i).getString("gender");
                    status = data.getJSONObject(i).getString("status");
                    data_values.setTotal(total);
                    data_values.setPages(pages);
                    data_values.setPage(page);
                    data_values.setLimit(limit);

                    data_values.setPrevious(previous);
                    data_values.setCurrent(current);
                    data_values.setNext(next);

                    data_values.setId(id);
                    data_values.setName(name);
                    data_values.setEmail(email);
                    data_values.setGender(gender);
                    data_values.setStatus(status);


                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .taskDao()
                            .insert(data_values);
                }
                if(page!=pages) {
                    new JsonTask().execute(next);
                }
                else {

                   /* if (pd.isShowing()) {
                        pd.dismiss();
                    }*/
                    download_layout.setVisibility(View.GONE);
                    go.setVisibility(View.VISIBLE);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void getPersonDetails(int page_count) {
        class GetTasks extends AsyncTask<Void, Void, List<PersonDetails>> {

            @Override
            protected List<PersonDetails> doInBackground(Void... voids) {
                List<PersonDetails> personDetails = DatabaseClient
                        .getInstance(MainActivity.this)
                        .getAppDatabase()
                        .taskDao()
                        .getAll(page_count);
                return personDetails;
            }

            @Override
            protected void onPostExecute(List<PersonDetails> personDetails) {
                super.onPostExecute(personDetails);
                if(personDetails.size()>0){
                    download_layout.setVisibility(View.GONE);
                    go.setVisibility(View.VISIBLE);
                }
                else {
                    new JsonTask().execute("https://gorest.co.in/public/v1/users");
                }

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
