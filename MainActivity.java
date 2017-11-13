package com.wlacu.exapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        Button Send_Button = (Button) findViewById(R.id.Send_Button);
        final TextInputEditText Message_Text_input = (TextInputEditText) findViewById(R.id.Message_Text_input);
        final LinearLayout Message_History = (LinearLayout) findViewById(R.id.Message_History);
        final ScrollView Message_ScrollView = (ScrollView) findViewById(R.id.Message_ScrollView);
        final CardView SenderCardView = (CardView) findViewById(R.id.Sender_Cardview_Model);
        final TextView SenderTextViewModel = (TextView) findViewById(R.id.Sender_Text_Model);

        Send_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView Message_Cardview = new CardView(getApplicationContext());
                Message_Cardview.setLayoutParams(SenderCardView.getLayoutParams());
                Message_Cardview.setContentPadding(SenderCardView.getContentPaddingLeft(),SenderCardView.getContentPaddingTop(),SenderCardView.getContentPaddingRight(),SenderCardView.getContentPaddingBottom());
                Message_Cardview.setPadding(SenderCardView.getPaddingLeft(),SenderCardView.getPaddingTop(),SenderCardView.getPaddingRight(),SenderCardView.getPaddingBottom());
                Message_Cardview.setRadius(SenderCardView.getRadius());
                Message_Cardview.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                TextView Message_text = new TextView(getApplicationContext());
                Message_text.setLayoutParams(SenderTextViewModel.getLayoutParams());
                //Message_text.setTextAppearance(getResources().getInteger());

                Message_Cardview.addView(Message_text);
                Message_text.setText(Message_Text_input.getText());
                Message_Text_input.setText("");
                Message_History.addView(Message_Cardview);
                Message_ScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        Message_ScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
