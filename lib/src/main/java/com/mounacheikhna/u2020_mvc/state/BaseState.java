package com.mounacheikhna.u2020_mvc.state;

import android.support.annotation.NonNull;


/**
 * Created by cheikhna on 04/06/15.
 */
public interface BaseState {

    void registerForEvents(Object receiver);
    void unregisterForEvents(Object receiver);

    class UiCausedEvent { //dont know yet why do we need an id ?
        public final int callingId;

        public UiCausedEvent(int callingId) {
            this.callingId = callingId;
        }
    }

    class BaseArgumentEvent<T> extends UiCausedEvent {
        public final T item;

        public BaseArgumentEvent(int callingId, @NonNull T item) {
            super(callingId);
            this.item = item;
        }
    }

}
