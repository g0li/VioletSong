package forevtechnologies.alegriauiux;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import forevtechnologies.alegriauiux.adapter.main_fragment.MainFragment;



public class EventsActivity extends AppCompatActivity {

    MainFragment mainFragment;
    public void changeactivity(Activity a, Bundle b)
    {        startActivity(new Intent(a, CartActivity.class).putExtras(b));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root, mainFragment = MainFragment.newInstance())
                    .commit();
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.root);
            if (fragment instanceof MainFragment) {
                mainFragment = (MainFragment) fragment;
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater minf = getMenuInflater();
        minf.inflate(R.menu.menu_registration,menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.cart) {

            startActivity(new Intent(EventsActivity.this,CartActivity.class));


    }
       else if (item.getItemId()==R.id.broch){

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mainFragment == null || !mainFragment.isAdded() || !mainFragment.deselectIfSelected()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
