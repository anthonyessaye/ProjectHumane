package com.themodernbit.projecthumane;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.themodernbit.projecthumane.CameraActions.CameraActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LevelFragment.OnFragmentInteractionListener, MainFragment.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private String LevelName = "Beginner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Project Humane");
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Float button opens camera view
               // DealWithFragments(new CameraFragment(), "Camera");
               // fab.setVisibility(View.GONE);
               // toolbar.setVisibility(View.GONE);
                Intent theIntent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(theIntent);

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // This piece of code is for nav bar
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DealWithFragments(new MainFragment(), "Main");
    }


    /* This code is for the buttons */

    public void onBeginnerClick(View view){
        DealWithFragments(new LevelFragment(), "Beginner");
    }

    public void onIntermediateClick(View view){
        DealWithFragments(new LevelFragment(), "Intermediate");
    }

    public void onAdvancedClick(View view){
        DealWithFragments(new LevelFragment(), "Advanced");
    }

    public void onExpertClick(View view){
        DealWithFragments(new LevelFragment(), "Expert");
    }

    public void onExtrasClick(View view){
        DealWithFragments(new LevelFragment(),"Extras");
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int count = getFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            toolbar.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.



        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                DealWithFragments(new MainFragment(), "Home");
                toolbar.setVisibility(View.VISIBLE);
                fab.setVisibility(View.VISIBLE);
                break;

            case R.id.nav_camera:
                Intent theIntent = new Intent(this, CameraActivity.class);
                startActivity(theIntent);

                toolbar.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                break;

            case R.id.nav_settings:
                break;

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Function to restack fragments
    public void DealWithFragments(Fragment aFragment, String theTitle){



        Fragment newFragment = null;
        newFragment =  aFragment;
        toolbar.setTitle(theTitle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack("");
        ft.replace(R.id.content_main, newFragment);
        ft.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
