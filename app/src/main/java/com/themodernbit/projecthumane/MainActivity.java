package com.themodernbit.projecthumane;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.themodernbit.projecthumane.Beta.FragmentAdapter;
import com.themodernbit.projecthumane.CameraActions.CameraActivity;
import com.themodernbit.projecthumane.User.UserDBHandler;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LevelFragment.OnFragmentInteractionListener, MainFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener, UserFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextView TitleText;
    private final String LevelName = "Beginner";

    private UserDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguageForApp("en");


        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Project Humane");
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);

        Intent PreviousIntent = new Intent();

        if(PreviousIntent.getExtras() != null)
       // dbHandler = PreviousIntent.getExtras().getParcelable("key");


        TitleText = (TextView) findViewById(R.id.textViewTitle);


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

/*
        try {
          //  loadUser(getCurrentFocus());
        }

        catch (Exception e){
            Intent theIntent = new Intent(this,SignUpActivity.class);
            startActivity(theIntent);
        }

        */

        ViewPager vp_pages = (ViewPager) findViewById(R.id.vp_pages);
        PagerAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        vp_pages.setAdapter(pagerAdapter);


        TabLayout tbl_pages = (TabLayout) findViewById(R.id.tbl_pages);
        tbl_pages.setupWithViewPager(vp_pages);




    }

    public void loadUser(View view) {
            TitleText.setText(dbHandler.loadHandler());
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int count = getFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            toolbar.setTitle("Project Humane");
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
                DealWithFragments(new SettingsFragment(), "Settings");
                toolbar.setVisibility(View.VISIBLE);
                fab.setVisibility(View.VISIBLE);
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


    private void setLanguageForApp(String languageToLoad){
        Locale locale;
        if(languageToLoad.equals("not-set")){ //use any value for default
            locale = Locale.getDefault();
        }
        else {
            locale = new Locale(languageToLoad);
        }
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public void onResume(){
        super.onResume();
        fab.setVisibility(View.VISIBLE);
    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
