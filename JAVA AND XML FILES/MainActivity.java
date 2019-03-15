package com.example.nithish.loginapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //declaring constants
    private Toolbar toolbar;

    //declaring firebse
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize toolbar
        toolbar = (Toolbar)findViewById(R.id.main_tbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Loginact");







        //initialize firebase
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            tostart();
        }
        
    }

    public void tostart(){
        Intent mts = new Intent(MainActivity.this,StartActivity.class);
        startActivity(mts);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId()){
             case R.id.main_toolbar_menu_logout:FirebaseAuth.getInstance().signOut();
                                                tostart();
                                                break;
             case R.id.main_toolbar_menu_delete:currentUser.delete();
                                                tostart();
                                                break;
         }
        return true;
    }





}
