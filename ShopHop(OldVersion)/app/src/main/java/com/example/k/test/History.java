package com.example.k.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.parse.*;
import java.util.*;
import android.util.Log;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
/**
 * Created by k on 12/2/14.
 */
public class History extends ActionBarActivity {

    //String output;
    ArrayList<String> listNames = new ArrayList<String>();
    ArrayList<String> myList = new ArrayList<String>();
    ListView listView;

    String listNameWeClicked;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);

        MenuItem settings = menu.findItem(R.id.PleaseShowUp);
        settings.setIcon(R.drawable.threelines);
        settings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);



        return true;
    }













    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prebuiltlayout);
        listView = (ListView)findViewById(R.id.listhistory);
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listNames);
        listView.setAdapter(myAdapter);




        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // When clicked, show a toast with the TextView text
                //Log.d("CLICKED", "asdf;lkjaf;lgkjas;lkgadfjg");
                listNameWeClicked = listNames.get(position).toString();
                Log.d(listNameWeClicked, "THIS NAME WAS CLIKCED");
                final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("item");
                query2.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            for (ParseObject obj: objects) {

                                //build list view and set adapted
                                //myList = new ArrayList<Item>();
                                //listNames.add(obj.get())


                                if (obj.containsKey(listNameWeClicked)) {
                                    //listNames.add(obj.get("name").toString());
                                    myList.add(obj.get("name").toString());
                                    Intent i = new Intent(History.this,BuildList.class);
                                    i.putExtra("preloaded",myList );


                                    startActivity(i);
                                    overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_in_top);
                                    //    Log.d(obj.get("name").toString(), "ted");

                                }

                            }

                            //String output = objects.get(0).get("name").toString();
                            //Log.d(output, "SDF:LSDF");
                        } else {

                        }
                    }
                });

            }
        });








        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView parentView, View childView, int position, long id)
            {
                //Here write your code for starting the new activity on selection of list item
                //Log.d("REACHED HERE", "PROBABLY NOT GOIGN TO SEE THIS");
                listNameWeClicked = listNames.get(position).toString();
                Log.d(listNameWeClicked, "THIS NAME WAS CLIKCED");
                final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("item");
                query2.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            for (ParseObject obj: objects) {

                                //build list view and set adapted
                                //myList = new ArrayList<Item>();
                                //listNames.add(obj.get())


                                if (obj.containsKey(listNameWeClicked)) {
                                    //listNames.add(obj.get("name").toString());
                                    myList.add(obj.get("name").toString());
                                    Intent i = new Intent(History.this,BuildList.class);
                                    i.putExtra("preloaded",myList );


                                    startActivity(i);
                                    //    Log.d(obj.get("name").toString(), "ted");

                                }

                            }

                            //String output = objects.get(0).get("name").toString();
                            //Log.d(output, "SDF:LSDF");
                        } else {

                        }
                    }
                });






            }
            public void onNothingSelected(AdapterView parentView)
            {
            }
        });


        Parse.initialize(this, "0df8dFjqd2Dlx0YMTCym2nIVXfJyv0tpu2PSELdo", "CKYXNR2NLldJYCT3CdJ7O2B4xSJKgyuTmFAFL3NV");
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("UserList");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject list : objects) {
                        //output = objects.get(0).get("name").toString();
                        listNames.add(list.get("name").toString());
                        myAdapter.notifyDataSetChanged();
                    }

                    //Log.d(output, "SDF:LSDF");
                } else {

                }
            }
        });



    }
}
