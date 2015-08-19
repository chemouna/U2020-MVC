package com.mounacheikhna.u2020_mvc;

import android.app.Application;

import com.mounacheikhna.u2020_mvc.data.LumberYard;
import com.mounacheikhna.u2020_mvc.ui.ActivityHierarchyServer;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * Created by cheikhna on 19/08/15.
 */
public class U2020App extends Application {

    @Inject ActivityHierarchyServer activityHierarchyServer;
    @Inject LumberYard lumberYard;

    @Override public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        LeakCanary.install(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        } else {
            // TODO Crashlytics.start(this);
            // TODO Timber.plant(new CrashlyticsTree());
        }

        lumberYard.cleanUp();
        Timber.plant(lumberYard.tree());

        registerActivityLifecycleCallbacks(activityHierarchyServer);
    }

}
