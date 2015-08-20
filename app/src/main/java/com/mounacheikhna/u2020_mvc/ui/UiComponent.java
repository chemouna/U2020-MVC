package com.mounacheikhna.u2020_mvc.ui;

import dagger.Component;

/**
 * Created by cheikhna on 20/08/15.
 */
@Component(modules = { UiModule.class })
public interface UiComponent {

    void injectMainActivity(MainActivity activity);

}
