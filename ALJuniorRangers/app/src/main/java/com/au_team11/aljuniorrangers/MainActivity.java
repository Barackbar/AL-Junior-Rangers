package com.au_team11.aljuniorrangers;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity implements ParkListener, ParkActivityListener {

    TrailWalkFragmentArcGIS trailWalkFragment = null;
    //used by the actionPointPicture to determine if the picture was successfully taken
    Boolean trailWalkPictureTaken;

    FragmentManager fragmentManager;

    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate");

        trailWalkPictureTaken = false;

        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        //TODO: pass filename for object data in constructor arguments
        if (savedInstanceState == null) {
            //put the main menu on screen
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            fragmentManager.beginTransaction().add(R.id.activity_main, mainMenuFragment).commit();

            //new wordsearch instantiation
            //TODO: put this into the type.equals("wordsearch") part in onParkActivitySelectedListener
            //WordSearchFragmentJDSS wordSearchFragmentJDSS = new WordSearchFragmentJDSS();
            //Bundle arguments = new Bundle();
            //arguments.putString(getResources().getString(R.string.AssetBundleKey), "wordsearch_test_jdss.json");
            //wordSearchFragmentJDSS.setArguments(arguments);
            //fragmentManager.beginTransaction().add(R.id.activity_main, wordSearchFragmentJDSS).commit();
        }
    }

    @Override
    public void onBackPressed() {
        //will exit the app or pop backstack willy nilly
        super.onBackPressed();

        //will put the park menu on screen

    }

    public void onParkSelectedListener(String parkFileName) {
        //create a new ParkFragment to put on screen
        ParkFragment parkFragment = new ParkFragment();
        //load the arguments bundle with the asset data file name
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
            trailWalkFragment = new TrailWalkFragmentArcGIS();
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
            //WordSearchFragment wordSearchFragment = new WordSearchFragment();

            //new wordsearch instantiation
            WordSearchFragmentJDSS wordSearchFragmentJDSS = new WordSearchFragmentJDSS();
            Bundle arguments = new Bundle();
            arguments.putString(getResources().getString(R.string.AssetBundleKey), fileName);
            wordSearchFragmentJDSS.setArguments(arguments);

            fragmentManager.beginTransaction()
                           .replace(R.id.activity_main, wordSearchFragmentJDSS)
                           .addToBackStack(null)
                           .commit();
        }
        else if (type.equals("animalparts")) {
            /*InfoFragment infoFragment = new InfoFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_main, infoFragment)
                    .addToBackStack(null)
                    .commit();
            */
            AnimalPartsFragment animalPartsFragment = new AnimalPartsFragment();
            Bundle arguments = new Bundle();
            arguments.putString(getResources().getString(R.string.AssetBundleKey), fileName);
            animalPartsFragment.setArguments(arguments);
            fragmentManager.beginTransaction()
                    .add(R.id.activity_main, animalPartsFragment)
                    .addToBackStack(null)
                    .commit();
        }
        else if (type.equals("progressreport")) {
            ProgressReportFragment progressReportFragment = new ProgressReportFragment();
            Bundle arguments = new Bundle();
            arguments.putString(getResources().getString(R.string.AssetBundleKey), fileName);
            progressReportFragment.setArguments(arguments);
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_main, progressReportFragment)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            //do nothing
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MainActivity", "oAR");
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Log.i("MainActivity", "rC == 1 && rC == R_OK");
        }
    }
}
