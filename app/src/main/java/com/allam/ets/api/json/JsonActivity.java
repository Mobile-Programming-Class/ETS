package com.allam.ets.api.json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.allam.ets.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonActivity extends AppCompatActivity {
    private ListView lv;
    ArrayList<HashMap< String, String >> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_json);

        contactList = new ArrayList < > ();
        lv = (ListView) findViewById(R.id.lv);
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask< Void, Void, Void > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(JsonActivity.this,
                    "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void...arg0) {

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://allam0053.github.io/api/kontak.json";
            String jsonStr = sh.makeServiceCall(url);
            Log.e("Main", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("contacts");
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");
                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");
                        // tmp hash map for single contact
                        HashMap < String, String > contact = new HashMap < > ();
                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);
                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e("Main", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage() + "\ndata:\n" + jsonStr,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e("Main", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(JsonActivity.this, contactList,
                    R.layout.list_item_api_json, new String[] {
                    "id",
                    "name",
                    "email",
                    "mobile"
            },
                    new int[] {
                            R.id.id, R.id.name, R.id.email, R.id.mobile
                    });
            lv.setAdapter(adapter);
        }
    }
}