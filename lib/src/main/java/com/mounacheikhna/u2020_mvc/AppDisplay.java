package com.mounacheikhna.u2020_mvc;

import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by cheikhna on 11/05/2015.
 */
public interface AppDisplay {

    void closeDrawerLayout();

    boolean toggleDrawer();

    /**
     * Show a process fragment using its id
     * @param routineId
     */
    void showRoutine(long routineId);

    /**
     * Display screen for creating a new routine
     */
    //void showCreateRoutine();

    /**
     * Pops entire backstack from fragment manager
     */
    boolean popEntireFragmentBackStack();

    /**
     * display's activity does have a main fragment set
     */
    boolean hasMainFragment();

    /**
     * set toolbar as action bar
     */
    void setSupportActionBar(Object toolbar, boolean handleBackground);

    void setActionBarTitle(CharSequence title);


    /**
     * Shows a collection of routines
     */
    void showMyRoutinesFragment();

    /**
     * Display up indicator
     */
    void showUpNavigation(boolean show);

    /**
     * Sets subtitle on screen action bar
     * @param subtitle
     */
    void setActionBarSubtitle(CharSequence subtitle);

    /**
     * Display screen to search and select tasks
     */
    void showSearchTasks();

    /**
     * Display a screen to create tasks
     */
    //void showCreateTask();

    void showAbout();

    void showSettings();

    void showAboutFragment();

    void showMessage(View topView, @StringRes int messageResId);

    void showColorPicker();

    void setMainColor(int color);

    void setStatusBarColor(int color);

    boolean goBack();
}
