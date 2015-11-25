package com.example.k.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.*;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import com.parse.*;
import android.graphics.Color;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.view.inputmethod.*;

/**
 * Created by k on 12/1/14.
 */
public class BuildList extends ActionBarActivity {

    private EditText userInput;
    private EditText listName;
    private Button addItem;
    private Button chooseStore;

    private ImageButton actualChooseStore;



    private ArrayList<Item> myList = new ArrayList<Item>();
    ItemArrayAdapter customAdapter;
    boolean loadedList = false;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildlist);

        ArrayList<String> dataRecieved;
        if ((dataRecieved = getIntent().getStringArrayListExtra("preloaded"))!= null){
            loadedList = true;
            for (String itemSuccessfullyRetrieved : dataRecieved) {
                for (Item i: ItemssSingleton.get().getKnownItems()){
                    if (i.getName().equalsIgnoreCase(itemSuccessfullyRetrieved)){
                        myList.add(i);
                    }
                }
                //String itemNameRecovered = dataRecieved.get(0);
                Log.d(itemSuccessfullyRetrieved, "ERICPAULOS");
            }
        }

        Parse.initialize(this, "0df8dFjqd2Dlx0YMTCym2nIVXfJyv0tpu2PSELdo", "CKYXNR2NLldJYCT3CdJ7O2B4xSJKgyuTmFAFL3NV");

        /*
        Item firstTestItem = new Item("apple", "that's how gravity words");
        Item secondTestItem = new Item("apple2", "just a clone");

        ArrayList<Item> testItemList = new ArrayList<Item>();
        //testItemList.add(firstTestItem);
        //testItemList.add(secondTestItem);

        ListView itemList = (ListView)findViewById(R.id.itemList);
        ItemArrayAdapter customAdapter = new ItemArrayAdapter(this,testItemList);

        itemList.setAdapter(customAdapter);
        //pls work  SUCCESS
        */

        userInput = (EditText)findViewById(R.id.userItemInput);
        listName = (EditText)findViewById(R.id.userListName);
        addItem = (Button)findViewById(R.id.addButton);
        addItem.setBackgroundColor(Color.rgb(195,195,169));
        chooseStore = (Button)findViewById(R.id.chooseStore);
        chooseStore.setBackgroundColor(Color.rgb(195,195,169));
        ListView itemList = (ListView)findViewById(R.id.itemList);

        //ArrayList<Item> knownItems = ItemssSingleton.get().getKnownItems(); //not really necessary for now--but see comments on the data class-could be useful

        customAdapter = new ItemArrayAdapter(this,myList);
        itemList.setAdapter(customAdapter);



        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText myEditText = (EditText) findViewById(R.id.userItemInput);
                InputMethodManager imm = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);



                Log.d("ADD ITEM CLICKED", "WE SHOULD BE SEEING THIS");
                String test = userInput.getText().toString();
                Item result = ItemssSingleton.get().isValidInput(test);
                if (result !=null){ //This means that the input is valid
                    for (Item i: myList){
                        if (i.getName().equalsIgnoreCase(result.getName())){
                            Toast.makeText(getApplicationContext(),"This item has already been added.",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    myList.add(result);
                    customAdapter.notifyDataSetChanged();
                } else {
                    //case where invalid

                    Toast.makeText(getApplicationContext(),"Can't locate this item.",Toast.LENGTH_LONG).show();

                }
            }
        });

        chooseStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText myEditText2 = (EditText) findViewById(R.id.userListName);
                InputMethodManager imm2 = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm2.hideSoftInputFromWindow(myEditText2.getWindowToken(), 0);

                String userListName = listName.getText().toString();

                if (userListName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a name for your list.", Toast.LENGTH_LONG).show();
                    return;
                }
                ParseObject userList = new ParseObject("UserList");
                userList.put("name",userListName);
                userList.saveInBackground();

                //ArrayList<ParseObject> myItems = new ArrayList<ParseObject>();

                if (myList.size() == 0) {
                    Toast.makeText(getApplicationContext(), "You cannot save an empty list.", Toast.LENGTH_LONG).show();
                    return;
                }
                for (Item i : myList){
                    final ParseObject item = new ParseObject("item");
                    item.put("name", i.getName());
                    item.put("description", i.getDesc());
                    //item.put("parent", userList);
                    //item.saveInBackground();
                    //userList.put("item",item);
                    //userList.saveInBackground();

                    //myItems.add(item);
                    item.put(userListName, userListName); //my final roundabout solution. have some ideas hazy in this late hour but I'll end it with this
                    item.saveInBackground();
                }
                /*
                for (int i = 0; i<myItems.size();i++) {
                    ParseObject itemToAdd = myItems.get(i);
                    userList.put(Integer.toString(i+1),itemToAdd);
                    userList.saveInBackground();
                }
                */
                Toast.makeText(getApplicationContext(), "List saved successfully.", Toast.LENGTH_LONG).show();

            }
        });

        actualChooseStore = (ImageButton)findViewById(R.id.findStore);
        actualChooseStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "About to start new activity", Toast.LENGTH_LONG).show();
                Intent i = new Intent(BuildList.this, ChooseStore.class);
                ArrayList<String> names = new ArrayList<String>();
                for(Item item: myList){
                    names.add(item.getName());
                }
                i.putExtra("itemNames", names);
                //i.putExtra("imagePath", R.id.)
                startActivity(i);
            }
        });

    }


    private class ItemArrayAdapter extends ArrayAdapter<Item> {
        Context c;
        public ItemArrayAdapter(Context c, ArrayList<Item> items){
            super(c,0,items);
            this.c = c;
        }

        public View getView(int pos, View convertView, ViewGroup parent){
            if (convertView == null){
                LayoutInflater l = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = l.inflate(R.layout.itemrow,parent,false);
            }
            //ImageView itemPic = (ImageView)convertView.findViewById(R.id.itemPic);
            //DO THIS PART LATER--FOR NOW LEAVE THE RED BALL

            Item i = getItem(pos);

            TextView itemName = (TextView)convertView.findViewById(R.id.itemName);
            TextView itemDesc = (TextView)convertView.findViewById(R.id.itemDesc);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.itemPic);


            imageView.setImageResource(i.getImagePath());



            itemName.setText(i.getName());
            itemDesc.setText(i.getDesc());

            return convertView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);



        MenuItem settings = menu.findItem(R.id.PleaseShowUp);
        settings.setIcon(R.drawable.threelines);
        settings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
