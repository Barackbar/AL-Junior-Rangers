package com.au_team11.aljuniorrangers;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

//testing the ability of branching and merging

public class MainActivity extends Activity implements ParkListener, ParkActivityListener {

    TrailWalkFragment trailWalkFragment = null;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate");

        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        //create a new TrailWalkFragment
        //TODO: pass filename for object data in constructor arguments
        if (savedInstanceState == null) {

            /* COMMENTED FOR TESTING PURPOSES ONLY
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            fragmentManager.beginTransaction().add(R.id.activity_main, mainMenuFragment).commit();
            */

            TrailWalkFragmentArcGIS trailWalkFragmentArcGIS = new TrailWalkFragmentArcGIS();
            fragmentManager.beginTransaction()
                           .add(R.id.activity_main, trailWalkFragmentArcGIS)
                           .commit();
        }

    }

    @Override
    public void onBackPressed() {
        //will exit the app or pop backstack willy nilly
        super.onBackPressed();

        //will put the park menu on screen

    }

    public void onParkSelectedListener(String parkFileName) {
        //create new ParkFragment to put on screen
        ParkFragment parkFragment = new ParkFragment();
        //load the arguments bundle with the asset data file
        Bundle arguments = new Bundle();
        arguments.putString(getResources().getString(R.string.AssetBundleKey), parkFileName);
        parkFragment.setArguments(arguments);
        //put the fragment on the screen
        fragmentManager.beginTransaction()
                       .replace(R.id.activity_main, parkFragment)
                       .addToBackStack(null)
                       .commit();
    }

    public void onParkActivitySelectedListener(String fileName, String type) {

        if (type.equals("trailwalk")) {
            trailWalkFragment = new TrailWalkFragment();
            Bundle arguments = new Bundle();
            arguments.putString(getResources().getString(R.string.AssetBundleKey), fileName);
            trailWalkFragment.setArguments(arguments);
            //put the fragment on the screen
            fragmentManager.beginTransaction()
                           .replace(R.id.activity_main, trailWalkFragment)
                           .addToBackStack(null)
                           .commit();
        }
        else if (type.equals("wordsearch")) {
            WordSearchFragment wordSearchFragment = new WordSearchFragment();
            fragmentManager.beginTransaction()
                           .replace(R.id.activity_main, wordSearchFragment)
                           .addToBackStack(null)
                           .commit();
        }
        else {
            //do nothing
        }
    }
}
