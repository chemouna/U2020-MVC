package com.mounacheikhna.u2020_mvc.controller;

import android.support.annotation.NonNull;

import com.mounacheikhna.alias.AppDisplay;
import com.mounacheikhna.alias.lib.BuildConfig;
import com.mounacheikhna.alias.state.BaseState;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import timber.log.Timber;

/**
 * Created by cheikhna on 13/05/2015.
 */
abstract class BaseUiController<U extends BaseUiController.Ui<UC>, UC>
                    extends BaseController {

    private Set<U> mUis;
    private Set<U> mUnmodifiableUis;

    public interface Ui<UC> {
        void setCallbacks(UC callbacks);
        boolean isModal();
    }

    public interface SubUi {
    }

    public BaseUiController() {
        this.mUis = new CopyOnWriteArraySet<>();
        this.mUnmodifiableUis = Collections.unmodifiableSet(mUis);
    }

    public synchronized final void attachUi(@NonNull U ui) {
        if(mUis.contains(ui)) {
            throw new IllegalArgumentException("UI is already attached");
        }
        mUis.add(ui);

        ui.setCallbacks(createUiCallbacks(ui));

        if(isInited()) {
            if (!ui.isModal() && !(ui instanceof SubUi)) {
                updateDisplayTitle(getUiTitle(ui)); //TODO: make each ui give his title with ui.getTitle()
            }
            onUiAttached(ui);
            populateUi(ui); //TODO: improve this by making each ui populate itself
        }
    }

    public synchronized final void detachUi(@NonNull U ui) {
        if(!mUis.contains(ui)) {
            throw new IllegalStateException("ui is not attached");
        }
        onUiDetached(ui);
        ui.setCallbacks(null);
        mUis.remove(ui);
    }

    protected void onInited() {
        if (!mUis.isEmpty()) {
            for (U ui : mUis) {
                onUiAttached(ui);
                populateUi(ui);
            }
        }
    }

    protected synchronized final void populateUis() {
        if (BuildConfig.DEBUG) {
            Timber.d(getClass().getSimpleName(), "populateUis");
        }
        for (U ui : mUis) {
            populateUi(ui);
        }
    }

    protected boolean onBack() {
        if (!mUis.isEmpty()) {
            for (U ui : mUis) {
                if(onBack(ui)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean onBack(U ui) {
        return false;
    }

    protected void populateUi(U ui) {}

    protected String getUiTitle(U ui) {
        return null;
    }

    protected final void updateDisplayTitle(String title) {
        AppDisplay display = getDisplay();
        if (display != null) {
            display.setActionBarTitle(title);
        }
    }

    protected synchronized U findUi(final int id) {
        for (U ui : mUis) {
            if (getId(ui) == id) {
                return ui;
            }
        }
        return null;
    }

    protected void onUiAttached(U ui) {
    }

    protected void onUiDetached(U ui) {
    }


    protected abstract UC createUiCallbacks(U ui);

    protected int getId(U ui) {
        return ui.hashCode();
    }

    public Set<U> getUis() {
        return mUnmodifiableUis;
    }

    protected final void populateUiFromEvent(@NonNull BaseState.UiCausedEvent event) {
        final U ui = findUi(event.callingId);
        if (ui != null) {
            populateUi(ui);
        }
    }

}
