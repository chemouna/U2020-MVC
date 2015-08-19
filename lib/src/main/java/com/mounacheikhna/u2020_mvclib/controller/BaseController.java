package com.mounacheikhna.u2020_mvc.controller;

import android.content.Intent;
import android.support.annotation.VisibleForTesting;

import com.mounacheikhna.alias.AppDisplay;

/**
 * Created by cheikhna on 13/05/2015.
 */
abstract class BaseController {

    private AppDisplay mDisplay;
    private boolean mInited;

    public final void init() {
        if(mInited) {
            throw new IllegalStateException("Already inited");
        }
        mInited = true;
        onInited();
    }

    public final void suspend() {
        if(!mInited) {
            throw new IllegalStateException("Not inited");
        }
        onSuspended();
        mInited = false;
    }

    public final boolean isInited() {
        return mInited;
    }

    protected void onInited() {}

    protected void onSuspended() {}

    public boolean handleIntent(Intent intent) {
        return false;
    }

    @VisibleForTesting
    void setDisplay(AppDisplay display) {
        mDisplay = display;
    }

    protected final AppDisplay getDisplay() {
        return mDisplay;
    }

}
