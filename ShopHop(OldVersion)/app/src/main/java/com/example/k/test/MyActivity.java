package com.example.k.test;

import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;

import com.parse.*; //I added this myself--"quick start" didn't tell me to--not so quick afterall


public class MyActivity extends ActionBarActivity {

    private ImageButton buildListButton;
    private ImageButton historyListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startmenu);

        historyListButton = (ImageButton)findViewById(R.id.history);
        historyListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.v("HISTORY BUTTON CLICKED", "TRUE");
                Intent i = new Intent(MyActivity.this, History.class);
                startActivity(i);
            }
        });


        buildListButton = (ImageButton)findViewById(R.id.buildList);
        buildListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyActivity.this, BuildList.class);
                startActivity(i);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_in_top);
            }
        });

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
