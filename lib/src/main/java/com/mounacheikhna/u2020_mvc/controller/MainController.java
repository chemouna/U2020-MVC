package com.mounacheikhna.u2020_mvc.controller;

import android.support.annotation.NonNull;

import com.mounacheikhna.u2020_mvc.AppDisplay;
import com.mounacheikhna.u2020_mvc.state.ApplicationState;
import com.squareup.otto.DeadEvent;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by cheikhna on 13/05/2015.
 */
@Singleton
public class MainController extends BaseUiController<MainController.MainControllerUi,
        MainController.MainControllerUiCallbacks> {

    private ApplicationState mState;

    @Inject
    public MainController(@NonNull ApplicationState state) {
        super();
        mState = state;

    }

    @Override
    protected MainControllerUiCallbacks createUiCallbacks(MainControllerUi ui) {
        return new MainControllerUiCallbacks() {
            @Override
            public boolean onBack() {
                /*if (mRoutineController.onBack()) {
                    return true;
                }*/
                return false;
            }
        };
    }

    public void onNavigationItemSelected(NavigationMenuItem item) {
        AppDisplay display = getDisplay();
        if (display != null) {
            switch (item) {

            }
            display.closeDrawerLayout();
        }
    }

    public interface MainControllerUi extends BaseUiController.Ui<MainControllerUiCallbacks> {
    }

    public interface NavigationUi extends MainControllerUi {

    }

    @Override
    protected void onInited() {
        super.onInited();
        mState.registerForEvents(this);

    }

    @Override
    protected void onSuspended() {
        mState.unregisterForEvents(this);
        //TODO: maybe close stuff here like Subscriptions , sqlbrite cursors.
        super.onSuspended();
    }

    public boolean onHomeButtonPressed() {
        AppDisplay display = getDisplay();
        if (display != null && (display.toggleDrawer() || display.popEntireFragmentBackStack())) {
            return true;
        }
        return false;
    }

    public boolean onAboutButtonPressed() {
        AppDisplay display = getDisplay();
        if (display != null) {
            display.showAbout();
        }
        return true;
    }

    public boolean onSettingsButtonPressed() {
        AppDisplay display = getDisplay();
        if (display != null) {
            display.showSettings();
        }
        return true;
    }

    public interface MainControllerUiCallbacks {
        boolean onBack();
    }

    public interface MainUi extends MainControllerUi {
    }

    public void attachDisplay(@NonNull AppDisplay display) {
        if (getDisplay() != null) {
            throw new IllegalArgumentException("We currently have a display");
        }
        setDisplay(display);
    }

    public void detachDisplay(@NonNull AppDisplay display) {
        if (getDisplay() != display) {
            throw new IllegalArgumentException("display is not attached");
        }
        setDisplay(null);
    }

    @Override
    protected void setDisplay(final AppDisplay display) {
        super.setDisplay(display);

    }

    public enum NavigationMenuItem {
        MY_ROUTINES, ACTIVE_ROUTINES, INACTIVE_ROUTINES
    }

    @Subscribe
    public void onDeadEvent(DeadEvent event) {
        Timber.d(" (TAG-TEST) Watchout received a dead event for : " + event.event);
    }

}

