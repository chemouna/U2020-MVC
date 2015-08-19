package com.mounacheikhna.u2020_mvc.state;

import android.support.annotation.NonNull;

import com.squareup.otto.Bus;

/**
 * Created by cheikhna on 04/06/15.
 */
public final class ApplicationState implements BaseState {

    private final Bus mEventBus;

    public ApplicationState(@NonNull Bus eventBus) {
        mEventBus = eventBus;
    }

    @Override
    public void registerForEvents(final Object receiver) {
        mEventBus.register(receiver);
    }

    @Override
    public void unregisterForEvents(final Object receiver) {
        mEventBus.unregister(receiver);
    }

}

