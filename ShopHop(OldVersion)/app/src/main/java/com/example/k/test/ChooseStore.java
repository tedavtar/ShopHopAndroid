package com.example.k.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by k on 12/5/14.
 */
public class ChooseStore extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosestore);
        ImageButton button;
        ImageButton button2;
        ImageButton button3;
        ImageButton button4;
        ImageButton button5;
        ImageButton button6;
        button = (ImageButton)findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "About to start new activity", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChooseStore.this, WatchActivity.class);
                Bundle extras = getIntent().getExtras();
                i.putExtras(extras);
                i.putExtra("sc", 1);
                startActivity(i);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_in_top);

            }
        });

        button2 = (ImageButton)findViewById(R.id.imageButton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "About to start new activity", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChooseStore.this, WatchActivity.class);
                Bundle extras = getIntent().getExtras();
                i.putExtras(extras);
                i.putExtra("sc", 2);
                startActivity(i);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_in_top);

            }
        });

        button3 = (ImageButton)findViewById(R.id.imageButton3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "About to start new activity", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChooseStore.this, WatchActivity.class);
                Bundle extras = getIntent().getExtras();
                i.putExtras(extras);
                i.putExtra("sc", 3);
                startActivity(i);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_in_top);

            }
        });

        button4 = (ImageButton)findViewById(R.id.imageButton4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "About to start new activity", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChooseStore.this, WatchActivity.class);
                Bundle extras = getIntent().getExtras();
                i.putExtras(extras);
                i.putExtra("sc", 4);
                startActivity(i);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_in_top);

            }
        });

        button5 = (ImageButton)findViewById(R.id.imageButton5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "About to start new activity", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChooseStore.this, WatchActivity.class);
                Bundle extras = getIntent().getExtras();
                i.putExtras(extras);
                i.putExtra("sc", 5);
                startActivity(i);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_in_top);

            }
        });

        button6 = (ImageButton)findViewById(R.id.imageButton6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "About to start new activity", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChooseStore.this, WatchActivity.class);
                Bundle extras = getIntent().getExtras();
                i.putExtras(extras);
                i.putExtra("sc", 6);
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
