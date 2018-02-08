package com.example.boytang_subbook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


/*
This Activity design was influenced by:

http://androidallprograms.blogspot.ca/2013/12/android-tutorial-listview-adding-items.html
Accessed on February 5, 2018

as well as the lonelyTwitter App from the labs
 */


//extends AppCompatActivity
public class MainActivity extends Activity {

    //declare variables
    private static final String FILENAME = "sub_list.sav";
    private EditText editTextname;
    private EditText editTextdate;
    private EditText editTextamount;
    private EditText editTextcomment;
    private ListView list;
    private Button addButton;
    private ArrayList<Subscription> subList;
    private SubscriptionAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize variables
        editTextname = (EditText) findViewById(R.id.editText);
        editTextdate = (EditText) findViewById(R.id.editText2);
        editTextamount = (EditText) findViewById(R.id.editText3);
        editTextcomment = (EditText) findViewById(R.id.editText4);
        addButton = (Button) findViewById(R.id.button2);
        list = (ListView) findViewById(R.id.Listy);
        subList = new ArrayList<Subscription>();


        //listen for click on add button
        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                //get data from widget
                String name = editTextname.getText().toString();
                String date = editTextdate.getText().toString();
                /*Taken from https://stackoverflow.com/questions/4229710/string-from-edittext-to-float
                  Accessed February 7, 2018
                */
                float amount = Float.valueOf(editTextamount.getText().toString());
                String comment = editTextcomment.getText().toString();


                Subscription subscription = new Subscription(name, date, amount, comment);
                subList.add(subscription);
                adapter.notifyDataSetChanged();

                saveInFile();


            }
        });

    }



    //Method from lonelyTwitter lab
    @Override
    protected void onStart() {

        // TODO Auto-generated method stub
        super.onStart();
        Log.i("LifeCycle --->", "onStart is called");
        loadFromFile();

        /* Following 2 lines of code from:
           https://stackoverflow.com/questions/30371684/listview-displaying-objects-details
           Accessed February 7, 2018
         */
        adapter = new SubscriptionAdapter(this, R.layout.one_list_element, subList);
        list.setAdapter(adapter);


    }

    //Method from lonelyTwitter lab
    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //Accessed 2018-01-25
            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subList = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            subList = new ArrayList<Subscription>();
        } /*catch (IOException e) {
			e.printStackTrace();
			//throw new RuntimeException();
		}*/

    }

    //Method from lonelyTwitter lab
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush(); //clears buffer

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    /* Attempted delete function; does not work.
       Taken from https://github.com/yacekmm/jacek-droid/blob/master/ListViewDemo/src/pl/looksok/listviewdemo/AddNewPerson.java
       Accessed February 7, 2018
     */
    public void removeSubscription(View v) {
        Subscription removeSub = (Subscription) v.getTag();
        adapter.remove(removeSub);
        adapter.notifyDataSetChanged();
        saveInFile();
    }

    //Method from lonelyTwitter lab
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy is called");
    }
}



